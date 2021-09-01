package com.an.mybit.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.an.mybit.Dto.CurrentCoinInfoDTO;
import com.an.mybit.Dto.MarketCodeDTO;
import com.an.mybit.Dto.MinuteCandleDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpbitApiService {
    
    /**
     *  업비트에서 거래 가능한 마켓 목록 조회
     * 
     * @return
     * @throws MalformedURLException
     */
    public List<MarketCodeDTO> retrieveMarketCode(Boolean isDetails) {
        
        List<MarketCodeDTO> MarketCodeDTOList = null;
        BufferedReader br = null;
        String jsonData = null;

        Gson gson = new GsonBuilder().create();

        String baseUrl = "https://api.upbit.com/v1/market/all";

        if(isDetails)
            baseUrl += "?isDetails=true";        

        try {
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");
            conn.connect();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            while((jsonData = br.readLine()) != null){
                MarketCodeDTOList = gson.fromJson(jsonData, new TypeToken<List<MarketCodeDTO>>(){}.getType());
            }            
        }
        catch (Exception e){
            e.printStackTrace();
        } 
        finally{
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {                
                e.printStackTrace();
            }            
        }       

        List<MarketCodeDTO> ret = new ArrayList<>();

        for(MarketCodeDTO dto : MarketCodeDTOList){
            if(dto.getMarket().startsWith("KRW")){
                ret.add(dto);
            }
        }
        
        return ret;
    }

    /**
     *  마켓코드로 현재 코인 정보 조회
     * 
     * @return
     * @throws 
     */
    public List<CurrentCoinInfoDTO> retrieveCurrentCoinInfo(String marketCode){

        String baseUrl = "https://api.upbit.com/v1/ticker?markets=" + marketCode;
        BufferedReader br = null;
        String jsonData = null;

        List<CurrentCoinInfoDTO> CurrentCoinInfoDTOList = null;

        Gson gson = new GsonBuilder().create();

        URL url;
        try {
            url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");
            conn.connect();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while((jsonData = br.readLine()) != null){
                CurrentCoinInfoDTOList = gson.fromJson(jsonData, new TypeToken<List<CurrentCoinInfoDTO>>(){}.getType());
            }     

        } catch (Exception e) {            
            e.printStackTrace();
        }

        return CurrentCoinInfoDTOList;
    }

    /**
     *  마켓코드로 분 캔들 조회
     * 
     * @return
     * @throws 
     */
    public List<MinuteCandleDTO> retrieveMinuteCandles(String unit, String market, String to, String count){

        String baseUrl = "https://api.upbit.com/v1/candles/minutes/" + unit + "?market=" + market;
        if(StringUtils.hasText(to)){
            baseUrl = baseUrl + "&to=" + to;
        }
        if(StringUtils.hasText(count)){
            baseUrl = baseUrl + "&count=" + count;
        }
        
        BufferedReader br = null;
        String jsonData = null;

        List<MinuteCandleDTO> MinuteCandleDTOList = null;

        Gson gson = new GsonBuilder().create();

        URL url;
        try {
            url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");
            conn.connect();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while((jsonData = br.readLine()) != null){
                MinuteCandleDTOList = gson.fromJson(jsonData, new TypeToken<List<MinuteCandleDTO>>(){}.getType());
            }     

        } catch (Exception e) {            
            e.printStackTrace();
        }

        return MinuteCandleDTOList;
    }
}