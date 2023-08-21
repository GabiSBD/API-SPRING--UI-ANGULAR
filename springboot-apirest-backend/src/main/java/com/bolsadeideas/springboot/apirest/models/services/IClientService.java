package com.bolsadeideas.springboot.apirest.models.services;

import com.bolsadeideas.springboot.apirest.models.entity.Client;

import java.util.List;

public interface IClientService {
    public List<Client> findAll();
}
