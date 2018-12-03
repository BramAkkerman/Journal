package com.example.brama.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private String title;
    private String content;
    private String mood;
    private String date;

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }
}