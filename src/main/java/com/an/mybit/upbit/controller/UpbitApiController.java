package com.an.mybit.upbit.controller;

import com.an.mybit.upbit.dto.*;
import com.an.mybit.upbit.service.UpbitApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class UpbitApiController {

    @Autowired
    private UpbitApiService upbitApiService;
    
    @CrossOrigin
    @GetMapping("/marketCode")
    public List<MarketCodeDTO> retrieveMarketCode(Boolean isDetails){


        return upbitApiService.retrieveMarketCode(isDetails);
    }

    @CrossOrigin
    @GetMapping("/currentCoinInfo")
    public List<CurrentCoinInfoDTO> retrieveCurrentCoinInfo(String marketCode){

        return upbitApiService.retrieveCurrentCoinInfo(marketCode);
    }

    @CrossOrigin
    @GetMapping("/minuteCandles/{unit}")
    public List<MinuteCandleDTO> retrieveMinuteCandles(@PathVariable String unit, @RequestParam String market, @RequestParam(required = false) String to, @RequestParam(required = false) String count){

        return upbitApiService.retrieveMinuteCandles(unit, market, to, count);
    }

    @CrossOrigin
    @GetMapping("/dayCandles")
    public List<DayCandleDTO> retrieveDayCandles(@RequestParam String market, @RequestParam(required = false) String to, @RequestParam(required = false) String count, @RequestParam(required = false)  String convertingPriceUnit){

        return upbitApiService.retrieveDayCandles(market, to, count, convertingPriceUnit);
    }

    @CrossOrigin
    @GetMapping("/weekCandles")
    public List<WeekCandleDTO> retrieveWeekCandles(@RequestParam String market, @RequestParam(required = false) String to, @RequestParam(required = false) String count){

        return upbitApiService.retrieveWeekCandles(market, to, count);
    }

    @CrossOrigin
    @GetMapping("/monthCandles")
    public List<MonthCandleDTO> retrieveMonthCandles(@RequestParam String market, @RequestParam(required = false) String to, @RequestParam(required = false) String count){        

        return upbitApiService.retrieveMonthCandles(market, to, count);
    }
    
}