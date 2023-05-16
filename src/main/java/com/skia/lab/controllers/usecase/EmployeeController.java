package com.skia.lab.controllers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skia.lab.models.usecase.Employee;
import com.skia.lab.repository.usecase.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // @Autowired
    // public EmployeeController(EmployeeRepository employeeRepository) {
    // this.employeeRepository = employeeRepository;
    // }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee _employee = employeeRepository
                    .save(new Employee(employee.getFirstName(), employee.getLastName(), employee.getBirthDate(),
                            employee.getAddress(), employee.getPhoneNumber(), employee.getEmail()));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);
        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long managerId) {

        try {
            List<Employee> employee = new ArrayList<Employee>();

            if (departmentId != null && managerId == null)
                employeeRepository.findByDepartmentId(departmentId).forEach(employee::add);
            else if (departmentId == null && managerId != null)
                employeeRepository.findByManagerId(managerId).forEach(employee::add);
            else
                employeeRepository.findAll().forEach(employee::add);

            if (employee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> employeeData = employeeRepository.findById(id);

        if (employeeData.isPresent()) {
            Employee _employee = employeeData.get();
          _employee.setFirstName(employee.getFirstName());
          _employee.setLastName(employee.getLastName());
          _employee.setBirthDate(employee.getBirthDate());
          _employee.setAddress(employee.getAddress());
          _employee.setPhoneNumber(employee.getPhoneNumber());
          _employee.setEmail(employee.getEmail());
          return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<Employee>> findEmployees(@RequestParam String query) {
    //     List<Employee> employees = employeeRepository.findEmployees(query);
    //     return ResponseEntity.ok(employees);
    // }

//setmanager

//setDepartment

}
