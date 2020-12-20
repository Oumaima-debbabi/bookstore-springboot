package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Panier;
public interface PanierRepository  extends JpaRepository<Panier, Long>{
	  public List<Panier> getPaniersBydateAchat(Date dateAchat);

	    public Optional<Panier> findCommandByUserIdAndAndFinale(Long userId, boolean finale);

	    public List<Panier> getCommandsByUserId(Long userId);
}