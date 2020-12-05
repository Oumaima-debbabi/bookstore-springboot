package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.repository.Bookrepository;

public class BookService {
Bookrepository bookRepository;
    public Book getBookById(Long id)  {
        return bookRepository.findById(id)
				 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
    }
}
