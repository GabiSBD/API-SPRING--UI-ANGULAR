package com.bolsadeideas.springboot.apirest.controllers;

import com.bolsadeideas.springboot.apirest.models.entity.Client;
import com.bolsadeideas.springboot.apirest.models.services.ClientServiceImpl;
import com.bolsadeideas.springboot.apirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class ClientRestController {
    @Autowired
    private IClientService clientService;
    @GetMapping("/clients")
    public List<Client> findAll(){
        return clientService.findAll();
    }

}
