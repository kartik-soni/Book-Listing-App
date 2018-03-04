package com.example.kartik.booklistingapp;

/**
 * Created by KARTIK on 3/4/2018.
 */

public class Books {
    private String mAuthor;
    private String mTitle;
    public Books(String author,String title){
        mAuthor=author;
        mTitle=title;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getTitle(){
        return mTitle;
    }

}
