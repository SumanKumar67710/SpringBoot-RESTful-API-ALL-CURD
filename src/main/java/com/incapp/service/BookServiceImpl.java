package com.incapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.dao.BookRepository;
import com.incapp.model.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepo;
	
	@Override
	public List<Book> getAllBooks() {
		return bookRepo.getAllBooks();
	}

	@Override
	public Book getBook(String name) {
		return bookRepo.getBook(name);
	}

	@Override
	public List<Book> getBookLike(String name) {
		return bookRepo.getBookLike(name);
	}

	@Override
	public boolean setBook(Book book) {
		return bookRepo.setBook(book);
	}
	
	@Override
	public String updateBook(String name,Book book) {
		return bookRepo.updateBook(name,book);
	}
	
	@Override
	public boolean deleteBook(String name) {
		return bookRepo.deleteBook(name);
	}

}
