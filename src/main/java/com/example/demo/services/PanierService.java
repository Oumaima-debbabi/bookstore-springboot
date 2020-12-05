package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.PanierLineRepository;
import com.example.demo.repository.PanierRepository;
import com.example.demo.entities.LignePanier;
import com.example.demo.entities.Panier;
public class PanierService {
	 @Autowired
	 PanierLineRepository commandLineRepository;

	    @Autowired
	    PanierRepository pRepository;

	    public List<Panier> getAllCommand() {
	        return pRepository.findAll();
	    }

	    public Panier getCmdById(Long id) {
	        return pRepository.findById(id)
					 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
	       
	    }

	    public List<Panier> getCmdByCreationDate(Date creation) {
	        return pRepository.getPaniersBydateAchat(creation);
	    }

	    public Panier createCommand(Panier c) {
	        return pRepository.save(c);
	    }

	    @Transactional
	    public Panier deleteCommand(Long comId) {
	        Panier p =
	                pRepository.findById(comId)
					 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
	        commandLineRepository.deletePanierLinesByPanierId(comId);
	       pRepository.deleteById(comId);
	        return p;
	    }

	    public Optional<Panier> getUserActiveCommand(Long userId) {
	        return pRepository.findCommandByUserIdAndAndWindedUp(userId, false);
	    }

	    public List<Panier> getUserCommands(Long userId) {
	        return pRepository.getCommandsByUserId(userId);
	    }

	    public double windUpCommand(Long comId) {
	        Panier p =
	                pRepository.findById(comId)
					 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
	        if (!p.isFinale()) {
	            p.setFinale(true);
	            pRepository.save(p);
	        }
	        double total = 0;
	        List<LignePanier> commandLines =  commandLineRepository.findCommandLinesByCommandId(comId);
	        for (LignePanier commandLine: commandLines){
	            total +=commandLine.getQuantity()+commandLine.getBook().getPrice();
	        }
	        return total;
	    }
}

