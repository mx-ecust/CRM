package com.mx.crm.workbench.web.controller;

import com.mx.crm.settings.domain.User;
import com.mx.crm.settings.service.UserService;
import com.mx.crm.settings.service.impl.UserServiceImpl;
import com.mx.crm.utils.DateTimeUtil;
import com.mx.crm.utils.PrintJson;
import com.mx.crm.utils.ServiceFactory;
import com.mx.crm.utils.UUIDUtil;
import com.mx.crm.vo.PaginationVO;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.domain.ActivityRemark;
import com.mx.crm.workbench.service.ActivityService;
import com.mx.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");

        String path = req.getServletPath();

        if ("/workbench/activity/getUserList.do".equals(path)) {

            //getUserList(req, resp);

        } else if ("/workbench/activity/save.do".equals(path)) {

            //save(req,resp);

        }

    }


}
