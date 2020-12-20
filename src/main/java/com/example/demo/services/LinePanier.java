 package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.LignePanier;
import com.example.demo.entities.Panier;
import com.example.demo.entities.Book;
import com.example.demo.repository.PanierLineRepository;
import com.example.demo.repository.PanierRepository;


@Service
public class LinePanier {

	  @Autowired
	  BookService bookService;
	  
	    @Autowired
	    PanierLineRepository commandLineRepository;

	    @Autowired
	    MyUserService userService;


	    @Autowired
	    PanierService commandService;

	    public List<LignePanier> getAllCommandLines(){
	        return commandLineRepository.findAll();
	    }

	    public LignePanier getCommandLineById(Long id) {
	        return commandLineRepository.findById(id).orElseThrow(()-> 
			new IllegalArgumentException("Il n'existe pas"));
	    }


	    public LignePanier addCommandLine(Long bookId,  LignePanier commandLine,
	                                      Long userId) throws Exception {
        Book b = bookService.getBookById(bookId);
        Optional<Panier> c = commandService.getUserActiveCommand(userId);
//	        // if the user still has a command which is not winded up
	        if (c.isPresent()){
            commandLine.setCommand(c.get());
	            System.err.println(true);
	            //test if the command already has a command line containing the book id
	            if (commandLineRepository.findCommandLineByCommandIdAndBookId(c.get().getId(), bookId).isPresent())
	                throw new Exception("You have already commanded this book");
            }

	        else {
            System.err.println(false);
	            // else create a new command
	            Panier cc =new Panier();
	            commandLine.setCommand(commandService.createCommand(cc));
	        }
	        commandLine.setBook(b);
	        return commandLineRepository.save(commandLine);
  }

	    public void deleteCommandLine(Long clId) throws Exception {
	        LignePanier c = getCommandLineById(clId);
	        if (commandService.getCmdById(c.getCommand().getId()).isFinale()) {
	            throw new Exception("This command is " +
	                    "winded you can't modify its lines");
	        }
	        if (commandLineRepository.findCommandLinesByCommandId(c.getCommand().getId()).size()==1) commandService.deleteCommand(c.getCommand().getId());
	        commandLineRepository.deleteById(clId);
	    }

	    public LignePanier updateCom(Long coId, Long bId, LignePanier c) throws Exception {
	        if (commandService.getCmdById(coId).isFinale()) {
	            throw new Exception("This command is " +
	                    "winded you can't modify its lines");
	        }
	        LignePanier cl = commandLineRepository.findCommandLineByCommandIdAndBookId(coId, bId).get();
	        cl.setQuantity(c.getQuantity());
	        return commandLineRepository.save(cl);
	    }

	}
