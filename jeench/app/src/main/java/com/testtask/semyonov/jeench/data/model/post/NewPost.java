package com.testtask.semyonov.jeench.data.model.post;

public class NewPost
{
    private int userId;
    private String title;
    private String body;

    public NewPost( final int userId, final String title, final String body ){
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
