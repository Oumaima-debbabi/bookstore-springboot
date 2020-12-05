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
public UserEntity getUser() {
	return user;
}
public void setUser(UserEntity user) {
	this.user = user;
}
public Panier(Long id, Date dateAchat, boolean finale, UserEntity user) {
	super();
	this.id = id;
	this.dateAchat = dateAchat;
	this.finale = finale;
	this.user = user;
}
public Panier() {
	
}

}