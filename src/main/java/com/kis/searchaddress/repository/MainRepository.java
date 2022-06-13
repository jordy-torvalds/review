package com.kis.searchaddress.repository;

import com.kis.searchaddress.dao.SearchHistoryDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class MainRepository {
    private static List<SearchHistoryDAO> store = new ArrayList<>();

    public boolean saveHistory(SearchHistoryDAO dao) {
        store.add(dao);

        return true;
    }

    public List<SearchHistoryDAO> findAllHistory() {
        return store;
    }
}
