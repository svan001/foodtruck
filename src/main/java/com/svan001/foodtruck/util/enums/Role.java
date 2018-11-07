package com.svan001.foodtruck.util.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    // Account manager : foodtruck owner
    MANAGER("MANAGER"),
    // Employee : work for the food truck, need access to some data
    EMPLOYEE("EMPLOYEE"),
    // Customer/client using the application
    CUSTOMER("CUSTOMER");

    Role(String label) {
        this.label = label;
    }

    private String label;

    public static final Role getByLabel(String label) {
        for (Role role : values()) {
            if (role.getLabel().equals(label)) {
                return role;
            }
        }
        return null;
    }

}
