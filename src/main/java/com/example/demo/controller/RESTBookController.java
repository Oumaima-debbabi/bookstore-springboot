package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import com.example.demo.repository.Bookrepository;


import java.util.List;
import com.example.demo.entities.Book;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RESTBookController {
	@Autowired
	Bookrepository bkrepo;
	@GetMapping("/books")
	public List<Book>getAll(){
	    return	bkrepo.findAll();	
	}
	
	@GetMapping("/books/{id}")
	public Book bookId(@PathVariable Long id) {

		return bkrepo.findById(id).orElseThrow(()-> 
		new IllegalArgumentException("Il n'existe pas"));
		
	}
	 @PostMapping("/books/addbook")
	 public Book addbokk(@RequestBody Book book) {
		 return bkrepo.save(book);
	 }
	 @DeleteMapping("/books/delete/{id}")
	 public void deleteBook(@PathVariable long id) {
	 	bkrepo.deleteById(id);
	 }
	  
	 @PutMapping("/books/update/{id}")
	 public ResponseEntity<Object> updatebook(@RequestBody Book book, @PathVariable long id) {

			Optional<Book> bookOptional = bkrepo.findById(id);

			if (!bookOptional.isPresent())
				return ResponseEntity.notFound().build();

			book.setId(id);
			
			bkrepo.save(book);

			return ResponseEntity.noContent().build();
		}
	/* @PutMapping("/{id}")
		public Book updateBook(@RequestBody Book user, @PathVariable ("id") long id) {
		 Book existingUser = this.bkrepo.findById(id)
				 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
		 existingUser.setAuthor(user.getAuthor());
		   existingUser.setTitre(user.getTitre());
			 existingUser.setRelease(user.getReleased());
			 existingUser.setPrice(user.getPrice());
			 return this.bkrepo.save(existingUser);
		}
	 */
	 }
