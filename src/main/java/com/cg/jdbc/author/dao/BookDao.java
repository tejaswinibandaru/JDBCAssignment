package com.cg.jdbc.author.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.jdbc.author.exception.BookException;
import com.cg.jdbc.author.model.Book;


public interface BookDao {
	
	public Book addBook(Book book) throws BookException;
	public List<Book> listAllBooks() throws BookException;
	public Integer removeBook(BigInteger bookId) throws BookException;
	public Book updateBook(BigInteger bookId) throws BookException;
	public List<Book> searchBook(BigInteger bookId) throws BookException;
	public List<String> viewBooksByAuthor(String bookName);
	public List<Book> updateByName(String name) throws BookException;

}
