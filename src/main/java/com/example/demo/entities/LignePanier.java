package com.example.demo.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LignePanier")
public class LignePanier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private int quantity;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Panier panier;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Panier getpanier() {
		return panier;
	}
	public void setpanier(Panier panier) {
		this.panier = panier;
	}
    
}