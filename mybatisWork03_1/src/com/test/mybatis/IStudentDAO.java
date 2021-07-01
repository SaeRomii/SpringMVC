/*============================
   IStudentDAO.java
   - 인터페이스
============================*/

package com.test.mybatis;

import java.util.ArrayList;

public interface IStudentDAO
{
	// 학생 인원 수 확인
	public ArrayList<StudentDTO> list();
	
	// 인원 수 확인
	public int count();
	
	// 데이터 추가
	public int add(StudentDTO student);
	
	// 데이터 확인(sid검색)
	public StudentDTO search(String sid);

}
