package com.an.mybit.Controller;

import java.util.List;

import com.an.mybit.Dto.MarketCodeDTO;
import com.an.mybit.Service.UpbitApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}