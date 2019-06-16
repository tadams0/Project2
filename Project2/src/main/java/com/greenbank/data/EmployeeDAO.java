package com.greenbank.data;

import java.util.Set;

import com.greenbank.beans.Employee;

public interface EmployeeDAO {

	public void addEmployee(Employee employee);
	
	public Employee getEmployee(Employee emp);
	
	public Set<Employee> getEmployees();
	
	public void deleteEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);

	Employee getEmployeeByInfoId(int userInfoId);
}
