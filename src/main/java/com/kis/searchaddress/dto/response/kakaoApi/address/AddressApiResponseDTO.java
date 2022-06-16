package com.kis.searchaddress.dto.response.kakaoApi.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class AddressApiResponseDTO {

    @JsonProperty(value="documents")
    private List<AddressDocument> addressDocuments;
    private Meta            meta;       //로컬 메타데이터
}
