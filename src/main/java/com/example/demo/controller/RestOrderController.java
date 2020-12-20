package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LignePanier;
import com.example.demo.services.LinePanier;


@RestController
@RequestMapping("command-line")
public class RestOrderController{
	 @Autowired
	    LinePanier commandLineService;

	    @GetMapping("")
	    public List<LignePanier> getAllLines() {
	        return commandLineService.getAllCommandLines();
	    }

	    @GetMapping("{id}")
	    public LignePanier getLine(@PathVariable Long id){
	        return commandLineService.getCommandLineById(id);
	    }

 	    @PostMapping("/add/user/{userId}/book/{bookId}")
 	    public LignePanier addLine(@PathVariable Long bookId,@PathVariable Long userId,
 	                               @RequestBody @Validated LignePanier c, BindingResult result) throws Exception {
 	        if (result.hasErrors())
 	            System.err.println(result.getAllErrors());
 	        return commandLineService.addCommandLine(bookId, c, userId);
 	    }

	    @DeleteMapping("{id}")
	    public void deleteCommandLine(@PathVariable Long id) throws Exception {
	        commandLineService.deleteCommandLine(id);
	    }

	    @PutMapping("modify/command/{cmdId}/book/{bookId}")
	    public LignePanier updateCom(@PathVariable Long cmdId, @PathVariable Long bookId,
	                                 @RequestBody @Validated LignePanier c, BindingResult result) throws Exception {
	        if (result.hasErrors())
	            System.err.println(result.getAllErrors());
	        return commandLineService.updateCom(cmdId, bookId, c);
	    }
	}