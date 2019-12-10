package com.ramosvji.clients.service.impl;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramosvji.clients.config.ConfigSecurity;
import com.ramosvji.clients.entity.Client;
import com.ramosvji.clients.repository.ClientsRepository;
import com.ramosvji.clients.service.ClientsService;
import com.ramosvji.clients.service.dto.ClientIntDtoRequest;
import com.ramosvji.clients.service.dto.ClientIntDtoResponse;
import com.ramosvji.clients.service.mapper.ClientMapper;

@Service
@Transactional
public class ClientsServiceImpl implements ClientsService {
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private ClientsRepository repository;
	
	@Autowired
	private ConfigSecurity configSecurity;
	
	@Autowired
	private ClientMapper clientMapper;

	@Override
	public ClientIntDtoResponse save(final ClientIntDtoRequest request) {
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
	public ClientIntDtoResponse getClientByUsername(final String username) {
		Client client = repository.findByUsername(username);
		ClientIntDtoResponse clientResponse = modelMapper.map(client, ClientIntDtoResponse.class);
		
		return clientResponse;
	}

	@Override
	public void delete(String username) {
		repository.deleteByUsername(username);
	}

	@Override
	public Page<ClientIntDtoResponse> getAll(final Pageable pageable) {
		Page<Client> client = repository.findAll(pageable);
		
		Page<ClientIntDtoResponse> result = client.map(new Function<Client, ClientIntDtoResponse>() {
		    @Override
		    public ClientIntDtoResponse apply(Client client) {
		    	ClientIntDtoResponse dto = modelMapper.map(client, ClientIntDtoResponse.class);
		  
		        return dto;
		    }
		});
				
		return result;
	}

	@Override
	public ClientIntDtoResponse update(final ClientIntDtoRequest clientIntDtoRequest,final String username) {
		Client client = repository.findByUsername(username);
		
		client.setName(clientIntDtoRequest.getName());	
		client.setLastname(clientIntDtoRequest.getLastname());
		client.setRoles(clientMapper.rolesMapper(clientIntDtoRequest.getRoles()));
		
		client = repository.save(client);
		
		ClientIntDtoResponse clientResponse = modelMapper.map(client, ClientIntDtoResponse.class);
		
		return clientResponse;
	}

}
