/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper.anime1;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ankush
 */
public class RecentlyUpdated {
    
    private final String url="http://www.anime1.com/";
    private JSONObject  json;
 
    public RecentlyUpdated(){
        json= new JSONObject();
     }
    
        
    public JSONObject getAnimes() throws UnknownHostException,
            JSONException,     IOException  { // this exception is throws when no internet connection
      
        startScraping();  
        return json;
     }
     
    private void startScraping()throws IOException{
        JSONArray array = new JSONArray();
            
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div.new-list").first().select("div.nl-item");
        elements.stream().forEach((element) -> {
                        
              //  System.out.println(  "name"+  element.text());

            System.out.println(  "img"+  element.select("img").attr("src"));

            System.out.println(  "url"+  element.select("a[href]").first().attr("href"));
                        
            String name=element.text();
            String episode="";
            
            if(name.contains("Episode")){
                String data[]= name.split(" ");
                Boolean flag=false;
                
                for(String word: data)
                 {
                    if(word.equalsIgnoreCase("episode")){
                        flag=true;
                    }
                    
                    if(flag){
                        episode=episode+" "+word;
                    }else
                        name=name+" "+ word;
                  
                } 
            }

            episode= reformatEpisodeString(episode);
            
            
           System.out.println(  "name"+ name);
           System.out.println(  "wp"+ episode);

            String image=element.select("img").attr("src");
            String link= element.select("a[href]").first().attr("href");
            
            
            JSONObject item= new JSONObject();
            try {    		 
                item.put("name", name);
          
	    item.put("image",image);    		 
	   // item.put("latest episode", ls.getEpisode());
	    item.put("url", link);
             } catch (JSONException ex) {
                Logger.getLogger(RecentlyUpdated.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
	    array.put(item);
            
            
            
        });  
       
    }
    
    
    private String reformatEpisodeString(String ep){
        ep=ep.trim();
        String data[]=ep.split(" ");
        try {
            return data[0] + data[1];
        } catch (Exception e) {
           return ep;
        }
    }

}
