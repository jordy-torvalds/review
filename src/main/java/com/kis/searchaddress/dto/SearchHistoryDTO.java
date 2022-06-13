package com.kis.searchaddress.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SearchHistoryDTO {
    private String input;
    private String searchResult;
}
