package com.wade.lifewater.mapper;

import com.wade.lifewater.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    List<Department> getAllDepartments();

    List<Department> selectByPartentId(Integer id);


}