package com.bolsadeideas.springboot.apirest.models.services;

import com.bolsadeideas.springboot.apirest.models.DAO.IClientDao;
import com.bolsadeideas.springboot.apirest.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Esta clase hace de fachada de la interfaz IClientDao que extiende de la clase CrudRepository
 * se encargara de interactuar y modificar los registros de Client en la bbdd e implementa los métodos de su interfaz
 * IClientService
 */
@Service
public class ClientServiceImpl implements IClientService{
    @Autowired
    private IClientDao clientDao;
    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    @Transactional
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }
}
