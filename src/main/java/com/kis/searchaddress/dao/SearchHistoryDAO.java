package com.kis.searchaddress.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoryDAO {
    private String input;
    private String searchResult;
}
