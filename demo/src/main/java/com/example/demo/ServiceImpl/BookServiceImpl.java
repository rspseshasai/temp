package com.example.demo.ServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;



@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public void createBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book findById(long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public Book update(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBookById(long id) {
		bookRepository.deleteById( id);
	}

	@Override
	public List<Book> getByUserID(Long id) {
		return bookRepository.findByUserId(id);
	}

	@Override
	public Book findBookById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}