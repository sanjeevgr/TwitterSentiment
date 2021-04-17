/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cse408groupproject;

import java.util.HashMap;

public class SentimentalAnalysis {
    public SentimentalAnalysis(BagOfWords bag, HashMap<Integer,String> reverseLexicon, HashMap<String, Float> wordValue){

        float tweetscore = 0;


        for (int i = 0; i < bag.featureVector.length; i++) {
            String key = reverseLexicon.get(i);
            int count = bag.featureVector[i];
            float value = 0;
            
            if(count > 0 && wordValue.containsKey(key))
                tweetscore += (float) (count*wordValue.get(key));
            // else if no key it would multiply by 0
        }
        //Map < String, Float > sentiment = new HashMap < String, Float > ();
        //sentiment.put(tweet, tweetscore);
        System.out.print(tweetscore);
    }
}