package com.incapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Consumer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.incapp.dao.BookRepository;
import com.incapp.model.Book;
import com.incapp.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/")
	@ResponseBody
	public String homePage() {
		return "Welcome to my RESTful service";
	}
	
	//@RequestMapping(value = "/books", method = RequestMethod.GET)
	@GetMapping(value = "/books",produces = {"application/xml","application/json"})
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping(value = "/book/{name}")
	public Book getBook(@PathVariable("name") String name) {
		return bookService.getBook(name);
	}
	
	@GetMapping(value = "/books/{name}")
	public List<Book> getBookLike(@PathVariable("name") String name) {
		return bookService.getBookLike(name);
	}
	
	@PostMapping(value = "/book",consumes = {"application/xml","application/json"})
	public String setBook(@RequestBody Book b) {
		if(bookService.setBook(b))
			return "Book successfully Added";
		else
			return "Book name already exist";
	}
	
	@PutMapping(value = "/book/{name}")
	public String updateBook(@PathVariable("name") String name, @RequestBody Book b) {
		return bookService.updateBook(name,b);
	}
	
	@DeleteMapping(value = "/book/{name}")
	public String deleteBook(@PathVariable("name") String name) {
		if(bookService.deleteBook(name))
			return "Book successfully Deleted";
		else
			return "No Book found";
	}
}
