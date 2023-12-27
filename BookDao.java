package com.salesforce.sej;

import java.util.List;

//BookDao 작성
//insert, select all, delete, update, select one
public interface BookDao {
	public int insert(BookVo vo);
	public List<BookVo> getList();
	public int delete(int bookId);
	public int update(BookVo vo);
	public List<BookVo> search(int number, String keyword);
	public List<BookVo2> searchAll(String keyword);
}
