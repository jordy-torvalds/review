package com.kis.searchaddress.dto.response.kakaoApi.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
//지번주소 상세 정보
public class Address {
    private String address_name;            //전체 지번 주소
    private String region_1depth_name;      //지역 1 Depth, 시도 단위
    private String region_2depth_name;      //지역 2 Depth, 구 단위
    private String region_3depth_name;      //지역 3 Depth, 동 단위
    private String region_3depth_h_name;    //지역 3 Depth, 행정동 명칭
    private String h_code;                  //행정 코드
    private String b_code;                  //법정 코드
    private String mountain_yn;             //산 여부, Y 또는 N
    private String main_address_no;         //지번 주번지
    private String sub_address_no;          //지번 부번지, 없을 경우 ""
    private String x;                       //X 좌표값, 경위도인 경우 longitude (경도)
    private String y;                       //Y 좌표값, 경위도인 경우 latitude (위도)
}
