package com.skia.lab.repository.usecase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Employee;

@Repository //Any persistent layer which is going to communicate with the database
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // @Query("SELECT e FROM Employee e WHERE e.manager.id = :managerId")
    // List<Employee> findByManagerId(@Param("managerId") Long managerId);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);


}
