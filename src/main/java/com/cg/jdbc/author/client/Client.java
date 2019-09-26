package com.cg.jdbc.author.client;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import com.cg.jdbc.author.dao.AuthorDao;
import com.cg.jdbc.author.dao.AuthorDaoImpl;
import com.cg.jdbc.author.dao.BookDao;
import com.cg.jdbc.author.dao.BookDaoImpl;
import com.cg.jdbc.author.exception.AuthorException;
import com.cg.jdbc.author.exception.BookException;
import com.cg.jdbc.author.model.Author;
import com.cg.jdbc.author.model.Book;

public class Client {
	
	private static AuthorDao authorDao;
	private static BookDao bookDao;
	
	static {
		authorDao=new AuthorDaoImpl();
		bookDao=new BookDaoImpl();
	}

	public static void main(String[] args) throws AuthorException {
		
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("1.Add author");
			System.out.println("2.Delete author");
			System.out.println("3.Update author");
			System.out.println("4.Search author");
			System.out.println("5.List all authors");
			System.out.println("6.View books by specific author");
			System.out.println("7.Update book details based on author name");
			System.out.println("Enter your choice: ");
			int choice=scanner.nextInt();
			switch (choice) {
			case 1:
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
				break;
				
			case 2:
				System.out.println("Enter the author id to remove");
				BigInteger authorId=scanner.nextBigInteger();
				
				if(authorDao.removeAuthor(authorId)==1) {
					System.out.println("Deleted successfully");
				}
				break;
				
			case 3:
				System.out.println("Enter the author id to update");
				authorId=scanner.nextBigInteger();
				System.out.println(authorDao.updateAuthor(authorId));
				break;
				
			case 4:
				System.out.println("Enter the author id to search");
				authorId=scanner.nextBigInteger();
				System.out.println(authorDao.searchAuthor(authorId));
				break;
				
			case 5:
				for(Author authorOb:authorDao.listAllAuthors()) {
					System.out.println(authorOb);
				}
				break;
			case 6:
				System.out.println("Enter the author name: ");
				String authorName=scanner.next();
				List<String> books=bookDao.viewBooksByAuthor(authorName);
				for(String book:books) {
					System.out.println(book);
				}
				break;
			case 7:
				System.out.println("Enter the author name: ");
				authorName=scanner.next();
				List<Book> bookDetails;
				try {
					bookDetails = bookDao.updateByName(authorName);
					for(Book bookObject:bookDetails) {
						System.out.println(bookObject);
					}
				} catch (BookException e) {
					// TODO Auto-generated catch block
					System.out.println("Exception:"+e.getMessage());
				}
				
				break;
			default:System.out.println("Invalid choice");
				break;
			}
		}
	
	}

}
