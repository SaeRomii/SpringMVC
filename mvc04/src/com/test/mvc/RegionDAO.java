/*===================================================
	#11. EmployeeDAO.java
	- 데이터베이스 액션 처리 클래스
	- 지역 데이터 입력 / 출력 / 수정 / 삭제 액션
	- Connection 객체에 대한 의존성 주입을 위한 준비
	  → setter injection
	     → ·인터페이스 형태의 자료형 구성
	        ·setter 메소드 정의
====================================================*/

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class RegionDAO implements IRegionDAO
{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	// 지역 전체 출력
	@Override
	public ArrayList<Region> list() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW ORDER BY REGIONID";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Region region = null;
		
		while (rs.next())
		{
			region = new Region();
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return result;
	}

	// 지역 데이터 추가
	@Override
	public int add(Region region) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "INSERT INTO REGION(REGIONID, REGIONNAME) VALUES(REGIONSEQ.NEXTVAL, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, region.getRegionName());
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 지역 데이터 삭제
	@Override
	public int remove(String regionId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM REGION WHERE REGIONID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(regionId));
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 지역 데이터 수정
	@Override
	public int modify(Region region) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE REGION SET REGIONNAME=? WHERE REGIONID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, region.getRegionName());
		pstmt.setInt(2, Integer.parseInt(region.getRegionId()));
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

}
