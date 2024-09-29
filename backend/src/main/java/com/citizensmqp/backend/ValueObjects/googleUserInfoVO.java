package com.citizensmqp.backend.ValueObjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class googleUserInfoVO {
    public String name;
    public String email;
    public String id;
    public String picture;
    public String family_name;
    public boolean verified;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
