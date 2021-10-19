package com.academy.ibm.nearatm.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.academy.ibm.nearatm.model.Sucursal;
import com.academy.ibm.nearatm.model.Address;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;


public class LocationsAPI {

    public  ArrayList<Sucursal> getObjectsFromJson(final ObjectNode jsonDocumentNode) {
        final ArrayList<Sucursal> documentObjects = new ArrayList<Sucursal>();

        for (final JsonNode header : (Iterable<JsonNode>) jsonDocumentNode::elements) {
            for (final JsonNode subheader : (Iterable<JsonNode>) header::elements) {
                for (final Map.Entry<String, JsonNode> field : (Iterable<Map.Entry<String, JsonNode>>) subheader::fields) {
                    Address address = new Address((field.getValue()).get(3).toString().replace("\"", ""), (field.getValue()).get(4).toString().replace("\"", ""));
                    //                                              String sucNumber,                       String sucType, Address , double sucLatitude, double sucLongitude
                   Sucursal suc = new Sucursal((field.getValue()).get(0).toString().replace("\"", ""), (field.getValue()).get(19).toString().replace("\"", ""), address, Double.parseDouble((field.getValue()).get(16).toString().replace("\"", "")), Double.parseDouble((field.getValue()).get(15).toString().replace("\"", "")));
                   
                   documentObjects.add(suc);
                }
            }
        }
        return documentObjects;
    }
   
    public String getLocations(){
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

    public String jsonClean(String str){
        if(str.startsWith("jsonCallback({\"Servicios\":")){
            str = str.substring(26, str.length());
            str = str.substring(0, str.length()-3);
        }
        return str;
    }
    
}
