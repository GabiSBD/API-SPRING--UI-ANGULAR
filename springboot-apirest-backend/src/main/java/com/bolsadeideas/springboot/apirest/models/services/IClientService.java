package com.bolsadeideas.springboot.apirest.models.services;

import com.bolsadeideas.springboot.apirest.models.entity.Client;

import java.util.List;

public interface IClientService {
    public List<Client> findAll();
    public Client findById(Long id);
    public Client save(Client client);
    public void deleteById(Long id);
}
