package com.example.demo.controller;

import com.example.demo.words.Words;
import com.example.demo.Mapping.WordsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;


@Controller
public class MainController {

@Autowired
WordsMap wordMapsInMain = new WordsMap();
    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome ";
    }

    @RequestMapping(value = "/entries",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Words> getEntries(){
        List<Words> list = wordMapsInMain.getAllEntries();
        return list;

    }
    @RequestMapping(value = "/filter",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List <Words> filterEntries() {
        List<Words> list = wordMapsInMain.getAllEntries();
        List<Words> filteredList = list
                .stream().filter(p -> p.getRelation().equals(p.getRelation())).collect(Collectors.toList());
              //.stream().filter(p -> p.getRelation().equals("Antonym")).collect(Collectors.toList());
        return (List<Words>) filteredList;
    }

    @RequestMapping(value = "/swap",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List <Words> swapEntries() {
        List<Words> list = wordMapsInMain.getAllEntries();
        List<Words> swappedList = new ArrayList<>();
        swappedList = list.stream()
                .map(w -> new Words( w.getWord2() , w.getWord1(),w.getRelation()))
                .collect(Collectors.toList());
        return  swappedList;
    }
    @RequestMapping(value = "/lowercase",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List <Words> lowercaseEntries() {
        List<Words> list = wordMapsInMain.getAllEntries();
        List<Words> lowerCaseList = new ArrayList<>();
        lowerCaseList = list.stream()
                .map(w -> new Words( w.getWord1().toLowerCase() , w.getWord2().toLowerCase(),w.getRelation().toLowerCase()))
                .collect(Collectors.toList());
        return  lowerCaseList;
    }

    @RequestMapping(value = "/limitchar",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List <Words> limitCharEntries() {
        List<Words> list = wordMapsInMain.getAllEntries();
    List<Words> limitchar = new ArrayList<>();
    limitchar = list.stream().map(w -> new Words( w.getWord1().replaceAll("[^a-zA-Z \\s]", ""),
            w.getWord2().replaceAll("[^a-zA-Z \\s]", ""),
            w.getRelation().replaceAll("[^a-zA-Z \\s]", "")))
            .collect(Collectors.toList());
    return  limitchar;
    }
    @RequestMapping(value = "/addwords",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List <Words> addWords() {
        List<Words> list = wordMapsInMain.getAllEntries();
        List<Words> addWords = new ArrayList<>();

        addWords = list.stream().map(w -> new Words( w.getWord1(),
                w.getWord2(),
                w.getRelation()))
                .collect(Collectors.toList());
        return  addWords;
    }

    @RequestMapping(value = "/addAnOtherRelation",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void addAnOtherRelation(final String word1, final String word2 , final String relation) {
        List<Words> list = wordMapsInMain.getAllEntries();
        boolean addAnOtherRelation;
        addAnOtherRelation = list.stream().anyMatch(o -> (o.getWord1().equals(word1) && o.getWord2().equals(word2)) && !o.getRelation().equals(relation));
        if (addAnOtherRelation) {
            throw new RuntimeException("Words with different relation");
        }
    }

    @RequestMapping(value = "/addAnOtherRelationInv",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void addAnOtherRelationInv(final String word1, final String word2 , final String relation) {
        List<Words> list = wordMapsInMain.getAllEntries();
        boolean addAnOtherRelationInv;
        addAnOtherRelationInv = list.stream().anyMatch(o -> (o.getWord1().equals(word2) && o.getWord2().equals(word1)) && !o.getRelation().equals(relation));
        if (addAnOtherRelationInv) {
            throw new RuntimeException("Words with different relation");
        }
    }

}

