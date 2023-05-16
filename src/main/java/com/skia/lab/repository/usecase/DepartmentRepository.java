package com.skia.lab.repository.usecase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // You can define custom query methods here if needed
}
