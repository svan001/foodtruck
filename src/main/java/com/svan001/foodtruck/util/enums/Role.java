package com.svan001.foodtruck.util.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN", true),
    // Account manager : foodtruck owner
    MANAGER("MANAGER", true),
    // Employee : work for the food truck, need access to some data
    EMPLOYEE("EMPLOYEE", true),
    // Customer/client using the application
    CUSTOMER("CUSTOMER", true),
    // TODO to remove TEST
    AUTH_1("AUTH_1", false);

    Role(String label, boolean isRole) {
        this.authorityName = isRole ? "ROLE_" + label : label;
        this.label = label;
        this.isRole = isRole;
    }

    private String label;
    // Spring Sec will sometime need us to add "ROLE_" before the label for Role, when is does not do it itself
    private String authorityName;
    // If not => is authority
    private boolean isRole;

    public static final Role getByLabel(String label) {
        for (Role role : values()) {
            if (role.getLabel().equals(label)) {
                return role;
            }
        }
        return null;
    }

}
