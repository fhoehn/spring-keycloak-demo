package com.fhoehn.keycloakdemo.service.petstore.presentation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhoehn.keycloakdemo.service.petstore.presentation.dto.PetDto;

import jakarta.servlet.http.HttpServletRequest;

/**
 * A really bad implementation of a petstore, which demonstrates the basic usage of an external IDP via openID-connect
 */
@RestController()
@RequestMapping("/api/petstore/v1/pets")
public class PetsController {

	private final List<PetDto> pets;
	
	public PetsController() {
		pets = new ArrayList<PetDto>();
		pets.add(PetDto.builder().id(UUID.randomUUID()).name("Panda").description("A funny animal").build());
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PetDto>> getAllPets(JwtAuthenticationToken auth) {
		return ResponseEntity.ok(pets);
	}
	
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('petstore_admin')")
	public ResponseEntity<Void> createPet(HttpServletRequest request, JwtAuthenticationToken auth, PetDto pet) throws URISyntaxException{
		pets.add(pet);
		return ResponseEntity.created(new URI(request.getRequestURI()+"/"+pet.getId())).build();
		
	}

}
