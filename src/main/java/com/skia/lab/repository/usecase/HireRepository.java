package com.skia.lab.repository.usecase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Hire;

@Repository
public interface HireRepository extends JpaRepository<Hire, Long> {

    @Query("SELECT e FROM Hire e WHERE e.employee.id = :employeeId")
    List<Hire> findByEmployeeId(Long employeeId);
    // You can define custom query methods here if needed


}
