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

        long eIdx = 0;

        if(departmentRepository.count() != 0) departmentRepository.deleteAll();
        if(departmentRepository.count() == 0){
            for (int i = 0; i < 5; i++) {
                var d = new Department(faker.animal().name());
                d.setId((long)i);
                departmentRepository.save(d);
            }
        }


        if(employeeRepository.count() != 0) employeeRepository.deleteAll();

        Employee[] employees = new Employee[40];
        if(employeeRepository.count() == 0){
            

            for (int i = 0; i < 40; i++) {
                employees[i] = new Employee();
                employees[i].setFirstName(faker.name().firstName());
                employees[i].setLastName(faker.name().lastName());
                employees[i].setBirthDate(LocalDate.now());
                employees[i].setAddress(faker.address().fullAddress());
                employees[i].setPhoneNumber(faker.name().fullName());
                employees[i].setEmail(faker.internet().emailAddress());
                long a = 4;
                if(i>10 && i<20) a = 1;
                if(i>20 && i<30) a = 2;
                if(i>30 && i<40) a = 3;
                if(departmentRepository.existsById(a))
                employees[i].setDepartment(departmentRepository.findById(a).get());

                if(i>10) employees[i].setManager(employees[10]);
                if(i<10 && i >0) employees[i].setManager(employees[0]);
                
                // employee.setDepartment(i<10 ? department1 : department2);
                employeeRepository.save(employees[i]);
                if(i == 0) eIdx = employees[i].getId();
            }
        }
        if(hireRepository.count() != 0) hireRepository.deleteAll();
        if(hireRepository.count() == 0){
            
            var e0 =employeeRepository.findById((long)eIdx);
            Hire hire0 = new Hire(LocalDate.now(), faker.name().title(), 50000, "commercio", e0.get());
            hireRepository.save(hire0);
            for (int i = 1; i < 20; i++) {
                var e =employeeRepository.findById((long)employees[i].getId());
                Hire hire = new Hire(LocalDate.now(), "Sales", 30000, "commercio", e.get());

                hireRepository.save(hire);
            }

        }

        if(attendanceRepository.count() != 0) attendanceRepository.deleteAll();
        if(attendanceRepository.count() == 0){
            
            var e0 =employeeRepository.findById((long)eIdx);
            Attendance att0 = new Attendance(LocalDate.of(2023,2,1), LocalTime.of(8,0,0),LocalTime.of(18,0,0), null, e0.get());
            attendanceRepository.save(att0);

            for (int i = 1; i < 28; i++){
                
                Attendance attX = new Attendance(LocalDate.of(2023,1,i), LocalTime.of(8,0,0),LocalTime.of(18,0,0), null , e0.get());
                    attX.setAbsenceType(AbsenceType.sickness.ordinal());
                attendanceRepository.save(attX);
            }

            for (int j = 1; j < employeeRepository.count(); j++) {
                var e =employeeRepository.findById((long)employees[j].getId());

                for (int i = 1; i < 28; i++) { for (int k = 1; k < 12; k++) attendanceRepository.save( new Attendance(LocalDate.of(2023,k,i), LocalTime.of(8,0,0),LocalTime.of(18,0,0), null, e.get()));    }
                    
            }
        }

    }
}
