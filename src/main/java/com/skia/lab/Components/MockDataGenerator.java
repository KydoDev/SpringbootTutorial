package com.skia.lab.Components;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.skia.lab.models.usecase.AbsenceType;
import com.skia.lab.models.usecase.Attendance;
import com.skia.lab.models.usecase.Department;
import com.skia.lab.models.usecase.Employee;
import com.skia.lab.models.usecase.Hire;
import com.skia.lab.repository.usecase.AttendanceRepository;
import com.skia.lab.repository.usecase.DepartmentRepository;
import com.skia.lab.repository.usecase.EmployeeRepository;
import com.skia.lab.repository.usecase.HireRepository;
import com.skia.lab.repository.usecase.PerformanceEvaluationRepository;
import com.skia.lab.repository.usecase.TrainingRepository;
import com.skia.lab.repository.usecase.WageRepository;

@Component
public class MockDataGenerator {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final HireRepository hireRepository;
    private final AttendanceRepository attendanceRepository;
    private final PerformanceEvaluationRepository performanceEvaluationRepository;
    private final TrainingRepository trainingRepository;
    private final WageRepository wageRepository;
    private final Faker faker;

    public MockDataGenerator(EmployeeRepository employeeRepository,
                                DepartmentRepository departmentRepository,
                                HireRepository hireRepository,
                                AttendanceRepository attendanceRepository,
                                PerformanceEvaluationRepository performanceEvaluationRepository,
                                TrainingRepository trainingRepository,
                                WageRepository wageRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.hireRepository = hireRepository;
        this.attendanceRepository = attendanceRepository;
        this.performanceEvaluationRepository = performanceEvaluationRepository;
        this.trainingRepository = trainingRepository;
        this.wageRepository = wageRepository;
        this.faker = new Faker();
    }

    public void generateMockData() {
        
        if(departmentRepository.count() == 0){
            for (int i = 0; i < 5; i++) {
                Department department = new Department(faker.name().title());
                
            }
        }
        
        if(employeeRepository.count() == 0){
            
            for (int i = 0; i < 20; i++) {
                Employee employee = new Employee();
                employee.setFirstName(faker.name().firstName());
                employee.setLastName(faker.name().lastName());
                employee.setBirthDate(LocalDate.now());
                employee.setAddress(faker.address().fullAddress());
                employee.setPhoneNumber(faker.name().fullName());
                employee.setEmail(faker.internet().emailAddress());

                // employee.setDepartment(i<10 ? department1 : department2);
                employeeRepository.save(employee);
            }
        }
        
        if(hireRepository.count() == 0){
            
            var e0 =employeeRepository.findById((long)0);
            Hire hire0 = new Hire(LocalDate.now(), "Patata"+0, 50000, "commercio", e0.get());
            hireRepository.save(hire0);
            for (int i = 1; i < 20; i++) {
                var e =employeeRepository.findById((long)i);
                Hire hire = new Hire(LocalDate.now(), "Sales", 30000, "commercio", e.get());

                hireRepository.save(hire);
            }

        }
        if(attendanceRepository.count() == 0){
            
            var e0 =employeeRepository.findById((long)0);
            Attendance att0 = new Attendance(LocalDate.of(2023,2,1), LocalTime.of(8,0,0),LocalTime.of(18,0,0), null, e0.get());
            attendanceRepository.save(att0);

            for (int i = 1; i < 28; i++){
                
                Attendance attX = new Attendance(LocalDate.of(2023,2,1), LocalTime.of(8,0,0),LocalTime.of(18,0,0), null , e0.get());
                    attX.setAbsenceType(AbsenceType.sickness.ordinal());
                attendanceRepository.save(attX);
            }

            for (int j = 1; j < 20; j++) {
                var e =employeeRepository.findById((long)j);

                for (int i = 1; i < 28; i++) attendanceRepository.save( new Attendance(LocalDate.of(2023,2,i), LocalTime.of(8,0,0),LocalTime.of(18,0,0), null, e.get()));
                    
            }
        }

    }
}
