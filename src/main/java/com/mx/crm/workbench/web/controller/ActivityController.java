package com.mx.crm.workbench.web.controller;

import com.mx.crm.settings.domain.User;
import com.mx.crm.settings.service.UserService;
import com.mx.crm.settings.service.impl.UserServiceImpl;
import com.mx.crm.utils.*;
import com.mx.crm.vo.PaginationVO;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.domain.ActivityRemark;
import com.mx.crm.workbench.service.ActivityService;
import com.mx.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("���뵽�г��������");

        String path = req.getServletPath();

        if ("/workbench/activity/getUserList.do".equals(path)) {

            getUserList(req, resp);

        } else if ("/workbench/activity/save.do".equals(path)) {

            save(req,resp);

        } else if ("/workbench/activity/pageList.do".equals(path)){

            pageList(req,resp);

        } else if ("/workbench/activity/delete.do".equals(path)){

            delete(req,resp);

        } else if("/workbench/activity/getUserListAndActivity.do".equals(path)){

            getUserListAndActivity(req,resp);

        }else if("/workbench/activity/update.do".equals(path)){

            update(req,resp);

        }else if("/workbench/activity/detail.do".equals(path)){

            detail(req,resp);

        }else if("/workbench/activity/getRemarkListByAid.do".equals(path)){

            getRemarkListByAid(req,resp);

        }else if("/workbench/activity/deleteRemark.do".equals(path)){

            deleteRemark(req,resp);

        }else if("/workbench/activity/saveRemark.do".equals(path)){

            saveRemark(req,resp);

        }else if("/workbench/activity/updateRemark.do".equals(path)){

            updateRemark(req,resp);

        }

    }

    private void updateRemark(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ִ���޸ı�ע����");

        String id = req.getParameter("id");
        String noteContent = req.getParameter("noteContent");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)req.getSession().getAttribute("user")).getName();
        String editFlag = "1";

        ActivityRemark ar = new ActivityRemark();

        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setEditFlag(editFlag);
        ar.setEditBy(editBy);
        ar.setEditTime(editTime);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag =  as.updateRemark(ar);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);

        PrintJson.printJsonObj(resp,map);

    }

    private void saveRemark(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ִ����ӱ�ע����");

        String noteContent = req.getParameter("noteContent");
        String activityId = req.getParameter("activityId");
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)req.getSession().getAttribute("user")).getName();
        String editFlag = "0";

        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setActivityId(activityId);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.saveRemark(ar);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);

        PrintJson.printJsonObj(resp,map);


    }

    private void deleteRemark(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ɾ����ע����");

        String id = req.getParameter("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.deleteRemark(id);

        PrintJson.printJsonFlag(resp,flag);

    }

    private void getRemarkListByAid(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("�����г��idȡ�ñ�ע��Ϣ�б�");

        String activityId = req.getParameter("activityId");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        List<ActivityRemark> arList = as.getRemarkListByAid(activityId);

        PrintJson.printJsonObj(resp,arList);


    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("���뵽��ת����ϸ��Ϣҳ�Ĳ���");

        String id = req.getParameter("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Activity a = as.detail(id);

        req.setAttribute("a",a);

        req.getRequestDispatcher("/workbench/activity/detail.jsp").forward(req,resp);

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ִ���г���޸Ĳ���");

        String id = req.getParameter("id");
        String owner = req.getParameter("owner");
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String cost = req.getParameter("cost");
        String description = req.getParameter("description");
        //�޸�ʱ�䣺��ǰϵͳʱ��
        String editTime = DateTimeUtil.getSysTime();
        //�޸��ˣ���ǰ��¼�û�
        String editBy = ((User)req.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setCost(cost);
        a.setStartDate(startDate);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setDescription(description);
        a.setEditBy(editBy);
        a.setEditTime(editTime);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.update(a);

        PrintJson.printJsonFlag(resp, flag);

    }

    private void getUserListAndActivity(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("���뵽��ѯ�û���Ϣ�б�͸����г��id��ѯ������¼");

        String id = req.getParameter("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        /*
			�ܽ᣺
                controller����service�ķ���������ֵӦ����ʲô
                �����һ��ǰ��Ҫʲô����Ҫ��service��ȡʲô

            ǰ����Ҫ�ģ���ҵ���ȥҪ
            uList
            a

            ����������Ϣ�������ʲ��ߣ�����ѡ��ʹ��map�����������Ϣ����
            map
		*/

        Map<String,Object> map =  as.getUserListAndActivity(id);

        PrintJson.printJsonObj(resp,map);

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ִ���г��ɾ������");

        String ids[] = req.getParameterValues("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.delete(ids);

        PrintJson.printJsonFlag(resp,flag);

    }

    private void pageList(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("���뵽��ѯ�г���Ϣ��б�Ĳ��������������ѯ+��ҳ��ѯ��");

        String name = req.getParameter("name");
        String owner = req.getParameter("owner");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String pageNoStr = req.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //ÿҳչ�ֵļ�¼����
        String pageSizeStr = req.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //������Թ��ļ�¼��
        int skipCount = (pageNo - 1) * pageSize;


        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        /*

            ǰ��Ҫ�� �г����Ϣ�б�
                    ��ѯ��������

                    ҵ����õ�������������Ϣ֮�����������
                    map
                    map.put("dataList":dataList)
                    map.put("total":total)
                    PrintJSON map --> json
                    {"total":100,"dataList":[{�г��1},{2},{3}]}


                    vo
                    PaginationVO<T>
                        private int total;
                        private List<T> dataList;

                    PaginationVO<Activity> vo = new PaginationVO<>;
                    vo.setTotal(total);
                    vo.setDataList(dataList);
                    PrintJSON vo --> json
                    {"total":100,"dataList":[{�г��1},{2},{3}]}


                    ������ҳ��ѯ��ÿ��ģ�鶼�У���������ѡ��ʹ��һ��ͨ��vo�����������ȽϷ���

         */

        PaginationVO<Activity> vo = as.pageList(map);

        //vo--> {"total":100,"dataList":[{�г��1},{2},{3}]}
        PrintJson.printJsonObj(resp,vo);


    }

    private void save(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ִ���г����Ӳ���");

        String id = UUIDUtil.getUUID();
        String owner = req.getParameter("owner");
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String cost = req.getParameter("cost");
        String description = req.getParameter("description");
        //����ʱ�䣺��ǰϵͳʱ��
        String createTime = DateTimeUtil.getSysTime();
        //�����ˣ���ǰ��¼�û�
        String createBy = ((User)req.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setCost(cost);
        a.setStartDate(startDate);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);

        PrintJson.printJsonFlag(resp, flag);

    }

    private void getUserList(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("ȡ���û���Ϣ�б�");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(resp, uList);


    }


}
