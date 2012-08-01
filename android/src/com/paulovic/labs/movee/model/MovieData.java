package com.paulovic.labs.movee.model;

public class MovieData {
    private String mTitle;
    private String mSubtitle;
    private String mComment;
    private int mDrawableScene;

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
}
