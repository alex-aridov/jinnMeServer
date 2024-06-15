package com.jinnme.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ObjectToShortJson {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public String getJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("cant serialize object {}", object, e);
            return null;
        }
    }
}
