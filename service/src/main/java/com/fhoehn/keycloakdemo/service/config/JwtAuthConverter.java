package com.fhoehn.keycloakdemo.service.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * A simple converter for adding the 'ROLE_' prefix to the existing roles of the JWT, because Spring is always using this prefix somehow.
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	
	@Override
	public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
		
		Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), 
				extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
		
		return new JwtAuthenticationToken(jwt, authorities, JwtClaimNames.SUB);
	}


	private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
		
		
		Map<String, Object> resource;
		
		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		if(resourceAccess == null)
		{
			return Set.of();
		}
		if(resourceAccess.get("petstore-app") == null)
		{
			return Set.of();
		}
		resource = (Map<String, Object>) resourceAccess.get("petstore-app");
		
		Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
		return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet());
	}

}
