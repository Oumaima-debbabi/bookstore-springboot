 package com.example.demo.entities;
 
 import java.util.List;
 
 import javax.persistence.CascadeType;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 
 @Entity
 @Table(name="user")
 public class UserEntity {
 @Id
 @GeneratedValue (strategy = GenerationType.IDENTITY)
 private Long id;
 private String userName;
 private String password;
 private boolean active;
 private String roles;
 
 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 private List<Panier> listPanier;
 public Long getId() {
 	return id;
 }
 public void setId(Long id) {
 	this.id = id;
 }
 public String getUserName() {
 	return userName;
 }
 public void setUserName(String userName) {
 	this.userName = userName;
 }
 public String getPassword() {
 	return password;
 }
 public void setPassword(String password) {
 	this.password = password;
 }
 public boolean isActive() {
 	return active;
 }
 public void setActive(boolean active) {
 	this.active = active;
 }
 public String getRoles() {
 	return roles;
 }
 public void setRoles(String roles) {
 	this.roles = roles;
 }
 
 }
