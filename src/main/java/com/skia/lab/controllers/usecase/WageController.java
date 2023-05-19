package com.skia.lab.controllers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skia.lab.models.usecase.Wage;
import com.skia.lab.repository.usecase.WageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/wage")
public class WageController {

    @Autowired
    WageRepository wageRepository;

    // @Autowired
    // public WageController(WageRepository wageRepository) {
    // this.wageRepository = wageRepository;
    // }

    @PostMapping
    public ResponseEntity<Wage> createWage(@RequestBody Wage wage) {
        try {
            Wage _wage = wageRepository
                    .save(new Wage(wage.getPaymentDate(), wage.getSalaryAmount(), wage.getTaxes(),
                            wage.getDeductions(), wage.getEmployee()));
            return new ResponseEntity<>(_wage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wage> getWageById(@PathVariable Long id) {
        Optional<Wage> wageData = wageRepository.findById(id);
        if (wageData.isPresent()) {
            return new ResponseEntity<>(wageData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Wage>> getAllWages(@RequestParam(required = false) Long employeeId) {

        try {
            List<Wage> wage = new ArrayList<Wage>();

            if (employeeId != null)
                wageRepository.findByEmployeeId(employeeId).forEach(wage::add);
            else
                wageRepository.findAll().forEach(wage::add);

            if (wage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Wage> updateWage(@PathVariable Long id, @RequestBody Wage wage) {
        Optional<Wage> wageData = wageRepository.findById(id);

        if (wageData.isPresent()) {
            Wage _wage = wageData.get();
          _wage.setPaymentDate(wage.getPaymentDate());
          _wage.setSalaryAmount(wage.getSalaryAmount());
          _wage.setTaxes(wage.getTaxes());
          _wage.setDeductions(wage.getDeductions());
          _wage.setEmployee(wage.getEmployee());
          return new ResponseEntity<>(wageRepository.save(_wage), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWage(@PathVariable Long id) {
        try {
            wageRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<Wage>> findWages(@RequestParam String query) {
    //     List<Wage> wages = wageRepository.findWages(query);
    //     return ResponseEntity.ok(wages);
    // }

//setmanager

//setDepartment

}
