package com.example.demo.controller;
import com.example.demo.entities.*;
import com.example.demo.services.PanierService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
public class PanierController {
	@Autowired
	PanierService pService;

    @GetMapping("all")
    public List<Panier> getAllCommand(){
        return pService.getAllCommand();
    }

    @GetMapping("{id}")
    public Panier getCommandById(@PathVariable Long id) {
        return pService.getCmdById(id);
    }

    @GetMapping("user/{id}")
    public List<Panier> getCommandByUser(@PathVariable Long id) {
        return pService.getUserCommands(id);
    }

    @PostMapping("new")
    public Panier createCommand(@RequestBody @Validated Panier c) {
        return pService.createCommand(c);
    }

    @DeleteMapping("{id}")
    public Panier deleteCommand(@PathVariable Long id) {
        return pService.deleteCommand(id);
    }

    @GetMapping("{id}/total")
    public double windUpCommand(@PathVariable Long id){
        return pService.windUpCommand(id);
    }
}
