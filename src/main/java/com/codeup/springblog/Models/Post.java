package com.codeup.springblog.Models;

public class Post {
    private long id;
    private long auth_id;
    private String title;
    private String body;

    public Post(long auth_id, String title, String body) {
        this.auth_id = auth_id;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
