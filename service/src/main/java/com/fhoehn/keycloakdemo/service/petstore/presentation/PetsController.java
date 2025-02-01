package com.fhoehn.keycloakdemo.service.petstore.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhoehn.keycloakdemo.service.petstore.presentation.dto.Pet;

@RestController()
@RequestMapping("/v1/petstore/pets")
public class PetsController {

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> getAllPets() {

		List<Pet> mockedPets = List
				.of(Pet.builder().id(UUID.randomUUID()).name("Panda").description("A funny animal").build());
		return ResponseEntity.ok(mockedPets);
	}

}
