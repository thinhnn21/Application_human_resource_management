package com.samp.customermanagement.entity;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private int age;
    private String address;
    private double salary;
    private String deleteDate;
    public Customer(int id, String name, int age, String address, double salary, String deleteDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.deleteDate = deleteDate;
    }

    public Customer(String name, int age, String address, double salary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public double getSalary() {
        return salary;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer other = (Customer) obj;
        return id == other.id &&
                age == other.age &&
                Double.compare(other.salary, salary) == 0 &&
                Objects.equals(name, other.name) &&
                Objects.equals(address, other.address) &&
                Objects.equals(deleteDate, other.deleteDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address, salary, deleteDate);
    }
}
