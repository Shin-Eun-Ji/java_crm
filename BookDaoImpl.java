package com.salesforce.sej;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");
			System.out.println("접속성공");
		
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		return conn;
	}

	@Override
	public int insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO book(book_id, title, pubs, pub_data, author_id) " +
					       "VALUES ( seq_book_id.nextval, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPubData());
			pstmt.setInt(4, vo.getAuthorId());
			
			count = pstmt.executeUpdate();

			
			// 4.결과처리
			System.out.println(vo.toString());
			System.out.println(count + "건 처리");
			
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
		return count;
	}

	@Override
	public List<BookVo> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> bookList = new ArrayList<BookVo>();
		int count = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "Select * FROM book";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4.결과처리			
			while(rs.next()) {
				count++;
				BookVo book = new BookVo(rs.getInt(1), 
										  rs.getString(2), 
										  rs.getString(3),
										  rs.getString(4),
										  rs.getInt(5));
				bookList.add(book);
			}
			System.out.println(count + "건 처리");
			System.out.println(bookList);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
		return bookList;
	}

	@Override
	public int delete(int bookId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; 
			query = " DELETE FROM book a WHERE a.book_id = ? ";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, bookId);
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(bookId + "번 삭제");
			System.out.println(count + "건 처리");
			
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
		return count;
	}

	@Override
	public int update(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; 
			query = " UPDATE book a         \n" + 
					" SET a.title = ?       \n" + 
					"   , a.pub_data = ?    \n" + 
					" WHERE a.book_id = ?   ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubData());
			pstmt.setInt(3, vo.getBookId());
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(vo.toString());
			System.out.println(count + "건 처리");
			
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
		return count;
	}

	@Override
	public List<BookVo> search(int number, String keyword) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> bookList = new ArrayList<BookVo>();
		int count = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			
			if (number == 1) {
				query += "Select * FROM book WHERE title like ? ";
			} else if (number == 2) {
				query += "Select a.* FROM book a, author b WHERE a.author_id = b.author_id and b.author_name like ? ";
			} else if (number == 3) {
				query += "Select * FROM book WHERE pubs like ? ";
			}
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리			
			while(rs.next()) {
				count++;
				BookVo book = new BookVo(rs.getInt(1), 
										  rs.getString(2), 
										  rs.getString(3),
										  rs.getString(4),
										  rs.getInt(5));
				bookList.add(book);
			}
			System.out.println(count + "건 처리");
			System.out.println(bookList);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
		return bookList;
	}

	@Override
	public List<BookVo2> searchAll(String keyword) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo2> bookList = new ArrayList<BookVo2>();
		int count = 0;
		
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "SELECT a.BOOK_ID, a.TITLE , a.PUBS , a.PUB_DATA , b.AUTHOR_NAME FROM BOOK a "
					+ "INNER JOIN AUTHOR b "
					+ "on a.AUTHOR_ID = b.AUTHOR_ID "
					+ "where (a.title like ? "
					+ "      OR a.pubs like ? "
					+ "      OR b.AUTHOR_NAME like ? )";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리			
			while(rs.next()) {
				count++;
				BookVo2 book = new BookVo2(rs.getInt(1), 
										  rs.getString(2), 
										  rs.getString(3),
										  rs.getString(4),
										  rs.getString(5));
				bookList.add(book);
			}
			System.out.println(count + "건 처리");
			System.out.println(bookList);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
		return bookList;
	}

}
