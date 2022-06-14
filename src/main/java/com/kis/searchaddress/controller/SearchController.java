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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api")
    public String addressSearch(@RequestBody ApiRequestDTO dto) {
        log.info("dto.toString(): "+dto.toString());
        ApiResultResponseDTO apiResult;
        String apiResultJson = "";

        try {
            AddressApiResponseDTO addressApiDTO = apiService.addressApi(dto);
            KeywordApiResponseDTO keywordApiDTO = apiService.keywordApi(dto);

            String searchResult = apiService.searchResult(addressApiDTO, keywordApiDTO);

            log.info("searchResult::: "+ searchResult);

            apiResult = new ApiResultResponseDTO("S", searchResult);

            HistoryResponseDTO historyDTO = new HistoryResponseDTO(dto.toString(), searchResult);
            log.info(historyDTO.toString());

            historyService.saveHistory(historyDTO);
        } catch (JsonProcessingException e) {
            log.info("[addressSearch] JsonProcessingException 발생::: "+ e.getMessage());

            apiResult = new ApiResultResponseDTO("F","");
        }

        try {
            apiResultJson = objectMapper.writeValueAsString(apiResult);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return apiResultJson;
    }

    @PostMapping("/history")
    public String historySearch() {
        List<HistoryResponseDTO> allHistory = historyService.findAllHistory();
        String historyJson = "";

        for (int i = 0; i < allHistory.size(); i++) {
            log.info(i + " ::: "+ allHistory.get(i).toString());
        }

        try {
            historyJson = objectMapper.writeValueAsString(allHistory);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return historyJson;
    }
}
