package com.kis.searchaddress.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kis.searchaddress.dto.response.history.HistoryResponseDTO;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.kakaoApi.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.kakaoApi.keyword.KeywordApiResponseDTO;
import com.kis.searchaddress.dto.response.view.ApiResultResponseDTO;
import com.kis.searchaddress.service.ApiServiceImpl;
import com.kis.searchaddress.service.HistoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kis.searchaddress.api.helper.TypeConverter.dtoToJsonString;

/**
 * 주소 검색을 수행하는 Controller.
 * Kakao 지도 API 이용
 */
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ApiServiceImpl apiService;

    @Autowired
    HistoryServiceImpl historyService;

    @PostMapping("/api")
    public String addressSearch(@RequestBody ApiRequestDTO dto) {
        log.info("dto.toString(): " + dto.toString());
        ApiResultResponseDTO apiResult;

        try {
            AddressApiResponseDTO addressApiDTO = apiService.addressApi(dto);
            KeywordApiResponseDTO keywordApiDTO = apiService.keywordApi(dto);

            String searchResult = apiService.searchResult(addressApiDTO, keywordApiDTO);

            log.info("searchResult::: " + searchResult);

            if ("".equals(searchResult)) {
                apiResult = new ApiResultResponseDTO("F", searchResult);
            } else {
                apiResult = new ApiResultResponseDTO("S", searchResult);
            }
        } catch (JsonProcessingException e) {
            log.info("[addressSearch] JsonProcessingException 발생::: " + e.getMessage());

            apiResult = new ApiResultResponseDTO("F", "");
        }

        if("S".equals(apiResult.getResultCd())) {
            HistoryResponseDTO historyDTO = new HistoryResponseDTO(dto.getQuery(), apiResult.getAddressResult());
            log.info(historyDTO.toString());

            historyService.saveHistory(historyDTO);
        }

        return dtoToJsonString(apiResult);
    }

    @PostMapping("/history")
    public String historySearch() {
        List<HistoryResponseDTO> allHistory = historyService.findAllHistory();

        return dtoToJsonString(allHistory);
    }
}
