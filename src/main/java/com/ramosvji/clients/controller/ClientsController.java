package com.ramosvji.clients.controller;

import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramosvji.clients.controller.dto.ClientDtoRequest;
import com.ramosvji.clients.controller.dto.ClientDtoResponse;
import com.ramosvji.clients.controller.dto.ClientWithPasswordDtoResponse;

@RestController
public interface ClientsController {
	
	@PostMapping(path="/v01/clients/")
	public ResponseEntity<ClientDtoResponse> saveWithPassword(@RequestHeader(name="Accept-Language",required = false) Locale locale,final @RequestBody ClientDtoRequest request );
	
	@GetMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientWithPasswordDtoResponse> getClientsByName(final @PathVariable("username") String username);
	
	@GetMapping(path="/v01/clients/")
	public ResponseEntity<Page<ClientWithPasswordDtoResponse>> getClients(final @RequestParam("page") Integer page);
	
	@DeleteMapping(path="/v01/clients/{username}")
	public ResponseEntity<Void> delete(final @PathVariable("username") String username);
	
	@PutMapping(path="/v01/clients/{username}")
	public ResponseEntity<ClientDtoResponse> update(final @RequestBody ClientDtoRequest request,
			final @PathVariable("username") String username);

}
