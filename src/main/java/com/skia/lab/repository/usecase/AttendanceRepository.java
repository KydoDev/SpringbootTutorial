package com.skia.lab.repository.usecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // You can define custom query methods here if needed
}
