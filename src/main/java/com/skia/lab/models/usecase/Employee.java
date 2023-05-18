package com.skia.lab.models.usecase;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    
    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;
    
    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = dateOfBirth;
        this.email = email;
    }

    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String address,
                    String phoneNumber, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

// Getters and Setters
    
public Employee() {
    }

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public LocalDate getBirthDate() {
    return birthDate;
}

public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public Department getDepartment() {
    return department;
}

public void setDepartment(Department department) {
    this.department = department;
}

public Employee getManager() {
    return manager;
}

public void setManager(Employee manager) {
    this.manager = manager;
}
}
