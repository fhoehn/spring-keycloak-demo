package com.fhoehn.keycloakdemo.service.petstore.presentation.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDto {
	
	@NotNull
	private UUID id;
	@NotNull
	private String name;
	private String description;

}
