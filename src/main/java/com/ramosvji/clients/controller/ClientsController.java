package com.ramosvji.clients.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramosvji.clients.controller.dto.ClientDtoRequest;
import com.ramosvji.clients.controller.dto.ClientDtoResponse;
import com.ramosvji.clients.controller.dto.ClientWithPasswordDtoResponse;

@RestController
public interface ClientsController {
	
	@PostMapping(path="/v01/clients/")
	public ResponseEntity<ClientDtoResponse> saveWithPassword(final @RequestBody ClientDtoRequest request );
	
	@PostMapping(path="/v01/clients/")
	public ResponseEntity<ClientDtoResponse> saveWithoutPassword(final @RequestBody ClientDtoRequest request );

	@GetMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientWithPasswordDtoResponse> getPasswordByName(final @PathVariable("username") String username);
	
	@DeleteMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientDtoRequest> delete(final @PathVariable("username") String username);

}
