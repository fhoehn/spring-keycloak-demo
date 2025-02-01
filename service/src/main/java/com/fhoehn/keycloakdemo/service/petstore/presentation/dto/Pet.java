package com.fhoehn.keycloakdemo.service.petstore.presentation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
	
	private UUID id;
	private String name;
	private String description;

}
