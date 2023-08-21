package com.bolsadeideas.springboot.apirest.models.DAO;

import com.bolsadeideas.springboot.apirest.models.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientDao extends CrudRepository<Client,Long> {
}
