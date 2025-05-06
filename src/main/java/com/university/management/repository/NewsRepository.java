package com.university.management.repository;

import com.university.management.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Optional<News> findByNewsCode(String newsCode);
    boolean existsByNewsCode(String newsCode);
    
    @Query(value = "SELECT MAX(CAST(SUBSTRING(matin, 3, 6) AS UNSIGNED)) FROM news", nativeQuery = true)
    Integer findMaxNewsNumber();
}
