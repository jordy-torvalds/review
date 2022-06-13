package com.kis.searchaddress.dto.response.keyword;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Document {
    private String id;                  //장소 ID
    private String place_name;          //장소명, 업체명
    private String category_name;       //카테고리 이름
    private String category_group_code; //중요 카테고리만 그룹핑한 카테고리 그룹 코드
    private String category_group_name; //중요 카테고리만 그룹핑한 카테고리 그룹명
    private String phone;               //전화번호
    private String address_name;        //전체 지번 주소
    private String road_address_name;   //전체 도로명 주소
    private String x;                   //X 좌표값, 경위도인 경우 longitude (경도)
    private String y;                   //Y 좌표값, 경위도인 경우 latitude (위도)
    private String place_url;           //장소 상세페이지 URL
    private String distance;            //중심좌표까지의 거리. 단, x,y 파라미터를 준 경우에만 존재. 단위는 meter
}
