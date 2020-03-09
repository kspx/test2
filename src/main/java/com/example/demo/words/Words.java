package com.example.demo.words;

import javax.validation.constraints.NotNull;

public class Words {

        @NotNull
        private String word1;
        @NotNull
        private String word2;
        private String relation;

       public Words(String word1, String word2, String relation){
            this.word1=word1;
            this.word2=word2;
            this.relation=relation;
        }
    public Words(String word1, String word2){
        this.word1=word1;
        this.word2=word2;
    }
        public String getWord1(){
            return  word1;
        }

        public String getWord2(){
            return word2;
        }

        public String getRelation(){
            return relation;
        }

        public String setWord1(@NotNull String word1) {return this.word1=word1;}

        public String setWord2(@NotNull String word2) {return this.word2=word2;}

        public String setRelation(@NotNull String relation) {return this.relation=relation;}




}
