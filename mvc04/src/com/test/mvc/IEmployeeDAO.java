/*======================================
   #05. IEmployeeDAO.java
   - 인터페이스
======================================*/

package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEmployeeDAO
{
	public ArrayList<Employee> list() throws SQLException;
	public ArrayList<Region> regionList() throws SQLException;
	public ArrayList<Department> departmentList() throws SQLException;
	public ArrayList<Position> positionList() throws SQLException;
	
	// 직위에 따른 최소 기본급이라서 직위 매개변수로 받아야함
	public int getMinBasicPay(String positionId) throws SQLException;
	
	// DTO 개념으로 Employee 만든거 
	public int employeeAdd(Employee employee) throws SQLException;
	

	public int remove(String employeeId) throws SQLException;
	
	// 수정은 아이디가 아니라 Employee
	public int modify(Employee employee) throws SQLException;
	
	// 아이디 넘겼을 때 한사람 찾는거라 Employee 반환
	public Employee searchId(String employeeId) throws SQLException;
	
	// 로그인 성공하면 아이디 패스워드
	public String login(String id, String pw) throws SQLException;
	public String loginAdmin(String id, String pw) throws SQLException;
	
	
}
