package com.mx.crm.workbench.service;

import com.mx.crm.vo.PaginationVO;
import com.mx.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {


    boolean save(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);
}
