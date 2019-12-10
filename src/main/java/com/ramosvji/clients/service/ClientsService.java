package com.ramosvji.clients.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ramosvji.clients.service.dto.ClientIntDtoRequest;
import com.ramosvji.clients.service.dto.ClientIntDtoResponse;

@Service
public interface ClientsService {

	public ClientIntDtoResponse save(final ClientIntDtoRequest client);
	
	public ClientIntDtoResponse getClientByUsername(final String username);
	
	public Page<ClientIntDtoResponse> getAll(Pageable pageable);
	
	public void delete(final String username );
	
	public ClientIntDtoResponse update(final ClientIntDtoRequest client, final String username);
}
