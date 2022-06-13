package com.kis.searchaddress.dto.response.address;

/*
address_name의 값의 타입(Type). REGION(지명), ROAD(도로명), REGION_ADDR(지번 주소), ROAD_ADDR (도로명 주소) 중 하나
 */
public enum AddressType {
    REGION, ROAD, REGION_ADDR, ROAD_ADDR
}
