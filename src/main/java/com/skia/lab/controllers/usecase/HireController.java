package com.skia.lab.controllers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skia.lab.models.usecase.Hire;
import com.skia.lab.repository.usecase.HireRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/hire")
public class HireController {

    @Autowired
    HireRepository hireRepository;

    // @Autowired
    // public HireController(HireRepository hireRepository) {
    // this.hireRepository = hireRepository;
    // }

    @PostMapping
    public ResponseEntity<Hire> createHire(@RequestBody Hire hire) {
        try {
            Hire _hire = hireRepository
                    .save(new Hire(hire.getHiringDate(), hire.getPosition(), hire.getInitialSalary(),
                            hire.getContractType(), hire.getEmployee()));
            return new ResponseEntity<>(_hire, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hire> getHireById(@PathVariable Long id) {
        Optional<Hire> hireData = hireRepository.findById(id);
        if (hireData.isPresent()) {
            return new ResponseEntity<>(hireData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Hire>> getAllHires(@RequestParam(required = false) Long employeeId) {

        try {
            List<Hire> hire = new ArrayList<Hire>();

            if (employeeId != null)
                hireRepository.findByEmployeeId(employeeId).forEach(hire::add);
            else
                hireRepository.findAll().forEach(hire::add);

            if (hire.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Hire> updateHire(@PathVariable Long id, @RequestBody Hire hire) {
        Optional<Hire> hireData = hireRepository.findById(id);

        if (hireData.isPresent()) {
            Hire _hire = hireData.get();
            _hire.setHiringDate(hire.getHiringDate());
            _hire.setPosition(hire.getPosition());
            _hire.setInitialSalary(hire.getInitialSalary());
            _hire.setContractType(hire.getContractType());
            _hire.setEmployee(hire.getEmployee());
            return new ResponseEntity<>(hireRepository.save(_hire), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHire(@PathVariable Long id) {
        try {
            hireRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<Hire>> findHires(@RequestParam String query) {
    // List<Hire> hires = hireRepository.findHires(query);
    // return ResponseEntity.ok(hires);
    // }

    // setmanager

    // setDepartment

}
