package ru.buinovsky.distillers.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MANAGER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
