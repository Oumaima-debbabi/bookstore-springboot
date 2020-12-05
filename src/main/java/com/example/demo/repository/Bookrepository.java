package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Book;
public interface Bookrepository  extends JpaRepository<Book, Long>{

	
}
