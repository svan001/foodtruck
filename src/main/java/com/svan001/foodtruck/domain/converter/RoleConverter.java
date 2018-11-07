package com.svan001.foodtruck.domain.converter;

import com.svan001.foodtruck.util.enums.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.getLabel();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.getByLabel(dbData);
    }
}
