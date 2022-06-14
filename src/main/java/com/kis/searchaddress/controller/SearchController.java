package com.kis.searchaddress.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kis.searchaddress.dto.response.history.HistoryResponseDTO;
import com.kis.searchaddress.dto.request.ApiRequestDTO;
import com.kis.searchaddress.dto.response.kakaoApi.address.AddressApiResponseDTO;
import com.kis.searchaddress.dto.response.kakaoApi.keyword.KeywordApiResponseDTO;
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

    @PostMapping("/api")
    public String addressSearch(@RequestBody ApiRequestDTO dto) {
        log.info("dto.toString(): "+dto.toString());
        String result = "{}";

        try {
            AddressApiResponseDTO addressApiDTO = apiService.addressApi(dto);
            KeywordApiResponseDTO keywordApiDTO = apiService.keywordApi(dto);

            String s = apiService.searchResult(addressApiDTO, keywordApiDTO);

            log.info(s);
            result = "{\"result\":\""+s+"\"}";
            log.info(result);

            HistoryResponseDTO historyDTO = new HistoryResponseDTO(dto.toString(), s);
            log.info(historyDTO.toString());

            historyService.saveHistory(historyDTO);
        } catch (JsonProcessingException e) {
            log.info("[addressSearch] JsonProcessingException 발생::: "+ e.getMessage());
        }

        return result;
    }

    @PostMapping("/history")
    public String historySearch() {
        List<HistoryResponseDTO> allHistory = historyService.findAllHistory();

        for (int i = 0; i < allHistory.size(); i++) {
            log.info(i + " ::: "+ allHistory.get(i).toString());
        }
        return "{}";
    }
}
