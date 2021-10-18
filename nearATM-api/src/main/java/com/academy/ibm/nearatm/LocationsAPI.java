package com.academy.ibm.nearatm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

public class LocationsAPI {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String response = getLocations();
        response = jsonClean(response);

        JsonNode actualObj = mapper.readTree(response);
        ((ObjectNode)actualObj).remove(Arrays.asList("800", "500", "110", "400", "600", "900", "950"));
      // mapper.writeValue(new File("./data2.json"), actualObj);
        
        
    }


    public static String getLocations(){
        try{
            String locationsJson = "https://www.banamex.com/localizador/jsonP/json5.json";
            URL url = new URL(locationsJson);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String i = "", response = "";

            while ((i = in.readLine()) != null) {
                response = response + i;
            }
            in.close();

            return response;
        
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return new String();
    }

    public static String jsonClean(String str){
        if(str.startsWith("jsonCallback({\"Servicios\":")){
            str = str.substring(26, str.length());
            str = str.substring(0, str.length()-3);
        }
        return str;
    }
    
}
