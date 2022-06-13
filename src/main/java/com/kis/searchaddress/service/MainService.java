package com.kis.searchaddress.service;

import com.kis.searchaddress.dto.SearchHistoryDTO;

import java.util.List;

public interface MainService {

    boolean saveHistory(SearchHistoryDTO dto);

    List<SearchHistoryDTO> findAllHistory();
}
