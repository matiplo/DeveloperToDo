package com.example.ppp.developertodo.implementation.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ppp on 2018-02-08.
 */

public class TodoDownloaded {
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mText;

    public TodoDownloaded(int mId, String mText) {
        this.mId = mId;
        this.mText = mText;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
