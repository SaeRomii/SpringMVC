/*============================
   IGradeDAO.java
   - 인터페이스
============================*/

package com.test.mybatis;

import java.util.ArrayList;

public interface IGradeDAO
{
	// 성적 리스트 확인
	public ArrayList<GradeDTO> list();
	
	// 인원 수 확인
	public int count();
	
	// 데이터 입력
	public int add(GradeDTO g);
}
