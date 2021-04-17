package com.mycompany.cse408groupproject;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class BagOfWords{
    
    public String fileName;
    public int[] featureVector;
    
    public BagOfWords(File file, HashMap<String,Integer> voc) throws IOException {
        
        fileName = file.getName();

        String content = Files.readString(file.toPath());
        String[] words = content.split("\\W+");
        
        featureVector = new int[voc.size()];
        for(String word : words){
            if(voc.containsKey(word)){
                featureVector[voc.get(word)]++;
            }
        }
    }
    
    //returns a string array that contains each word in the folder
    static HashMap<String,Integer> MakeVocabulary(File folder, HashMap<String, Integer> vocabulary, ArrayList<String> stopWords) throws IOException {
        File[] listOfFiles = folder.listFiles();
        
        int index = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String content = Files.readString(file.toPath());
                String[] words = content.split("\\W+");
                //System.out.println(Arrays.toString(words));
                // Add each unique word to the vocabulary and mark down a pseudo-index value
                for(String word : words){
                    word = word.toLowerCase();
                    if(!stopWords.contains(word) && !vocabulary.containsKey(word)){
                        vocabulary.put(word,index++);
                    }
                }
            }
        }
        return vocabulary;
    }
}