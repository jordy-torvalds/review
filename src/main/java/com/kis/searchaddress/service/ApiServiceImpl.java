package com.kis.searchaddress.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.api.constants.EndPoint;
import com.kis.searchaddress.api.helper.WebClientHelper;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.address.Document;
import com.kis.searchaddress.dto.response.keyword.KeywordApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kis.searchaddress.api.helper.TypeConverter.*;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Autowired
    WebClientHelper helper;

    @Override
    public AddressApiResponseDTO addressApi(ApiRequestDTO dto) throws JsonProcessingException {
        String result = helper.request(EndPoint.ADDRESS.getType(), EndPoint.ADDRESS.getUrl(), apiRequestDTOToMap(dto));

        AddressApiResponseDTO addressApiResponseDTO = stringToDTO(result, AddressApiResponseDTO.class);
        log.info(addressApiResponseDTO.toString());

        return addressApiResponseDTO;
    }

    @Override
    public KeywordApiResponseDTO keywordApi(ApiRequestDTO dto) throws JsonProcessingException {
        String result = helper.request(EndPoint.KEYWORD.getType(), EndPoint.KEYWORD.getUrl(), apiRequestDTOToMap(dto));

        KeywordApiResponseDTO keywordApiResponseDTO = stringToDTO(result, KeywordApiResponseDTO.class);
        log.info(keywordApiResponseDTO.toString());

        return keywordApiResponseDTO;
    }

    //두 API 결과 가지고 ㅇㅇ로, ㅇㅇ길 도출 -> 추후 완성 예정
    @Override
    public String searchResult(AddressApiResponseDTO addressDto, KeywordApiResponseDTO keywordDto) {
        List<Document> documents = addressDto.getDocuments();
        return documents.get(0).getAddress_name();
    }
}
