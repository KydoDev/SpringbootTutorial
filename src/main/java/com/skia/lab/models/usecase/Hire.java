package com.skia.lab.models.usecase;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "hires")
public class Hire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hiring_date")
    private LocalDate hiringDate;

    private String position;

    @Column(name = "initial_salary")
    private double initialSalary;

    @Column(name = "contract_type")
    private String contractType;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public Hire(LocalDate hiringDate2, String position2, double initialSalary2, String contractType2,
    Employee employee2) {
}

    // Getters and Setters

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return LocalDate return the hiringDate
     */
    public LocalDate getHiringDate() {
        return hiringDate;
    }

    /**
     * @param hiringDate the hiringDate to set
     */
    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    /**
     * @return String return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return double return the initialSalary
     */
    public double getInitialSalary() {
        return initialSalary;
    }

    /**
     * @param initialSalary the initialSalary to set
     */
    public void setInitialSalary(double initialSalary) {
        this.initialSalary = initialSalary;
    }

    /**
     * @return String return the contractType
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * @param contractType the contractType to set
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * @return Employee return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
