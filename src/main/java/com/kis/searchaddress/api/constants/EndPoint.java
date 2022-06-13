package com.kis.searchaddress.api.constants;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum EndPoint {
    KEYWORD(HttpMethod.GET, "/v2/local/search/keyword"),
    ADDRESS(HttpMethod.GET, "/v2/local/search/address");

    private final HttpMethod type;
    private final String url;

    EndPoint(HttpMethod type, String url) {
        this.type = type;
        this.url = url;
    }
}
