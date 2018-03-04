package com.example.kartik.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KARTIK on 3/4/2018.
 */

public class Books implements Parcelable {
    private String mAuthor;
    private String mTitle;

    public Books(String author, String title) {
        mAuthor = author;
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    protected Books(Parcel in) {
        mAuthor = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mAuthor);
        parcel.writeString(mTitle);
    }
}