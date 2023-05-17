package com.skia.lab.controllers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skia.lab.models.usecase.Training;
import com.skia.lab.repository.usecase.TrainingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/training")
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    // @Autowired
    // public TrainingController(TrainingRepository trainingRepository) {
    // this.trainingRepository = trainingRepository;
    // }

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        try {
            Training _training = trainingRepository
            .save(new Training(training.getStartDate(), training.getEndDate(), 
            training.getType(), training.getDescription(), training.getEmployee()));
    return new ResponseEntity<>(_training, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        Optional<Training> trainingData = trainingRepository.findById(id);
        if (trainingData.isPresent()) {
            return new ResponseEntity<>(trainingData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Training>> getAllTrainings(@RequestParam(required = false) Long employeeId) {

        try {
            List<Training> training = new ArrayList<Training>();

            if (employeeId != null)
                trainingRepository.findByEmployeeId(employeeId).forEach(training::add);
            else
                trainingRepository.findAll().forEach(training::add);

            if (training.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(training, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestBody Training training) {
        Optional<Training> trainingData = trainingRepository.findById(id);

        if (trainingData.isPresent()) {
            Training _training = trainingData.get();
          _training.setStartDate(training.getStartDate());
          _training.setEndDate(training.getEndDate());
          _training.setType(training.getType());
          _training.setDescription(training.getDescription());
          _training.setEmployee(training.getEmployee());
          return new ResponseEntity<>(trainingRepository.save(_training), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        try {
            trainingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<Training>> findTrainings(@RequestParam String query) {
    //     List<Training> trainings = trainingRepository.findTrainings(query);
    //     return ResponseEntity.ok(trainings);
    // }

//setmanager

//setDepartment

}
