package com.university.Harvard.service.impl;

import com.university.Harvard.entity.Department;
import com.university.Harvard.entity.Lector;
import com.university.Harvard.repository.DepartmentRepository;
import com.university.Harvard.repository.LectorRepository;
import com.university.Harvard.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public String getHead(String departmentName) {
        if (validation(departmentName)) {
            return departmentRepository.findByName(departmentName).getHead().getName();
        }
        return "Incorrect name of department";
    }


    @Override
    public String statisticOfDegree(String departmentName) {
        Map<String, Long> result;

        Department department = departmentRepository.findByName(departmentName);
        if (validation(departmentName)) {
            result = department.getLectors().stream()
                    .collect(Collectors.groupingBy(l -> l.getDegree().name(), Collectors.counting()));

            return convertMapToString(result);
        }
        return "Incorrect name of department";
    }

    private String convertMapToString(Map<String, Long> map) {
        StringBuilder result = new StringBuilder();
        for (String degree : map.keySet()) {
            result.append(degree).append(" - ").append(map.get(degree)).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public BigDecimal getAvgSalary(String departmentName) {
        if (validation(departmentName)) {
            Department department = departmentRepository.findByName(departmentName);
            double result = department.getLectors().stream()
                    .map(Lector::getSalary)
                    .mapToDouble(BigDecimal::doubleValue)
                    .average().orElse(-1);
            return BigDecimal.valueOf(result).setScale(0, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public Integer getCountOfEmployee(String departmentName) {
        if (validation(departmentName)) {
            return departmentRepository.findByName(departmentName).getLectors().size();
        }
        return null;
    }

    @Override
    public String searchByName(String searchWord) {
        if (validation(searchWord)) {
            List<String> departmentNameBySearch = departmentRepository.findBySearchWord(searchWord);
            List<String> lectorNameBySearch = lectorRepository.findBySearchWord(searchWord);
            departmentNameBySearch.addAll(lectorNameBySearch);
            return departmentNameBySearch.stream()
                    .reduce((a, b) -> a + ", " + b).orElse("");

        }
        return null;
    }

    private boolean validation(String input) {
        if (input != null && input.matches("[a-zA-Z]+")) {
            return true;
        }
        return false;
    }
}
