package com.example.petvitaoriginal.classes;

public class Pets {

    private String dataPetName;
    private String dataPetType;
    private String dataPetGender;
    private String dataImage;
    private String key; // ID único do pet

    // Construtor padrão necessário para Firebase
    public Pets() {
    }

    // Construtor principal
    public Pets(String dataPetName, String dataPetType, String dataPetGender, String dataImage) {
        this.dataPetName = dataPetName;
        this.dataPetType = dataPetType;
        this.dataPetGender = dataPetGender;
        this.dataImage = dataImage;
    }

    // Construtor adicional que inclui a key
    public Pets(String dataPetName, String dataPetType, String dataPetGender, String dataImage, String key) {
        this.dataPetName = dataPetName;
        this.dataPetType = dataPetType;
        this.dataPetGender = dataPetGender;
        this.dataImage = dataImage;
        this.key = key; // Inicializa o ID do pet
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
