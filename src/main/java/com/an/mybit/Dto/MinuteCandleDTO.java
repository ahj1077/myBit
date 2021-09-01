package com.an.mybit.Dto;

import lombok.Data;

@Data
public class MinuteCandleDTO {

    private String	market;
    private String	candle_date_time_utc;
    private String	candle_date_time_kst;
    private String	opening_price;
    private String	high_price;
    private String	low_price;
    private String	trade_price;
    private String	timestamp;
    private String	candle_acc_trade_price;
    private String	candle_acc_trade_volume;
    private String	unit;
}
