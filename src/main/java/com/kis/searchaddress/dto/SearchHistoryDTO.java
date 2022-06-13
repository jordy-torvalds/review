package com.kis.searchaddress.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoryDTO {
    private String input;
    private String searchResult;
}
