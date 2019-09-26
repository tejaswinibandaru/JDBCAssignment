package com.cg.jdbc.author.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.jdbc.author.exception.AuthorException;
import com.cg.jdbc.author.exception.BookException;
import com.cg.jdbc.author.model.Author;
import com.cg.jdbc.author.model.Book;
import com.cg.jdbc.author.util.DBUtil;

public class BookDaoImpl implements BookDao {
	
	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static Logger logger;
	
	static {
		Properties properties=System.getProperties();
		String userDir= properties.getProperty("user.dir")+"\\src\\main\\resources\\";
		System.out.println("Current working directory: "+userDir);
		PropertyConfigurator.configure(userDir+"log4j.properties");
		logger=Logger.getLogger("BookDaoImpl.class");
	}
	
	static {
		try {
			connection=DBUtil.getConnection();
			logger.info("Connection established!!! ");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Connection unable to estalish: "+e.getMessage());
		}
	}

	@Override
	public Book addBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		String sql="insert into book(book_name,book_price,author_id) values(?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setBigDecimal(2, book.getBookPrice());
			preparedStatement.setString(1, book.getBookName());
			preparedStatement.setLong(3, book.getAuthorId().longValue());
			
			int recordsCount=preparedStatement.executeUpdate();
			logger.info(recordsCount+" rows inserted");
			
			BigInteger generatedId=BigInteger.valueOf(0L);
			resultSet=preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				generatedId=BigInteger.valueOf(resultSet.getLong(1001));
				logger.info("Auto generated id: "+generatedId);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			logger.error("Error generated at addBook method in BookDao: "+e.getMessage());
		}
		finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at addBook method in BookDao: "+e.getMessage());
				}
			}
		}
		return book;
	}

	@Override
	public List<Book> listAllBooks() throws BookException {
		// TODO Auto-generated method stub
		String sql="select * from book";
		List<Book> booksList=new ArrayList<Book>();
		try {
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Book book=new Book();
	
				book.setBookISBN(BigInteger.valueOf(resultSet.getLong("book_isbn")));
				book.setBookName(resultSet.getString("book_name"));
				book.setBookPrice(resultSet.getBigDecimal("book_price"));
				book.setAuthorId(BigInteger.valueOf(resultSet.getLong("author_id")));
				
				booksList.add(book);
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error generated at listAllBooks method in BookDao: "+e.getMessage());
		}
		finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at listAllBooks method in BookDao: "+e.getMessage());
				}
			}
		}
		return booksList;
	}

	@Override
	public Integer removeBook(BigInteger bookId) throws BookException {
		// TODO Auto-generated method stub
		String sql="delete from book where book_isbn=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookId.longValue());
			
			int recordsCount=preparedStatement.executeUpdate();
			logger.info(recordsCount+" rows deleted");
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error generated at deleteBook method in BookDao: "+e.getMessage());
		}
		finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at deleteBook method in BookDao: "+e.getMessage());
				}
			}
		}
		return 1;

	}

	@Override
	public Book updateBook(BigInteger bookId) throws BookException {
		// TODO Auto-generated method stub
		List<Book> books=listAllBooks();
		Book bookObj=new Book();
		for(Book book:books) {
			if(book.getBookISBN().equals(bookId)) {
				bookObj=book;
			}
		}
		
		String sql="update book set book_price=? where book_isbn=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, bookObj.getBookPrice());
			preparedStatement.setLong(2, bookObj.getBookISBN().longValue());
			
			int recordsCount=preparedStatement.executeUpdate();
			logger.info(recordsCount+" rows updated"); 
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error generated at updateBook method in BookDao: "+e.getMessage());
		}
		finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at updateBook method in BookDao: "+e.getMessage());
				}
			}
		}
		// TODO Auto-generated method stub
		return bookObj;
	}

	@Override
	public List<Book> searchBook(BigInteger bookId) throws BookException {
		// TODO Auto-generated method stub
		String sql="select * from book where book_isbn=?";
		List<Book> books=new ArrayList<Book>();
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookId.longValue());
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Book book=new Book();
				book.setBookISBN(BigInteger.valueOf(resultSet.getLong("book_isbn")));
				book.setBookName(resultSet.getString("book_name"));
				book.setBookPrice(resultSet.getBigDecimal("book_price"));
				book.setAuthorId(BigInteger.valueOf(resultSet.getLong("author_id")));
				
				books.add(book);
			}
		}catch (Exception e) {
			// TODO: handle exception

			logger.error("Error generated at searchBook method in BookDao: "+e.getMessage());
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at searchBook method in BookDao: "+e.getMessage());
				}
			}
		}
		return books;
	}
	
	public List<Book> updateByName(String name) throws BookException{
		List<Book> books = null;
		String sql="update book join author on book.author_id=author.author_id set book_price=500.0 where author.first_name=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			int recordsCount=preparedStatement.executeUpdate();
			
			sql="select * from book join author on book.author_id=author.author_id where author.first_name=?";
			books=new ArrayList<Book>();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Book book=new Book();
	
				book.setBookISBN(BigInteger.valueOf(resultSet.getLong("book_isbn")));
				book.setBookName(resultSet.getString("book_name"));
				book.setBookPrice(resultSet.getBigDecimal("book_price"));
				book.setAuthorId(BigInteger.valueOf(resultSet.getLong("author_id")));
				
				books.add(book);
			}

			recordsCount=preparedStatement.executeUpdate();
			logger.info(recordsCount+" rows updated");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error generated at updateByName method in BookDao: "+e.getMessage());
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					logger.error("Error generated at updateByName method in BookDao: "+e.getMessage());
				}
			}
		}

		return books;
	}
	
	public List<String> viewBooksByAuthor(String bookName){
		List<String> booksName=new ArrayList<String>();
		String sql="select book.book_name from book join author on book.author_id=author.author_id where author_name=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bookName);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String book=resultSet.getString("book_name");
				booksName.add(book);
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error generated at viewBooksByAuthor method in BookDao: "+e.getMessage());
		}
		finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at viewBooksByAuthor method in BookDao: "+e.getMessage());
				}
			}
		}
		return booksName;
	}
	
	public List<Book> viewBookByAuthor(BigInteger authorId) throws AuthorException{
		String sql="select book_name from book where author_id=?";
		List<Book> books=new ArrayList<Book>();
		
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1,authorId.longValue());
			
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Book book=new Book();
				book.setBookISBN(BigInteger.valueOf(resultSet.getLong("book_isbn")));
				book.setBookName(resultSet.getString("book_name"));
				book.setBookPrice(resultSet.getBigDecimal("book_price"));
				book.setAuthorId(BigInteger.valueOf(resultSet.getLong("author_id")));
				
				books.add(book);
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error generated at viewBookByAuthor method in BookDao: "+e.getMessage());
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at viewBookByAuthor method in BookDao: "+e.getMessage());
				}
			}
		}
		return books;
	}

}
