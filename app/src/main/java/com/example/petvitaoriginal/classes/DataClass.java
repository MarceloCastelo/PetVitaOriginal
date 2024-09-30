package com.example.petvitaoriginal.classes;

public class DataClass {

    private String dataPetName;
    private String dataPetType;
    private String dataPetGender;
    private String dataImage;
    private String key; // renomeado para seguir a convenção de nomes

    // Construtor padrão necessário para Firebase
    public DataClass() {
    }

    // Construtor principal
    public DataClass(String dataPetName, String dataPetType, String dataPetGender, String dataImage) {
        this.dataPetName = dataPetName;
        this.dataPetType = dataPetType;
        this.dataPetGender = dataPetGender;
        this.dataImage = dataImage;
    }

    // Construtor adicional com key
    public DataClass(String dataPetName, String dataPetType, String dataPetGender, String dataImage, String key) {
        this.dataPetName = dataPetName;
        this.dataPetType = dataPetType;
        this.dataPetGender = dataPetGender;
        this.dataImage = dataImage;
        this.key = key;
    }

    // Getters
    public String getDataPetName() {
        return dataPetName;
    }

    public String getDataPetType() {
        return dataPetType;
    }

    public String getDataPetGender() {
        return dataPetGender;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getKey() {
        return key;
    }

    // Setters
    public void setDataPetName(String dataPetName) {
        this.dataPetName = dataPetName;
    }

    public void setDataPetType(String dataPetType) {
        this.dataPetType = dataPetType;
    }

    public void setDataPetGender(String dataPetGender) {
        this.dataPetGender = dataPetGender;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
