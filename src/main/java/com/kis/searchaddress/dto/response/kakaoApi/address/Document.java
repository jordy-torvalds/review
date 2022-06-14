package com.kis.searchaddress.dto.response.kakaoApi.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Document {
    private String address_name;        //전체 지번 주소 또는 전체 도로명 주소. 입력에 따라 결정됨
    private String address_type;        //address_name의 값의 타입(Type). REGION(지명), ROAD(도로명), REGION_ADDR(지번 주소), ROAD_ADDR (도로명 주소) 중 하나
    private String x;                   //X 좌표값, 경위도인 경우 longitude (경도)
    private String y;                   //Y 좌표값, 경위도인 경우 latitude (위도)
    private Address address;            //지번주소 상세 정보
    private RoadAddress road_address;   //도로명주소 상세 정보
}
