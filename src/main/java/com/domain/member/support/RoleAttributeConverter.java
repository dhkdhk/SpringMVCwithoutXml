package com.domain.member.support;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

public class RoleAttributeConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return JsonMappingUtils.toJson(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            throw new IllegalArgumentException("Role Attribute is not founded.");
        }
        return JsonMappingUtils.toObject(dbData, List.class);
    }
}
