package com.incapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.incapp.model.Book;

@Repository
public class BookRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//insert new book
	public boolean setBook(Book b) {
		try {
        	jdbcTemplate.update(
            		"INSERT INTO books(name, price,aname,pname) VALUES (?,?,?,?)",
                    b.getName(), b.getPrice(),b.getAname(),b.getPname());
        	return true;
        }catch(DuplicateKeyException ex) {
            return false;
        }
    } 
	//get book by name like
	public List<Book>  getBookLike(String bookName) {
		class BookMapper implements RowMapper{
			public Book mapRow(ResultSet rs,int rowNum)throws SQLException{
				Book b=new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		try {
			final String query ="select * from books where name like '%"+bookName+"%'";
			List<Book>  b=jdbcTemplate.query(query,new BookMapper());
			if(b.isEmpty())
				return null;
			return b;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
    }
	//delete book by name
	public boolean deleteBook(String bn){
		String query ="delete from books where name = ?";
		int x=jdbcTemplate.update( query,new Object[]{bn});
		if(x!=0)
			return true;
		else
			return false;
	}
	//update book
	public String updateBook(String bn,Book b){
		try {
			String query ="update books set name=?, price=?, aname=?, pname=? where name = ?";
			int x=jdbcTemplate.update( query,new Object[]{b.getName(),b.getPrice(),b.getAname(),b.getPname(),bn});
			if(x!=0)
				return "Book Successfully Updated!";
			else
				return "No Book found to update!";
		}catch(DuplicateKeyException ex) {
			return "Book name is already exist!";
		}
	}
	//get book by name
	public Book getBook(final String bn) {
		class BookMapper implements RowMapper{
			public Book mapRow(ResultSet rs,int rowNum)throws SQLException{
				Book b=new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		try {
			final String query ="select * from books where name = ?";
			Book b=(Book)jdbcTemplate.queryForObject(
					query,new Object[]{bn},new BookMapper());
			return b;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	//get All book 
	public List<Book> getAllBooks() {
		class BookMapper implements RowMapper{
			public Book mapRow(ResultSet rs,int rowNum)throws SQLException {
				Book b=new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		try {
			final String query ="select * from books";
			List<Book> b=jdbcTemplate.query(query,new BookMapper());
			return b;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
    }
}
