package com.university.Harvard.repository;

import com.university.Harvard.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String departmentName);

    @Query("select d.name from Department d  where d.name like %:searchingWord%")
    List<String> findBySearchWord(String searchingWord);
}
