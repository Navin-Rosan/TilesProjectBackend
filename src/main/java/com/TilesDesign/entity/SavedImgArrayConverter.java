package com.TilesDesign.entity;

import jakarta.persistence.*;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Entity
@Converter(autoApply = true)
public class SavedImgArrayConverter implements AttributeConverter<List<Map<String, String>>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Map<String, String>> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            // Convert the array of Maps to JSON string
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting to JSON string", e);
        }
    }

    @Override
    public List<Map<String, String>> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            // Convert the JSON string back to an array of Maps
            return objectMapper.readValue(dbData, new TypeReference<List<Map<String, String>>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting from JSON string", e);
        }
    }
}

