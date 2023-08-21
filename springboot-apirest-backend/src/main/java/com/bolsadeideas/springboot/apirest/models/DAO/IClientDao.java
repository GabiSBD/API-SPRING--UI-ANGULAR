package com.bolsadeideas.springboot.apirest.models.DAO;

import com.bolsadeideas.springboot.apirest.models.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Extiende de la clase CrudRepository que provee de métodos básicos para interactuar con tablas de la bbdd
 */
@Repository
public interface IClientDao extends CrudRepository<Client,Long> {
}
