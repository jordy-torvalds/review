package com.kis.searchaddress.dto.response.keyword;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RegionInfo {
    private String[] region;        //질의어에서 인식된 지역의 리스트, ex) '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트
    private String keyword;         //질의어에서 지역 정보를 제외한 키워드, ex) '중앙로 맛집' 에서 '맛집'
    private String selected_region; //인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
}
