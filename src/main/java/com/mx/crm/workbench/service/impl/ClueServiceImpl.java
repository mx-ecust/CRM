package com.mx.crm.workbench.service.impl;

import com.mx.crm.utils.SqlSessionUtil;
import com.mx.crm.workbench.dao.ClueDao;
import com.mx.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {

    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);

}
