package com.ramosvji.clients.controller.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramosvji.clients.controller.ClientsController;
import com.ramosvji.clients.controller.dto.ClientDtoRequest;
import com.ramosvji.clients.controller.dto.ClientDtoResponse;
import com.ramosvji.clients.controller.dto.ClientWithPasswordDtoResponse;
import com.ramosvji.clients.service.ClientsService;
import com.ramosvji.clients.service.dto.ClientIntDtoRequest;
import com.ramosvji.clients.service.dto.ClientIntDtoResponse;

@RestController
@RequestMapping(path="/ramosvji/api/clients")
public class ClientsControllerImpl implements ClientsController{
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private ClientsService service;

	@Override
	@PostMapping(path="/v01/clients/")
	public ResponseEntity<ClientDtoResponse> saveWithPassword(final @RequestBody ClientDtoRequest request) {
		ClientIntDtoRequest clientRequest =  modelMapper.map(request, ClientIntDtoRequest.class);
		
		ClientIntDtoResponse clientResponse = service.save(clientRequest);
		
		ClientDtoResponse response = modelMapper.map(clientResponse, ClientDtoResponse.class);
		
		return new ResponseEntity<ClientDtoResponse>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	@PostMapping(path="/v01/clients/free")
	public ResponseEntity<ClientDtoResponse> saveWithoutPassword(final @RequestBody ClientDtoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientWithPasswordDtoResponse> getPasswordByName(final @PathVariable String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientDtoRequest> delete(final @PathVariable String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
