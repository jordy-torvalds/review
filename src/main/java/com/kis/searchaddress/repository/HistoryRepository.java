package com.kis.searchaddress.repository;

import com.kis.searchaddress.dao.HistoryDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class HistoryRepository {
    private static List<HistoryDAO> store = new ArrayList<>();

    public boolean saveHistory(HistoryDAO dao) {
        store.add(dao);

        return true;
    }

    public List<HistoryDAO> findAllHistory() {
        return store;
    }
}
