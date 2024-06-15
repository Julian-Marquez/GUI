package com.example.audioplayer;

public class Song {
    private String filePath;
    private String title;

    public Song(String filePath, String title) {
        this.filePath = filePath;
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }
}



