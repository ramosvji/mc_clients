package com.ramosvji.clients.repository;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ramosvji.clients.entity.Client;

@RefreshScope
@Repository
public interface ClientsRepository extends MongoRepository<Client,String>{
	
	public Client findByUsername(final String username);
	
	public void deleteByUsername(final String username);

}
