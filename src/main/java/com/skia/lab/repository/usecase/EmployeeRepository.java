package com.skia.lab.repository.usecase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skia.lab.models.usecase.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployee(Long id);
    // You can define custom query methods here if needed

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, Employee employee);

    boolean deleteEmployee(Long id);

    List<Employee> findEmployees(String query);
}
