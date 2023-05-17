package com.skia.lab.repository.usecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Wage;
import java.util.List;
@Repository
public interface WageRepository extends JpaRepository<Wage, Long> {
    
    @Query("SELECT e FROM Wage e WHERE e.employee.id = :employeeId")
    List<Wage> findByEmployeeId(Long employeeId);
    // You can define custom query methods here if needed
}
