package org.fmazmz.jwtservice.adapter.requests;

public record LoginRequest(
        String username,
        String password
) {
}
