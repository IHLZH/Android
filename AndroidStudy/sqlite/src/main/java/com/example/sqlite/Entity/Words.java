package com.example.sqlite.Entity;

public class Words {
    private Integer id;
    private String word;
    private String translation;
    public Words(){};
    public Words(String word, String translation){
        this.word = word;
        this.translation = translation;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getId() {
        return id;
    }
    public String getTranslation() {
        return translation;
    }

    public String getWord() {
        return word;
    }
}
