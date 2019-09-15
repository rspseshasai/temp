package com.example.demo.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Model.Book;
import com.example.demo.Service.BookService;



@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/user/{userId}/books")
	public List<Book> getAllCommentsByPostId(@PathVariable(value = "userId") Long userId) {
		List<Book> books = bookService.getByUserID(userId);
		return books;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		System.out.println("Fetching book with id " + id);
		Book book = bookService.findById(id);
		if (book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Book " + book.getBookName());
		book.setCreateDate(new Date());
		book.setUpdatedDate(new Date());
		bookService.createBook(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Book> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return books;
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateBook(@RequestBody Book currentBook) {
		Book book = bookService.findById(currentBook.getId());
		if (book == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		currentBook.setUpdatedDate(new Date());
		bookService.update(currentBook);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Book> deleteBook(@PathVariable("id") long id) {
		Book book = bookService.findById(id);
		if (book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.OK);
	}

}