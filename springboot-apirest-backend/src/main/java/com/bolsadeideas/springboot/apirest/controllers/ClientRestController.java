package com.bolsadeideas.springboot.apirest.controllers;

import com.bolsadeideas.springboot.apirest.models.DAO.IClientDao;
import com.bolsadeideas.springboot.apirest.models.entity.Client;
import com.bolsadeideas.springboot.apirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> findById(@PathVariable Long id){
        Client client =null;
        Map<String, Object> response = new HashMap<>();
        try {
            client = clientService.findById(id);

        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta en la BBDD");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(client == null){
            response.put("message","El Cliente con ID: ".concat(id.toString()).concat(" no existe en la BBDD"));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
    @PostMapping("/clients")
    public ResponseEntity<?> create(@RequestBody Client client){
        Client newClient = null;
        Map<String,Object> response = new HashMap<>();
        try {
             newClient = clientService.save(client);
        }catch (DataAccessException e){
            response.put("message","Error al realizar el insert en la BBDD");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "El cliente a sido creado con éxito");
        response.put("client",newClient);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@RequestBody Client clientUpdate, @PathVariable Long id){
        Client client = clientService.findById(id);
        Client result = null;
        Map<String,Object> response = new HashMap<>();

        if(client==null){
            response.put("message","Error al realizar el update en la BBDD, no se encontró el Cliente a actualizar en la BBDD");

            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            client.setName(clientUpdate.getName());
            client.setLastName(clientUpdate.getLastName());
            client.setEmail(clientUpdate.getEmail());

             result = clientService.save(client);
        }catch (DataAccessException e){
            response.put("message","Error al realizar el update en la BBDD");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "El Cliente con ID:"+id+" a sido actualizado con exito");
        response.put("client",result);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        Client clientToDelete = clientService.findById(id);

        if(clientToDelete==null) {
            response.put("message", "El id enviado no corresponde a ningún cliente");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try{
            clientService.deleteById(id);
        }catch (DataAccessException e){
            response.put("message", "Error al borrar el cliente con ID: "+id+"de la BBDD");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "El cliente con ID: "+id+" ha sido borrado con éxito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }

}
