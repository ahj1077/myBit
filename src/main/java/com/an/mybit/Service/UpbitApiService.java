package com.an.mybit.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.an.mybit.Dto.MarketCodeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.stereotype.Service;

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

}