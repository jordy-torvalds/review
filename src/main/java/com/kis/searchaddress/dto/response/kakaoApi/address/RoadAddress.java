package com.kis.searchaddress.dto.response.kakaoApi.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
도로명주소 상세 정보
 */
@Getter
@ToString
@NoArgsConstructor
public class RoadAddress {
    private String address_name;        //전체 도로명 주소
    private String region_1depth_name;  //지역명1
    private String region_2depth_name;  //지역명2
    private String region_3depth_name;  //지역명3
    private String road_name;           //도로명
    private String underground_yn;      //지하 여부, Y 또는 N
    private String main_building_no;    //건물 본번
    private String sub_building_no;     //건물 부번, 없을 경우 ""
    private String building_name;       //건물 이름
    private String zone_no;             //우편번호(5자리)
    private String x;                   //X 좌표값, 경위도인 경우 longitude (경도)
    private String y;                   //Y 좌표값, 경위도인 경우 latitude (위도)
}
