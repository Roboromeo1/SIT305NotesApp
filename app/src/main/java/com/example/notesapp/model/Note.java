package com.example.notesapp.model;

public class Note {
    private int id;
    private String name, content;

    //Get ID
    public int getId() {
        return id;
    }

    //Get and Set Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Get and Set Content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //Constructor
    public Note(String name, String content) {
        this.name = name;
        this.content = content;
    }


}
