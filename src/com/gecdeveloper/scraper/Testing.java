/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gecdeveloper.scraper;

import com.gecdeveloper.scraper.anime1.RecentlyUpdated;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ankush
 */
public class Testing {
     
     public static void main(String[] args){
         RecentlyUpdated s= new RecentlyUpdated();
         try {
             JSONObject animes = s.getAnimes();
         } catch (JSONException ex) {
             Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
         }
    
   
    }
   
}
