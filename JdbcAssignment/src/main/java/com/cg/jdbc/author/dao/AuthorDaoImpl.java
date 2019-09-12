package com.cg.jdbc.author.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.jdbc.author.exception.AuthorException;
import com.cg.jdbc.author.model.Author;
import com.cg.jdbc.author.util.DBUtil;

public class AuthorDaoImpl implements AuthorDao {

	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static Logger logger;
	
	static {
		Properties properties=System.getProperties();
		String userDir= properties.getProperty("user.dir")+"\\src\\main\resources\\";
		System.out.println("Current working directory: "+userDir);
		PropertyConfigurator.configure(userDir+"log4j.properties");
		logger=Logger.getLogger("DBUtil.class");
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
	public Author addAuthor(Author author) throws AuthorException {
		// TODO Auto-generated method stub
		String sql="insert into author(first_name,middle_name,last_name,phone_no) values(?,?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, author.getFirstName());
			preparedStatement.setString(2, author.getMiddleName());
			preparedStatement.setString(3, author.getLastName());
			preparedStatement.setString(4, author.getPhoneNo());
			
			int recordsCount=preparedStatement.executeUpdate();
			logger.info(recordsCount+" rows inserted");
			
			BigInteger generatedId=BigInteger.valueOf(1000L);
			resultSet=preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				generatedId=BigInteger.valueOf(resultSet.getLong(1));
				logger.info("Auto generated id: "+generatedId);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			logger.error("Error generated at addAuthor method in AuthorDao: "+e.getMessage());
		}
		finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				}catch (SQLException e) {
					// TODO: handle exception
					logger.error("Error generated at addAuthor method in AuthorDao: "+e.getMessage());
				}
			}
		}
		return author;
	}

	@Override
	public List<Author> listAllAuthors() throws AuthorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer removeAuthor(BigInteger authorId) throws AuthorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(BigInteger authorId) throws AuthorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author searchAuthor(BigInteger authorId) throws AuthorException {
		// TODO Auto-generated method stub
		return null;
	}

}
