package com.university.Harvard.repository;

import com.university.Harvard.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query("select l.name from Lector l where l.name like %:searchingWord%")
    List<String> findBySearchWord(String searchingWord);

}
