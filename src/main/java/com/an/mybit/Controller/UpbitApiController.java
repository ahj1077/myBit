package com.an.mybit.Controller;

import java.util.List;

import com.an.mybit.Dto.CurrentCoinInfoDTO;
import com.an.mybit.Dto.MarketCodeDTO;
import com.an.mybit.Dto.MinuteCandleDTO;
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
    
}