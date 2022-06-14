package com.kis.searchaddress.service;

import com.kis.searchaddress.dto.response.history.HistoryResponseDTO;

import java.util.List;

public interface HistoryService {

    boolean saveHistory(HistoryResponseDTO dto);

    List<HistoryResponseDTO> findAllHistory();
}
