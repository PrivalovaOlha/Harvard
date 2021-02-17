package com.university.Harvard.service;

import java.math.BigDecimal;

public interface DepartmentService {

    String getHead(String departmentName);

    String statisticOfDegree(String departmentName);

    BigDecimal getAvgSalary(String departmentName);

    Integer getCountOfEmployee(String departmentName);

    String searchByName(String searchWord);
}
