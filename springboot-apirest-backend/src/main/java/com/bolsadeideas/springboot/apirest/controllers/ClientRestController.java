package com.bolsadeideas.springboot.apirest.controllers;

import com.bolsadeideas.springboot.apirest.models.DAO.IClientDao;
import com.bolsadeideas.springboot.apirest.models.entity.Client;
import com.bolsadeideas.springboot.apirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Clase controladora que hace de via de entrada a app externas para valerse de los métodos que esta misma posee para
 * interactuar con la tabla clients de la bbdd. Utiliza una injecion de la clase ClientServiceImpl usando su interfaz como
 * tipo de dato
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class ClientRestController {
    @Autowired
    private IClientService clientService;
    @Autowired
    private IClientDao iClientDao;

    @GetMapping("/clients")
    public List<Client> findAll(){
        return clientService.findAll();
    }
    @GetMapping("/clients/{id}")
    public Client findById(@PathVariable Long id){
        return clientService.findById(id);
    }
    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client){
        return clientService.save(client);
    }
    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client clientUpdate, @PathVariable Long id){
        Client client = clientService.findById(id);

        client.setName(clientUpdate.getName());
        client.setLastName(clientUpdate.getLastName());
        client.setEmail(clientUpdate.getEmail());

        return clientService.save(client);
    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clientService.deleteById(id);
    }

}
