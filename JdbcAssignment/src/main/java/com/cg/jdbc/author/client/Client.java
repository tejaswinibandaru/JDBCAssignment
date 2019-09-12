package com.cg.jdbc.author.client;

import java.util.Scanner;

import com.cg.jdbc.author.dao.AuthorDao;
import com.cg.jdbc.author.dao.AuthorDaoImpl;
import com.cg.jdbc.author.exception.AuthorException;
import com.cg.jdbc.author.model.Author;

public class Client {
	
	private static AuthorDao authorDao;
	
	static {
		authorDao=new AuthorDaoImpl();
	}

	public static void main(String[] args) throws AuthorException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		
		Author author=new Author();
		String firstName=scanner.next();
		String middleName=scanner.next();
		String lastName=scanner.next();
		String phoneNo=scanner.next();
		
		author.setFirstName(firstName);
		author.setMiddleName(middleName);
		author.setLastName(lastName);
		author.setPhoneNo(phoneNo);
		
		Author authorObj=authorDao.addAuthor(author);
		System.out.println("Author added to database!!!");
		

	}

}
