package com.wade.lifewater.service;

import com.wade.lifewater.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    List<Department> selectByPartentId(Integer id);

}
