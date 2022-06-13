package com.kis.searchaddress.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.keyword.KeywordApiResponseDTO;
import com.kis.searchaddress.service.AddressSearchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주소 검색을 수행하는 Controller.
 * Kakao 지도 API 이용
 */
@RestController
@Slf4j
public class AddressSearchController {

    @Autowired
    AddressSearchServiceImpl searchService;

    @PostMapping("/search")
    public String addressSearch(@RequestBody ApiRequestDTO dto) {
        log.info("dto.toString(): "+dto.toString());

        try {
            AddressApiResponseDTO addressApiDTO = searchService.addressApiResult(dto);
            KeywordApiResponseDTO keywordApiDTO = searchService.keywordApiResult(dto);

        } catch (JsonProcessingException e) {
            log.info("[addressSearch] JsonProcessingException 발생::: "+ e.getMessage());
        }

        return "{}";
    }
}
