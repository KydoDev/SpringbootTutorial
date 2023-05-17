package com.skia.lab.repository.usecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.PerformanceEvaluation;
import java.util.List;
@Repository
public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {

    @Query("SELECT e FROM PerformanceEvaluation e WHERE e.employee.id = :employeeId")
    List<PerformanceEvaluation> findByEmployeeId(Long employeeId);
    // You can define custom query methods here if needed
}
