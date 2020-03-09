package com.example.demo.Mapping;

import com.example.demo.words.Words;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class WordsMap {
      private  static final Map<String, Words> wordsMap = new HashMap<>();
      private static final List<Words> wordsList = new ArrayList<Words>();

    static {
        wordMapValues();
    }
    private static void wordMapValues(){

        Words w1 = new Words("0990909 Son","Daughter","Antonym");
        Words w2 = new Words("  #$%^   Road","Street","Synonym");
        Words w3 = new Words("     )(*    Road","Avenue","Related");
        Words w4 = new Words("Synonym"      ,"Match","Related");
        Words w5 = new Words("Antonym","Synonym","Antonym");


        wordsList.add(w1);
        wordsList.add(w2);
        wordsList.add(w3);
        wordsList.add(w4);
        wordsList.add(w5);
 }
    public List<Words> getAllEntries() {

        List<Words> list = new ArrayList<>();
        list.addAll(wordsList);
        return list;
    }

}

