package com.example.noteapp;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String newTitle) {
    }

    public void setContent(String newContent) {
    }

}