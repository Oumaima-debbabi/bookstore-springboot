package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Panier;
import com.example.demo.services.PanierService;

public class PanierController {


    @Autowired
    PanierService commandService;

    @GetMapping("all")
    public List<Panier> getAllCommand(){
        return commandService.getAllCommand();
    }

    @GetMapping("{id}")
    public Panier getCommandById(@PathVariable Long id) {
        return commandService.getCmdById(id);
    }

    @GetMapping("user/{id}")
    public List<Panier> getCommandByUser(@PathVariable Long id) {
        return commandService.getUserCommands(id);
    }

    @PostMapping("new")
    public Panier createCommand(@RequestBody @Validated Panier c) {
        return commandService.createCommand(c);
    }

    @DeleteMapping("{id}")
    public Panier deleteCommand(@PathVariable Long id) {
        return commandService.deleteCommand(id);
    }

    @GetMapping("{id}/total")
    public double windUpCommand(@PathVariable Long id){
        return commandService.windUpCommand(id);
    }
    }