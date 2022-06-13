package com.kis.searchaddress.dto.response.keyword;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class KeywordApiResponseDTO {
    private List<Document> documents;
    private Meta meta;                  //장소 메타데이터
}
