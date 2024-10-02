package com.example.petvitaoriginal.classes;

public class Procedimento {

    private String tipoProcedimento;
    private String dataProcedimento;
    private String nomeVeterinario;
    private String notasAdicionais;

    public Procedimento(String tipoProcedimento, String dataProcedimento, String nomeVeterinario, String notasAdicionais) {
        this.tipoProcedimento = tipoProcedimento;
        this.dataProcedimento = dataProcedimento;
        this.nomeVeterinario = nomeVeterinario;
        this.notasAdicionais = notasAdicionais;
    }

    public String getTipoProcedimento() {
        return tipoProcedimento;
    }

    public void setTipoProcedimento(String tipoProcedimento) {
        this.tipoProcedimento = tipoProcedimento;
    }

    public String getDataProcedimento() {
        return dataProcedimento;
    }

    public void setDataProcedimento(String dataProcedimento) {
        this.dataProcedimento = dataProcedimento;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nomeVeterinario = nomeVeterinario;
    }

    public String getNotasAdicionais() {
        return notasAdicionais;
    }

    public void setNotasAdicionais(String notasAdicionais) {
        this.notasAdicionais = notasAdicionais;
    }
}
