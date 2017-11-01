package com.hibernate.rest;

import java.util.Objects;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int salary) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.salary = salary;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
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

    public int getSalary() {
	return salary;
    }

    public void setSalary(int salary) {
	this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
	if (o == null || !(o instanceof Employee)) {
	    return false;
	}
	Employee user = (Employee) o;
	return this.id == user.id
		&& this.firstName.equalsIgnoreCase(user.firstName)
		&& this.lastName.equalsIgnoreCase(user.lastName)
		&& this.salary == user.salary;
    }

    @Override
    public int hashCode() {
	return Objects
		.hash(this.id, this.firstName, this.lastName, this.salary);
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName="
		+ lastName + ", salary=" + salary + "]";
    }
}