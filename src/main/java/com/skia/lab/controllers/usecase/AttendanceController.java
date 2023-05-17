package com.skia.lab.controllers.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skia.lab.models.usecase.Attendance;
import com.skia.lab.repository.usecase.AttendanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    // associated employee ID, date, time of entry, time of exit, and type of absence (vacation, sickness, leave)
    @Autowired
    AttendanceRepository attendanceRepository;

    // @Autowired
    // public AttendanceController(AttendanceRepository attendanceRepository) {
    // this.attendanceRepository = attendanceRepository;
    // }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance _attendance = attendanceRepository
                    .save(attendance);
            return new ResponseEntity<>(_attendance, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Optional<Attendance> attendanceData = attendanceRepository.findById(id);
        if (attendanceData.isPresent()) {
            return new ResponseEntity<>(attendanceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Attendance>> getAllAttendances(@RequestParam(required = false) Long employeeId) {

        try {
            List<Attendance> attendance = new ArrayList<Attendance>();

            if (employeeId != null)
                attendanceRepository.findByEmployee(employeeId).forEach(attendance::add);
            else
                attendanceRepository.findAll().forEach(attendance::add);

            if (attendance.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(attendance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        Optional<Attendance> attendanceData = attendanceRepository.findById(id);

        if (attendanceData.isPresent()) {
            Attendance _attendance = attendanceData.get();

          _attendance.setEntryTime(attendance.getEntryTime());
          _attendance.setExitTime(attendance.getExitTime());
          _attendance.setAbsenceType(attendance.getAbsenceType().ordinal());
          _attendance.setEmployee(attendance.getEmployee()); //TODO get employee from employeeId

          return new ResponseEntity<>(attendanceRepository.save(_attendance), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        try {
            attendanceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<Attendance>> findAttendances(@RequestParam String query) {
    //     List<Attendance> attendances = attendanceRepository.findAttendances(query);
    //     return ResponseEntity.ok(attendances);
    // }

//setmanager

//setDepartment

}
