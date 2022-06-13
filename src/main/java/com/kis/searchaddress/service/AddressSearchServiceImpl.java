package com.kis.searchaddress.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.api.constants.EndPoint;
import com.kis.searchaddress.api.helper.KakaoRestApiHelper;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.keyword.KeywordApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.kis.searchaddress.api.helper.TypeConverter.*;

@Service
@Slf4j
public class AddressSearchServiceImpl implements AddressSearchService {

    @Autowired
    KakaoRestApiHelper helper;

    @Override
    public AddressApiResponseDTO addressApiResult(ApiRequestDTO dto) throws JsonProcessingException {
        String result = helper.request(EndPoint.ADDRESS.getType(), EndPoint.ADDRESS.getUrl(), apiRequestDTOToMap(dto));

        AddressApiResponseDTO addressApiResponseDTO = stringToDTO(result, AddressApiResponseDTO.class);
        log.info(addressApiResponseDTO.toString());

        return addressApiResponseDTO;
    }

    @Override
    public KeywordApiResponseDTO keywordApiResult(ApiRequestDTO dto) throws JsonProcessingException {
        String result = helper.request(EndPoint.KEYWORD.getType(), EndPoint.KEYWORD.getUrl(), apiRequestDTOToMap(dto));

        KeywordApiResponseDTO keywordApiResponseDTO = stringToDTO(result, KeywordApiResponseDTO.class);
        log.info(keywordApiResponseDTO.toString());

        return keywordApiResponseDTO;
    }
}
