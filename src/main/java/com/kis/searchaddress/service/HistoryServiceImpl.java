package com.kis.searchaddress.service;

import com.kis.searchaddress.dao.SearchHistoryDAO;
import com.kis.searchaddress.dto.SearchHistoryDTO;
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
    public boolean saveHistory(SearchHistoryDTO dto) {
        // 변환
        SearchHistoryDAO searchHistoryDAO = new SearchHistoryDAO(dto.getInput(), dto.getSearchResult());
        return historyRepository.saveHistory(searchHistoryDAO);
    }

    @Override
    public List<SearchHistoryDTO> findAllHistory() {
        List<SearchHistoryDTO> dto = new ArrayList<>();
        List<SearchHistoryDAO> dao = historyRepository.findAllHistory();

        // 변환
        for (SearchHistoryDAO searchHistoryDAO : dao) {
            SearchHistoryDTO searchHistoryDTO = new SearchHistoryDTO(searchHistoryDAO.getInput(), searchHistoryDAO.getSearchResult());
            dto.add(searchHistoryDTO);
        }

        return dto;
    }
}
