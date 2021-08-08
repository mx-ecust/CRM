package com.mx.crm.workbench.service.impl;

import com.mx.crm.utils.SqlSessionUtil;
import com.mx.crm.workbench.dao.ActivityDao;
import com.mx.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);


}
