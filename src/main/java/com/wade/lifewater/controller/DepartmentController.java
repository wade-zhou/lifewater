package com.wade.lifewater.controller;

import com.github.pagehelper.PageHelper;
import com.wade.lifewater.model.Department;
import com.wade.lifewater.service.DepartmentService;
import com.wade.lifewater.utils.BuildTree;
import com.wade.lifewater.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhouwei
 * @Date: 2018/6/1
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * 无极菜单查询
     * @return
     */
    @RequestMapping("/getAllMenus")
    @ResponseBody
    public List<Tree<Department>> getAllMenus(){
        List<Tree<Department>> trees = new ArrayList();
        List<Department> menuDtoList = departmentService.getAllDepartments();
        for (Department test : menuDtoList) {
            Tree<Department> tree = new Tree();
            tree.setId(test.getId().toString());
            tree.setParentId(test.getParentId() == null ? "1" : test.getParentId().toString());
            tree.setText(test.getName());
            trees.add(tree);
        }
        List<Tree<Department>> children= BuildTree.build(trees);
        return children;
    }

    @RequestMapping("/index")
    public String index(){
        return "page/index";
    }


    @RequestMapping("/selectByPartentId")
    @ResponseBody
    public Map<String, Object> selectByPartentId(Integer offset,Integer limit,String sortOrder,String sortName,Integer id){
        id = id == null ? 1 : id;
        PageHelper.startPage( offset,limit,sortName+" "+sortOrder); //使用pagehelper就这一行代码，足够了
        List<Department> departmentList = departmentService.selectByPartentId(id);
        List<Department> counts = departmentService.selectByPartentId(id);
        Map<String, Object> map = new HashMap();
        if(departmentList != null) {
            map.put("total", counts.size());
            map.put("rows", departmentList);
        }
        return map;
    }
}
