package com.kis.searchaddress.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SearchHistoryDAO {
    private String input;
    private String searchResult;
}
