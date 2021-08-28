package com.an.mybit.Dto;

import lombok.Data;

@Data
public class MarketCodeDTO {

    private String market;          //업비트에서 제공중인 시장정보
    private String korean_name;     //거래대상 암호화폐 한글명
    private String english_name;    //거래대상 암호화폐 영문명
    private String market_warning;  //유의 종목 여부 (NONE / CAUTION)
}
