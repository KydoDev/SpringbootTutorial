package com.skia.lab.repository.usecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Attendance;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT e FROM Attendance e WHERE e.employee.id = :employeeId")
    List<Attendance> findByEmployee(Long employeeId);
    // You can define custom query methods here if needed
}
