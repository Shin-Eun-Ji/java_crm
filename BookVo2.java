package com.salesforce.sej;

public class BookVo2 {
	private int bookId;
	private String title;
	private String pubs;
	private String pubData;
	private String authorName;
	
	public BookVo2(int bookId, String title, String pubs, String pubData, String authorName) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubData = pubData;
		this.authorName = authorName;
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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "BookVo2 [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubData=" + pubData
				+ ", authorName=" + authorName + "]";
	}
	
	
}
