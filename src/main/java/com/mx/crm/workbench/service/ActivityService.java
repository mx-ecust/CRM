package com.mx.crm.workbench.service;

import com.mx.crm.vo.PaginationVO;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {


    boolean save(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    boolean update(Activity a);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);
}
