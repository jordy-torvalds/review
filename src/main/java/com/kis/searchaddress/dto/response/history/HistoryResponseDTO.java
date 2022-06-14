package com.kis.searchaddress.dto.response.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponseDTO {
    private String input;
    private String searchResult;
}
