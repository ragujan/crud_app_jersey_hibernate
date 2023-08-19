package com.seblacko.rag.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "hire_date")
    private String hiredDate;


//    @Column(name = "employee_position_id")
//    private Integer employeePositionId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee",cascade = CascadeType.REMOVE)
    private Set<EmployeeSalary> employeeSalary = new HashSet<>();
    //department entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    //employee position entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_position_id")
    private EmployeePosition employeePosition;

    public Employee() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }

    public Set<EmployeeSalary> getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Set<EmployeeSalary> employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

//    public Integer getEmployeePositionId() {
//        return employeePositionId;
//    }
//
//    public void setEmployeePositionId(Integer employeePositionId) {
//        this.employeePositionId = employeePositionId;
//    }
}
