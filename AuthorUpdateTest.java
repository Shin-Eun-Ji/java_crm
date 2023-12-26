package com.javaex.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdateTest {

	public static void main(String[] args) {


		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = "";
//			sql =   " UPDATE AUTHOR a       \n" + 
//					" SET a.AUTHOR_DESC = ? \n" + 
//					" WHERE a.AUTHOR_ID = ?   ";
			
//			sql =   " Insert into AUTHOR (author_name, author_desc, author_id)       \n" + 
//					" values( ?, ?, seq_author_id.nextval ) \n";			
			
			sql = " delete from author where author_id = ? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(sql);
//          update
//			pstmt.setString(1, "웹툰작가");
//			pstmt.setInt(2, 7);

//			insert
//			pstmt.setString(1, "박태준");
//			pstmt.setString(2, "김부장");

//			delete
			pstmt.setInt(1, 7);
			
			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.print(count + "건이 반영 완료 되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

}
