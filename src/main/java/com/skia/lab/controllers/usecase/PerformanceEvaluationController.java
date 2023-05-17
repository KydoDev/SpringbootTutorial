package com.skia.lab.controllers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skia.lab.models.usecase.PerformanceEvaluation;
import com.skia.lab.repository.usecase.PerformanceEvaluationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/performanceEvaluation")
public class PerformanceEvaluationController {

    @Autowired
    PerformanceEvaluationRepository performanceEvaluationRepository;

    // @Autowired
    // public PerformanceEvaluationController(PerformanceEvaluationRepository performanceEvaluationRepository) {
    // this.performanceEvaluationRepository = performanceEvaluationRepository;
    // }

    @PostMapping
    public ResponseEntity<PerformanceEvaluation> createPerformanceEvaluation(@RequestBody PerformanceEvaluation performanceEvaluation) {
        try {
            PerformanceEvaluation _performanceEvaluation = performanceEvaluationRepository
                    .save(new PerformanceEvaluation(performanceEvaluation.getEvaluationDate(), performanceEvaluation.getResults(), 
                    performanceEvaluation.getComments(), performanceEvaluation.getFeedback(), performanceEvaluation.getEmployee()));
            return new ResponseEntity<>(_performanceEvaluation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceEvaluation> getPerformanceEvaluationById(@PathVariable Long id) {
        Optional<PerformanceEvaluation> performanceEvaluationData = performanceEvaluationRepository.findById(id);
        if (performanceEvaluationData.isPresent()) {
            return new ResponseEntity<>(performanceEvaluationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PerformanceEvaluation>> getAllPerformanceEvaluations(@RequestParam(required = false) Long employeeId) {

        try {
            List<PerformanceEvaluation> performanceEvaluation = new ArrayList<PerformanceEvaluation>();

            if (employeeId != null)
                performanceEvaluationRepository.findByEmployeeId(employeeId).forEach(performanceEvaluation::add);
            else
                performanceEvaluationRepository.findAll().forEach(performanceEvaluation::add);

            if (performanceEvaluation.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(performanceEvaluation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<PerformanceEvaluation> updatePerformanceEvaluation(@PathVariable Long id, @RequestBody PerformanceEvaluation performanceEvaluation) {
        Optional<PerformanceEvaluation> performanceEvaluationData = performanceEvaluationRepository.findById(id);

        if (performanceEvaluationData.isPresent()) {
            PerformanceEvaluation _performanceEvaluation = performanceEvaluationData.get();
          _performanceEvaluation.setEvaluationDate(performanceEvaluation.getEvaluationDate());
          _performanceEvaluation.setResults(performanceEvaluation.getResults());
          _performanceEvaluation.setComments(performanceEvaluation.getComments());
          _performanceEvaluation.setFeedback(performanceEvaluation.getFeedback());
          _performanceEvaluation.setEmployee(performanceEvaluation.getEmployee());
          return new ResponseEntity<>(performanceEvaluationRepository.save(_performanceEvaluation), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformanceEvaluation(@PathVariable Long id) {
        try {
            performanceEvaluationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<PerformanceEvaluation>> findPerformanceEvaluations(@RequestParam String query) {
    //     List<PerformanceEvaluation> performanceEvaluations = performanceEvaluationRepository.findPerformanceEvaluations(query);
    //     return ResponseEntity.ok(performanceEvaluations);
    // }

//setmanager

//setDepartment

}
