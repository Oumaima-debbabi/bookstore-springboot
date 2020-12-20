//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;


 package com.example.demo.entities;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.ManyToOne;
 
 import javax.persistence.Table;

 @Entity
 @Table(name="panier")
 public class Panier {
 

 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column( updatable = false)
 private Date dateAchat = new Date();
 private boolean finale = false;
 @ManyToOne
 private UserEntity user;
 public Long getId() {
 	return id;
 }
 
 public UserEntity getUser() {
	return user;
}

public void setUser(UserEntity user) {
	this.user = user;
}

public void setId(Long id) {
 	this.id = id;
 }
 public Date getDateAchat() {
 	return dateAchat;
 }
 public void setDateAchat(Date dateAchat) {
 	this.dateAchat = dateAchat;
 }
 public boolean isFinale() {
 	return finale;
 }
 public void setFinale(boolean finale) {
 	this.finale = finale;
 }

 
 public Panier(Long id, Date dateAchat, boolean finale) {
 	super();
 	this.id = id;
 	this.dateAchat = dateAchat;
 	this.finale = finale;
 
 }
 public Panier() {
 	
 }
 
 }