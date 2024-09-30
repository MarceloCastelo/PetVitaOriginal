package com.example.petvitaoriginal.classes;

public class Vacina {
    private String nome;
    private String data;
    private String descricao;

    public Vacina(String nome, String data, String descricao) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }
}
