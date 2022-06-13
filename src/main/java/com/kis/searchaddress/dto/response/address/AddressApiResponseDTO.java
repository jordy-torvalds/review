package com.kis.searchaddress.dto.response.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class AddressApiResponseDTO {
    private List<Document>  documents;
    private Meta            meta;       //로컬 메타데이터
}
