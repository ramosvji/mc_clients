package com.ramosvji.clients.controller.impl;

import java.util.Locale;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ClientsControllerImpl implements ClientsController {
	private static final int SIZE = 5;
	
	@Autowired
	Environment environment;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private ClientsService service;

	@Override
	@PostMapping(path="/v01/clients/")
	public ResponseEntity<ClientDtoResponse> saveWithPassword(final @RequestHeader(name="Accept-Language",required = false) Locale localeReq,final @Validated @RequestBody ClientDtoRequest request) {
		System.out.println("-> "+ localeReq);
		ClientIntDtoRequest clientRequest =  modelMapper.map(request, ClientIntDtoRequest.class);
		
		ClientIntDtoResponse clientResponse = service.save(clientRequest);
		
		ClientDtoResponse response = modelMapper.map(clientResponse, ClientDtoResponse.class);
		
		HttpHeaders header = new HttpHeaders();
		String uri = "/ramosvji/api/clients/v01/clients/" + response.getId();
		
		header.set("Location", uri);
		
		return new ResponseEntity<ClientDtoResponse>(response, header, HttpStatus.CREATED);
	}

	@Override
	@GetMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientWithPasswordDtoResponse> getClientsByName(final @PathVariable String username) {
		ClientIntDtoResponse clientResponse = service.getClientByUsername(username);		
		
		ClientWithPasswordDtoResponse response = modelMapper.map(clientResponse, ClientWithPasswordDtoResponse.class);
		System.out.println("puerto " + environment.getProperty("local.server.port"));
		
		return new ResponseEntity<ClientWithPasswordDtoResponse>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	@DeleteMapping(path="/v01/clients/{username}")
	public ResponseEntity<Void> delete(final @PathVariable String username) {
		service.delete(username);
		return new ResponseEntity<Void>(null, null, HttpStatus.NO_CONTENT);
	}

	@Override
	@GetMapping(path="/v01/clients")
	public ResponseEntity<Page<ClientWithPasswordDtoResponse>> getClients(final @RequestParam("page") Integer page) {
		Pageable pageable = PageRequest.of(page, SIZE);
		
		Page<ClientIntDtoResponse> clientsResponse = service.getAll(pageable);
		
		Page<ClientWithPasswordDtoResponse> result = clientsResponse.map(new Function<ClientIntDtoResponse, ClientWithPasswordDtoResponse>() {
		    @Override
		    public ClientWithPasswordDtoResponse apply(ClientIntDtoResponse clientsResponse) {
		    	ClientWithPasswordDtoResponse dto = modelMapper.map(clientsResponse, ClientWithPasswordDtoResponse.class);
		  
		        return dto;
		    }
		});
		
		return new ResponseEntity<Page<ClientWithPasswordDtoResponse>>(result, null, HttpStatus.ACCEPTED);
	}

	@Override
	@PutMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientDtoResponse> update(final @Validated @RequestBody ClientDtoRequest request,
			final @PathVariable("username") String username) {
		ClientIntDtoRequest clientRequest =  modelMapper.map(request, ClientIntDtoRequest.class);
		ClientIntDtoResponse clientResponse = service.update(clientRequest, username);
		ClientDtoResponse response = modelMapper.map(clientResponse, ClientDtoResponse.class);
	
		return new ResponseEntity<ClientDtoResponse>(response, null, HttpStatus.CREATED);
	}

}