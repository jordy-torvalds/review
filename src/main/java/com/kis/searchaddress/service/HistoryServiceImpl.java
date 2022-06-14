package com.kis.searchaddress.service;

import com.kis.searchaddress.dao.HistoryDAO;
import com.kis.searchaddress.dto.response.history.HistoryResponseDTO;
import com.kis.searchaddress.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public boolean saveHistory(HistoryResponseDTO dto) {
        // 변환
        HistoryDAO historyDAO = new HistoryDAO(dto.getInput(), dto.getSearchResult());
        return historyRepository.saveHistory(historyDAO);
    }

    @Override
    public List<HistoryResponseDTO> findAllHistory() {
        List<HistoryResponseDTO> dto = new ArrayList<>();
        List<HistoryDAO> dao = historyRepository.findAllHistory();

        // 변환
        for (HistoryDAO historyDAO : dao) {
            HistoryResponseDTO searchHistoryDTO = new HistoryResponseDTO(historyDAO.getInput(), historyDAO.getSearchResult());
            dto.add(searchHistoryDTO);
        }

        return dto;
    }
}
