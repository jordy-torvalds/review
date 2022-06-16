package com.kis.searchaddress.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.api.constants.EndPoint;
import com.kis.searchaddress.api.helper.WebClientHelper;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.kakaoApi.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.kakaoApi.address.AddressDocument;
import com.kis.searchaddress.dto.response.kakaoApi.keyword.KeywordApiResponseDTO;
import com.kis.searchaddress.dto.response.kakaoApi.keyword.KeywordDocument;
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
        String returnString = "";

        List<AddressDocument> addressDocuments = addressDto.getAddressDocuments();
        List<KeywordDocument> keywordDocuments = keywordDto.getKeywordDocuments();

        String selectedRegion = keywordDto.getMeta().getSame_name().getSelected_region();

        //addressDocuments가 없으면
        //selectedRegion이 없으면
        //keyworddocument를 찾는다
        if (addressDocuments.size() != 0) {
            AddressDocument addressDocument = addressDocuments.get(0);
            String addressName = addressDocument.getRoad_address() == null ? "" : addressDocument.getRoad_address().getAddress_name();

            if (!"".equals(addressName)) {
                returnString = trimRoadStr(addressName);
            }
        }

        if ("".equals(returnString) && !"".equals(selectedRegion)) {
            returnString = trimRoadStr(selectedRegion);
        }

        if ("".equals(returnString) && keywordDocuments.size() != 0) {
            KeywordDocument keywordDocument = keywordDocuments.get(0);
            String addressName = keywordDocument.getRoad_address_name();

            returnString = trimRoadStr(addressName);
        }

        return returnString;
    }

    public String trimRoadStr(String str) {
        StringBuilder result = new StringBuilder();
        String[] arr = str.split(" ");

        for (String s : arr) {
            char[] c = s.toCharArray();
            if(c.length == 0) {
                continue;
            }
            char roadChar = c[c.length - 1];

            if (roadChar == '로') {
                result.append(s);
            } else if (roadChar == '길') {
                result.append(s);
                break;
            }
        }

        return result.toString();
    }
}
//주소 결과값 로직 순서
//1. 일단 다 keywordDto -> meta -> selectedRegion
//2. 1의 결과값에서 ㅇㅇ로, ㅇㅇ길, ㅇㅇ로+ ㅇㅇ길 만 추출