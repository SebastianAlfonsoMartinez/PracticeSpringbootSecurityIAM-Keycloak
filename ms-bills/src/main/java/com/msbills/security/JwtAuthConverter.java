package com.msbills.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> resourceAccess = source.getClaim(SecurityConstants.ACCESS_CLIENT_NAME);
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (resourceAccess != null && (resource = (Map<String, Object>) resourceAccess.get(SecurityConstants.CLIENT_NAME)) != null
                && (resourceRoles = (Collection<String>) resource.get(SecurityConstants.ROLES)) != null)
            authorities.addAll(extractClientRoles(resourceRoles));

        Collection<String> resourceGroup = (List<String>) source.getClaim(SecurityConstants.GROUP_NAME);
        if (resourceGroup != null && !resourceGroup.isEmpty())
            authorities.addAll(extractGroup(resourceGroup));

        return authorities;
    }
    private static Collection<GrantedAuthority> extractClientRoles(Collection<String> resourceRoles) {
        return resourceRoles
                .stream()
                .map(roleMap -> new SimpleGrantedAuthority(SecurityConstants.ROLE + roleMap))
                .collect(Collectors.toList());
    }
    private static Collection<GrantedAuthority> extractGroup(Collection<String> resourceGroupAccess) {
        return  resourceGroupAccess
                .stream()
                .map(roleMap -> new SimpleGrantedAuthority(SecurityConstants.GROUP + roleMap))
                .collect(Collectors.toList());
    }
}
