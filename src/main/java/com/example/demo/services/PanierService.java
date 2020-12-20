 package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
 import java.util.Date;
 import java.util.List;
 import java.util.Optional;
 
 import javax.transaction.Transactional;
 
 import org.springframework.beans.factory.annotation.Autowired;
 
import com.example.demo.repository.PanierLineRepository;
import com.example.demo.repository.PanierRepository;
import com.example.demo.entities.LignePanier;
import com.example.demo.entities.Panier;
@Service
 public class PanierService {

    @Autowired
 PanierLineRepository commandLineRepository;

    @Autowired
   PanierRepository commandRepository;

    public List<Panier> getAllCommand() {
        return commandRepository.findAll();
    }

    public Panier getCmdById(Long id) {
        return commandRepository.findById(id).orElseThrow(()-> 
		new IllegalArgumentException("Il n'existe pas"));
    }

    public List<Panier> getCmdByCreationDate(Date creation) {
        return commandRepository.getPaniersBydateAchat(creation);
    }
    public List<Panier> getUserCommands(Long userId) {
        return commandRepository.getCommandsByUserId(userId);
    }
    public Panier createCommand(Panier c) {
        return commandRepository.save(c);
    }
    public Optional<Panier> getUserActiveCommand(Long userId) {
        return commandRepository.findCommandByUserIdAndAndFinale(userId, false);
    }
    @Transactional
    public Panier deleteCommand(Long comId) {
    	Panier c =
                commandRepository.findById(comId).orElseThrow(()-> 
        		new IllegalArgumentException("Il n'existe pas"));
        commandLineRepository.deleteCommandLinesByCommandId(comId);
        commandRepository.deleteById(comId);
        return c;
    }


    public double windUpCommand(Long comId) {
    	Panier c =
                commandRepository.findById(comId).orElseThrow(()-> 
        		new IllegalArgumentException("Il n'existe pas"));
        if (!c.isFinale()) {
            c.setFinale(true);
            commandRepository.save(c);
        }
        double total = 0;
        List<LignePanier> commandLines =  commandLineRepository.findCommandLinesByCommandId(comId);
        for (LignePanier commandLine: commandLines){
            total +=commandLine.getQuantity()+commandLine.getBook().getPrice();
        }
        return total;
    }

}
