package com.cg.jdbc.author.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.jdbc.author.exception.AuthorException;
import com.cg.jdbc.author.model.Author;

public interface AuthorDao {
	public Author addAuthor(Author author) throws AuthorException;
	public List<Author> listAllAuthors() throws AuthorException;
	public Integer removeAuthor(BigInteger authorId) throws AuthorException;
	public Author updateAuthor(BigInteger authorId) throws AuthorException;
	public List<Author> searchAuthor(BigInteger authorId) throws AuthorException;
}
