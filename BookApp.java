package com.salesforce.sej;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
//		int count = 0;
		BookDao dao = new BookDaoImpl();
//		List<BookVo> list = new ArrayList<BookVo>();

		// insert
//		BookVo book = new BookVo(1, "흔한남매 15", "미래엔아이세움", "2023-12-14", 15);
//		count = dao.insert(book);
//		System.out.println("BookApp.dao.insert(vo) 결과 ---> " + count);

		// select all
//		list = dao.getList();
//		for(BookVo bookVo : list) {
//			System.out.println(bookVo);
//		}
//		System.out.println("BookApp.dao.getList() 결과 ---> " + list.size());

		// update
//		BookVo book = new BookVo(13, "흔한남매 14", "미래엔아이세움", "2023-09-21", 15);
//		count = dao.update(book);
//		System.out.println("BookApp.dao.update(vo) 결과 ---> " + count);

		// delete
//		Integer bookId = 14;
//		count = dao.delete(bookId);
//		System.out.println("BookApp.dao.delete(vo) 결과 ---> " + count);

		// search
		/*
		 * System.out.println("옵션을 선택하세요.");
		 * System.out.print("1. 제목, 2. 저자명, 3. 출판사>>"); Scanner sc = new
		 * Scanner(System.in);
		 * 
		 * int number = sc.nextInt(); String keyword = null;
		 * 
		 * if (number == 1) { System.out.print("제목을 입력하세요>>"); keyword = sc.next(); }
		 * else if (number == 2) { System.out.print("저자명을 입력하세요>>"); keyword =
		 * sc.next(); } else if (number == 3) { System.out.print("출판사를 입력하세요>>");
		 * keyword = sc.next(); } else { System.out.print("잘못 입력했어요."); }
		 * 
		 * list = dao.search(number, keyword); for (BookVo bookVo : list) {
		 * System.out.println(bookVo); }
		 * System.out.println("BookApp.dao.search() 결과 ---> " + list.size());
		 * 
		 * sc.close();
		 */
		
		
		// search 문제
		System.out.println("키워드를 입력하세요>>");
		Scanner sc = new Scanner(System.in);
		List<BookVo2> list2 = new ArrayList<BookVo2>();
		
		String keyword = sc.next();

		list2 = dao.searchAll(keyword);
		for (BookVo2 bookVo : list2) {
			System.out.println(bookVo);
		}
		System.out.println("BookApp.dao.search() 결과 ---> " + list2.size());
	
		sc.close();
	}

}
