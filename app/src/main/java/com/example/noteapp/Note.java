package com.example.noteapp;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String content;
    private String _id;

    public Note(String title, String content, String _id) {
        this.title = title;
        this.content = content;
        this._id = _id;

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

    public String getId() {
        return _id;
    }

}

