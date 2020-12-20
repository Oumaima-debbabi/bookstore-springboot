package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.LignePanier;


public interface PanierLineRepository extends JpaRepository<LignePanier, Long> {

	 public List<LignePanier> findCommandLinesByCommandId(Long comId);

	    public Optional<LignePanier> findCommandLineByCommandIdAndBookId(Long comId, Long bookId);

	    public void deleteCommandLinesByCommandId(Long id);
    
}
