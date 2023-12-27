package com.salesforce.sej;

//BookVo 작성
public class BookVo {
	private int bookId;
	private String title;
	private String pubs;
	private String pubData;
	private int authorId;
	
	public BookVo(int bookId, String title, String pubs, String pubData, int authorId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubData = pubData;
		this.authorId = authorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubData() {
		return pubData;
	}

	public void setPubData(String pubData) {
		this.pubData = pubData;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubData=" + pubData
				+ ", authorId=" + authorId + "]";
	}
	
}
