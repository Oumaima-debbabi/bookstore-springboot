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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

 @Entity 
 @Table(name="LignePanier")
 public class LignePanier {
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Positive(message = "The quantity must be at least 1") @NotNull
	    private int quantity;
	    @ManyToOne
	    private Book book;
	    @ManyToOne
	    private Panier command;
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
		public Panier getCommand() {
			return command;
		}
		public void setCommand(Panier command) {
			this.command = command;
		}
	    
	    
 }