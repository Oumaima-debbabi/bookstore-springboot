package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Book {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY )
	    private Long id;
	    private String titre;
	    private String author;
	    private double price;
	    private String released;
	    public Book() {
	    	super();
	    }
	   
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getReleased() {
			return released;
		}
		public void setRelease(String released) {
			this.released = released;
		}
	    
}
