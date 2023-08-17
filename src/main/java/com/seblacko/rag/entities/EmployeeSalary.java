package com.seblacko.rag.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_salary")
public class EmployeeSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "paid_at")
    private String paidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "employee_id",nullable = false)
    private Employee employee;

    public EmployeeSalary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
