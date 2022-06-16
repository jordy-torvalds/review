package com.kis.searchaddress.dto.response.kakaoApi.keyword;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class KeywordApiResponseDTO {

    @JsonProperty(value="documents")
    private List<KeywordDocument> keywordDocuments;
    private Meta meta;                  //장소 메타데이터
}
