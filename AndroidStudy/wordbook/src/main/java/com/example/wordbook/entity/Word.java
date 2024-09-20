package com.example.wordbook.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_words")
public class Word {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "word")
    private String word;
    @ColumnInfo(name = "trans")
    private String translation;

    public Word() {

    }

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public Word(Integer id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
