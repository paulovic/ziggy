package com.paulovic.labs.movee.model;

public class MovieData {
    private String mTitle;
    private String mSubtitle;
    private String mComment;
    private int mDrawableScene;
    private int mId;

    public MovieData(String text, String subtitle, String comment, int resScene){
        mTitle = text;
        mSubtitle = subtitle;
        mComment = comment;
        mDrawableScene = resScene;
    }

    public int getDrawableResScene() {
        return mDrawableScene;
    }
    
    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public String getComment() {
        return mComment;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
