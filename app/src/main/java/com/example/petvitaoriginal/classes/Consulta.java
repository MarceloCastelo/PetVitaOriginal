package com.example.petvitaoriginal.classes;

public class Consulta {

    private String tipoConsulta;
    private String dataConsulta;
    private String nomeVeterinario;
    private String observacoes;

    public Consulta(String tipoConsulta, String dataConsulta, String nomeVeterinario, String observacoes) {
        this.tipoConsulta = tipoConsulta;
        this.dataConsulta = dataConsulta;
        this.nomeVeterinario = nomeVeterinario;
        this.observacoes = observacoes;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nomeVeterinario = nomeVeterinario;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
