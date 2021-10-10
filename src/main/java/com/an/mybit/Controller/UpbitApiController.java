package com.an.mybit.Controller;

import java.util.List;

import com.an.mybit.Dto.CurrentCoinInfoDTO;
import com.an.mybit.Dto.DayCandleDTO;
import com.an.mybit.Dto.MarketCodeDTO;
import com.an.mybit.Dto.MinuteCandleDTO;
import com.an.mybit.Dto.MonthCandleDTO;
import com.an.mybit.Dto.WeekCandleDTO;
import com.an.mybit.Service.UpbitApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

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