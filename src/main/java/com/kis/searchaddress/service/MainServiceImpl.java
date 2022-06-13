package com.kis.searchaddress.service;

import com.kis.searchaddress.dao.SearchHistoryDAO;
import com.kis.searchaddress.dto.SearchHistoryDTO;
import com.kis.searchaddress.repository.MainRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MainServiceImpl implements MainService {

    @Autowired
    MainRepository mainRepository;

    @Override
    public boolean saveHistory(SearchHistoryDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        // 매핑 전략 설정
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // 변환
        SearchHistoryDAO searchHistoryDAO = modelMapper.map(dto, SearchHistoryDAO.class);
        return mainRepository.saveHistory(searchHistoryDAO);
    }

    @Override
    public List<SearchHistoryDTO> findAllHistory() {
        List<SearchHistoryDTO> dto = new ArrayList<>();
        List<SearchHistoryDAO> dao = mainRepository.findAllHistory();

        ModelMapper modelMapper = new ModelMapper();

        // 매핑 전략 설정
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // 변환
        for (SearchHistoryDAO searchHistoryDAO : dao) {
            SearchHistoryDTO searchHistoryDTO = modelMapper.map(searchHistoryDAO, SearchHistoryDTO.class);
            dto.add(searchHistoryDTO);
        }

        return dto;
    }
}
