package com.kis.searchaddress.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.keyword.KeywordApiResponseDTO;

import java.util.List;

public interface AddressSearchService {
    public AddressApiResponseDTO addressApiResult(ApiRequestDTO dto) throws JsonProcessingException;

    public KeywordApiResponseDTO keywordApiResult(ApiRequestDTO dto) throws JsonProcessingException;
}
