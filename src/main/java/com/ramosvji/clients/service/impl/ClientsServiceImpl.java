package com.ramosvji.clients.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramosvji.clients.config.ConfigSecurity;
import com.ramosvji.clients.entity.Client;
import com.ramosvji.clients.repository.ClientsRepository;
import com.ramosvji.clients.service.ClientsService;
import com.ramosvji.clients.service.dto.ClientIntDtoRequest;
import com.ramosvji.clients.service.dto.ClientIntDtoResponse;

@Service
public class ClientsServiceImpl implements ClientsService {
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private ClientsRepository repository;
	
	@Autowired
	private ConfigSecurity configSecurity;

	@Override
	public ClientIntDtoResponse save(ClientIntDtoRequest request) {
		Client client =  modelMapper.map(request, Client.class);
		
		if(client != null ) {
			final String passwordEncoded = configSecurity.encode(client.getPassword());
			client.setPassword(passwordEncoded);
		}
		
		client = repository.save(client);
		ClientIntDtoResponse clientResponse = modelMapper.map(client, ClientIntDtoResponse.class);
		
		return clientResponse;
	}

	@Override
	public ClientIntDtoResponse getClientByUsername(String username) {
		Client client = repository.findByUsername(username);
		ClientIntDtoResponse clientResponse = modelMapper.map(client, ClientIntDtoResponse.class);
		
		return clientResponse;
	}

	@Override
	public void delete(String username) {
		
	}

}
