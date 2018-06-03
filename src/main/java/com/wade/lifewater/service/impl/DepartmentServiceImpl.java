package com.wade.lifewater.service.impl;

import com.wade.lifewater.mapper.DepartmentMapper;
import com.wade.lifewater.model.Department;
import com.wade.lifewater.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhouwei
 * @Date: 2018/6/1
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments();
    }

    @Override
    public List<Department> selectByPartentId(Integer id) {
        return departmentMapper.selectByPartentId(id);
    }

}
