package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Book;

public interface BookService {

	void createBook(Book book);

	List<Book> getAllBooks();

	Book findBookById(long id);

	Book update(Book book);

	void deleteBookById(long id);

	List<Book> getByUserID(Long id);

	Book findById(long id);

}
