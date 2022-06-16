package com.kis.searchaddress.api.helper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TypeConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> apiRequestDTOToMap(ApiRequestDTO dto) {
        Map<String, Object> map = new HashMap<>();

        map.put("query", dto.getQuery());

        return map;
    }

    public static <T> T stringToDTO(String str, Class<T> cls) throws JsonProcessingException {

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        return objectMapper.readValue(str, cls);
    }

    // review: javadoc으로 exception 발생시 empty String("") 이 반환될 수 있음을 알려주면 좋겠네요!
    public static <T> String dtoToJsonString(T dto) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.info("[dtoToJsonString] JsonProcessingException 발생::: " + e.getMessage());
        }

        log.info("dtoToJsonString::: " + jsonString);
        return jsonString;
    }

    public static String mapToHttpParams(Map<String, Object> map) {
        StringBuilder paramBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            paramBuilder.append(paramBuilder.length() > 0 ? "&" : "");
            paramBuilder.append(String.format("%s=%s", key,
                    map.get(key).toString()));
        }
        return paramBuilder.toString();
    }

    private static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
