package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Book;
import com.example.demo.entities.LignePanier;
import com.example.demo.entities.Panier;
import com.example.demo.repository.PanierLineRepository;

public class LinePanier {
  BookService bookservice;
 
    @Autowired
    PanierLineRepository plRepository;

    @Autowired
    MyUserDetailsService userService;

    @Autowired
    PanierService commandService;

    public List<LignePanier> getAllCommandLines(){
        return plRepository.findAll();
    }

    public LignePanier getCommandLineById(Long id) {
        return plRepository.findById(id)
				 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
        }


    public LignePanier addCommandLine(Long bookId,  LignePanier commandLine,
                                      Long userId) throws Exception {
        Book b = bookservice.getBookById(bookId);
        Optional<Panier> c = commandService.getUserActiveCommand(userId);
        // if the user still has a command which is not winded up
        if (c.isPresent()){
            commandLine.setpanier(c.get());
            System.err.println(true);
            //test if the command already has a command line containing the book id
            if (plRepository.findCommandLineByCommandIdAndBookId(c.get().getId(), bookId).isPresent())
                throw new Exception("You have already commanded this book");
            }

        else {
            System.err.println(false);
            Panier cc =new Panier();
            cc.setUser(userService.getUserById(userId));
            commandLine.setpanier(commandService.createCommand(cc));
        }
        commandLine.setBook(b);
        return plRepository.save(commandLine);
    }

    public void deleteCommandLine(Long clId) throws Exception {
    	LignePanier c = getCommandLineById(clId);
        if (commandService.getCmdById(c.getpanier().getId()).isFinale()) {
            throw new Exception("This command is " +
                    "winded you can't modify its lines");
        }
        if (plRepository.findCommandLinesByCommandId(c.getpanier().getId()).size()==1)
        	commandService.deleteCommand(c.getpanier().getId());
        plRepository.deleteById(clId);
    }

    public LignePanier updateCom(Long coId, Long bId, LignePanier c) throws Exception {
        if (commandService.getCmdById(coId).isFinale()) {
            throw new Exception("This panier is " +
                    "final you can't modify its lines");
        }
        LignePanier lp = plRepository.findCommandLineByCommandIdAndBookId(coId, bId).get();
        lp.setQuantity(c.getQuantity());
        return plRepository.save(lp);
    }
}
