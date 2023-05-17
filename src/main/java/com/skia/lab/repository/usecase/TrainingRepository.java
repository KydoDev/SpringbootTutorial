package com.skia.lab.repository.usecase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT e FROM Training e WHERE e.employee.id = :employeeId")
    List<Training> findByEmployeeId(Long employeeId);
    // You can define custom query methods here if needed
}
