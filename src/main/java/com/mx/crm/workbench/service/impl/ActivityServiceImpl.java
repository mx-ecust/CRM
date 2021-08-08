package com.mx.crm.workbench.service.impl;

import com.mx.crm.utils.SqlSessionUtil;
import com.mx.crm.workbench.dao.ActivityDao;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.service.ActivityService;

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
}
