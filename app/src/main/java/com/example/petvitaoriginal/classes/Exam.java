package com.example.petvitaoriginal.classes;

public class Exam {
    private String name;
    private String date;
    private String description;

    public Exam(String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

