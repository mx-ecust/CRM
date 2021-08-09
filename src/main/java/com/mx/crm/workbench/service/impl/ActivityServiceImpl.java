package com.mx.crm.workbench.service.impl;

import com.mx.crm.settings.dao.UserDao;
import com.mx.crm.settings.domain.User;
import com.mx.crm.utils.SqlSessionUtil;
import com.mx.crm.vo.PaginationVO;
import com.mx.crm.workbench.dao.ActivityDao;
import com.mx.crm.workbench.dao.ActivityRemarkDao;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.domain.ActivityRemark;
import com.mx.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public boolean save(Activity a) {

        boolean flag = true;

        int count  = activityDao.save(a);

        if (count != 1){
            flag = false;
        }

        return flag;
    }

    public PaginationVO<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);

        // dataList

        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        //将数据封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);



        return vo;
    }

    public boolean delete(String[] ids) {

        boolean flag = true;
        //先查询出需要删除的备注的数量
        int count1 = activityRemarkDao.getCountByAids(ids);
        //删除备注，返回受到影响的条数，即实际删除的数量
        int count2 = activityRemarkDao.deleteByAids(ids);

        if (count1 != count2){
            flag = false;
        }

        //删除市场活动
        int count3 = activityDao.delete(ids);

        if (count3 != ids.length){
            flag = false;
        }

        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {

        //取uList
        List<User> uList = userDao.getUserList();

        //取a
        Activity a = activityDao.getById(id);


        //将uList,a打包到map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("a",a);

        return map;
    }

    public boolean update(Activity a) {

        boolean flag = true;

        int count  = activityDao.update(a);

        if (count != 1){
            flag = false;
        }

        return flag;
    }

    public Activity detail(String id) {

        Activity activity = activityDao.detail(id);

        return activity;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {

        List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);

        return arList;
    }

    public boolean deleteRemark(String id) {

        boolean flag = true;

        int count = activityRemarkDao.deleteById(id);

        if (count != 1){
            flag = false;
        }

        return flag;
    }
}
