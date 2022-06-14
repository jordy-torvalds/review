package com.kis.searchaddress.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.kakaoApi.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.kakaoApi.keyword.KeywordApiResponseDTO;


public interface ApiService {
    AddressApiResponseDTO addressApi(ApiRequestDTO dto) throws JsonProcessingException;

    KeywordApiResponseDTO keywordApi(ApiRequestDTO dto) throws JsonProcessingException;

    String searchResult(AddressApiResponseDTO addressDto, KeywordApiResponseDTO keywordDto);
}
