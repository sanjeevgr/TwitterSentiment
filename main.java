package com.mycompany.cse408groupproject;

import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        // Make the stop words list
        ArrayList<String> stopWords = new ArrayList<String>();
        try{
            File file = new File("C:\\Users\\nicpa\\Documents\\NetBeansProjects\\CSE408GroupProject\\src\\main\\java\\com\\mycompany\\cse408groupproject\\stopWords.txt");
            stopWords = new ArrayList<String>(Arrays.asList(Files.readString(file.toPath()).split("\n")));
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        // Grab the folder names
        File mainFolder = new File("C:\\Users\\nicpa\\Documents\\NetBeansProjects\\CSE408GroupProject\\src\\main\\java\\com\\mycompany\\cse408groupproject\\tweets");
        File[] listOfFolders = mainFolder.listFiles();
        
        
        //System.out.println(stopWords);
        //System.out.println(Arrays.toString(listOfFolders));
        
        // Make the lexicon
        HashMap<String, Integer> lexicon = new HashMap<String, Integer>();
        for(File folder : listOfFolders){
            try{
                BagOfWords.MakeVocabulary(folder, lexicon, stopWords);
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
        
        // Make bags for each tweet
        ArrayList<BagOfWords> bags = new ArrayList<BagOfWords>();
        for(File folder : listOfFolders){
            File[] tweets = folder.listFiles();
            for(File tweet : tweets){
                try{
                    bags.add(new BagOfWords(tweet,lexicon));
                }
                catch(IOException e){
                    System.out.println(e);
                }
            }
        }
        
        
        // Make the word with string value HashMap
        HashMap<String,Float> wordValue = new HashMap<String,Float>();
        try{
            File file = new File("C:\\Users\\nicpa\\Documents\\NetBeansProjects\\CSE408GroupProject\\src\\main\\java\\com\\mycompany\\cse408groupproject\\wordWithStrength.txt");
            String[] parts = Files.readString(file.toPath()).split("\t");
            ArrayList<String> fullParts = new ArrayList<String>();
            for(String part: parts){
                //System.out.println(part);
                String[] pieces = part.split("\n");
                for(String piece : pieces){
                    fullParts.add(piece);
                }
            }

            for(int i = 0; i < parts.length;i = i + 2){
                wordValue.put(fullParts.get(i), Float.parseFloat(fullParts.get(i + 1)));
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        

        // Make a reversed lexicon hashmap for easy word lookups
        HashMap<Integer,String> reverseLexicon = new HashMap<Integer,String>();
        for (Map.Entry<String, Integer> entry : lexicon.entrySet()) {
            reverseLexicon.put(entry.getValue(),entry.getKey());
        }
        
        
        // Print sentiment analysis results
        int count = 0;
        for(BagOfWords bag: bags){
            System.out.print(count++ + ": ");
            new SentimentalAnalysis(bag, reverseLexicon, wordValue);
            System.out.print("\n");
        }
    }
}
