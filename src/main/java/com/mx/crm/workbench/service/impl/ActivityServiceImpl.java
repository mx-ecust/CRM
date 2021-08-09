package com.mx.crm.workbench.service.impl;

import com.mx.crm.utils.SqlSessionUtil;
import com.mx.crm.vo.PaginationVO;
import com.mx.crm.workbench.dao.ActivityDao;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

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
}
