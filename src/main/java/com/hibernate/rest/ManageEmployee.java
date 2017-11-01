package com.hibernate.rest;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
    private static SessionFactory factory;

    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fName, String lName, int salary) {
	try (Session session = factory.openSession()) {
	    Transaction tx = null;
	    Integer employeeID = null;
	    try {
		tx = session.beginTransaction();
		Employee employee = new Employee(fName, lName, salary);
		employeeID = (Integer) session.save(employee);
		tx.commit();
	    } catch (HibernateException e) {
		if (tx != null)
		    tx.rollback();
		e.printStackTrace();
	    }
	    return employeeID;
	}
    }

    /* Method to READ all the employees */
    public void listEmployees() {
	try (Session session = factory.openSession()) {
	    Transaction tx = null;
	    try {
		tx = session.beginTransaction();
		List<Employee> employees = session.createQuery("FROM Employee").list();
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
		    Employee employee = (Employee) iterator.next();
		    System.out.print("First Name: " + employee.getFirstName());
		    System.out.print("  Last Name: " + employee.getLastName());
		    System.out.println("  Salary: " + employee.getSalary());
		}
		tx.commit();
	    } catch (HibernateException e) {
		if (tx != null)
		    tx.rollback();
		e.printStackTrace();
	    }
	}
    }

    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer employeeId, int salary) {
	Session session = factory.openSession();
	Transaction tx = null;

	try {
	    tx = session.beginTransaction();
	    Employee employee = (Employee) session.get(Employee.class,
		    employeeId);
	    employee.setSalary(salary);
	    session.update(employee);
	    tx.commit();
	} catch (HibernateException e) {
	    if (tx != null)
		tx.rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
    }

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer employeeId) {
	try (Session session = factory.openSession()) {
	    Transaction tx = null;
	    try {
		tx = session.beginTransaction();
		Employee employee = (Employee) session.get(Employee.class,
			employeeId);
		session.delete(employee);
		tx.commit();
	    } catch (HibernateException e) {
		if (tx != null)
		    tx.rollback();
		e.printStackTrace();
	    }
	}

    }

    public static void main(String[] args) {
	try {
	    factory = new Configuration().configure().buildSessionFactory();
	} catch (Throwable ex) {
	    System.err.println("Failed to create sessionFactory object." + ex);
	    throw new ExceptionInInitializerError(ex);
	}

	ManageEmployee ME = new ManageEmployee();

	/* Add few employee records in database */
	Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
	Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
	Integer empID3 = ME.addEmployee("John", "Paul", 10000);

	/* List down all the employees */
	ME.listEmployees();

	/* Update employee's records */
	ME.updateEmployee(empID1, 5000);

	/* Delete an employee from the database */
	ME.deleteEmployee(empID2);

	/* List down new list of the employees */
	ME.listEmployees();
    }
}