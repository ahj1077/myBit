package com.an.mybit.Dto;

import lombok.Data;

@Data
public class DayCandleDTO {

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
    private String	prev_closing_price;
    private String	change_price;
    private String	change_rate;
    private String	converted_trade_price;

}
