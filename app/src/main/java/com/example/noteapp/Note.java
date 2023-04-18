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
<<<<<<< HEAD
=======

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setContent(String newContent) {
        this.content = newContent;

    }


}
>>>>>>> 8684952d78c6877f63a3b51b8adb34fd2c0ee6ea

    public void setTitle(String newTitle) {
    }

    public void setContent(String newContent) {
    }

}