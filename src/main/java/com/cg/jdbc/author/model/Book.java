package com.cg.jdbc.author.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Book {
	
	private BigInteger bookISBN;
	private String bookName;
	private BigDecimal bookPrice;
	private BigInteger authorId;
	

	public Book() {
		// TODO Auto-generated constructor stub
	}


	public Book(BigInteger bookISBN, String bookName, BigDecimal bookPrice, BigInteger authorId) {
		super();
		this.bookISBN = bookISBN;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.authorId = authorId;
	}


	public BigInteger getBookISBN() {
		return bookISBN;
	}


	public void setBookISBN(BigInteger bookISBN) {
		this.bookISBN = bookISBN;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public BigDecimal getBookPrice() {
		return bookPrice;
	}


	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}


	public BigInteger getAuthorId() {
		return authorId;
	}


	public void setAuthorId(BigInteger authorId) {
		this.authorId = authorId;
	}


	@Override
	public int hashCode() {
		return this.bookISBN.intValue();
	}


	@Override
	public boolean equals(Object obj) {
		return this.hashCode()==obj.hashCode();
	}


	@Override
	public String toString() {
		return "Book [bookISBN=" + bookISBN + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", authorId="
				+ authorId + "]";
	}
	
	
}
