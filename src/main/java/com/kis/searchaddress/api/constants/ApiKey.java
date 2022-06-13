package com.kis.searchaddress.api.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApiKey {
    @Value("${admin-key}")
    private String adminKey;

    @Value("${rest-key}")
    private String restKey;

    private static volatile ApiKey instance;

    private ApiKey() {
    }

    public static ApiKey getInstance() {
        if (instance == null) {
            instance = new ApiKey();
        }
        return instance;
    }
}
