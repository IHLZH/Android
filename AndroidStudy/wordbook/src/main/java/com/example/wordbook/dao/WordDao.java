package com.example.wordbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordbook.entity.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    long[] insert(Word... word);

    @Delete
    int delete(Word word);

    @Update
    int update(Word word);

    @Query("select * from tb_words where word like '%' || :word || '%'")
    List<Word> queryByWord(String word);

    @Query("select * from tb_words")
    List<Word> queryAll();



}
