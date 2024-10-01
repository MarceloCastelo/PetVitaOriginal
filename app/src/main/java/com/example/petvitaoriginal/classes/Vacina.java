package com.example.petvitaoriginal.classes;

public class Vacina {
    private String codigo;
    private String tipo;
    private String data;
    private String numeroDoses;
    private String doseAplicada;
    private String nomeVeterinario;
    private String lote;
    private String local;
    private String notas;

    public Vacina(String codigo, String tipo, String data, String numeroDoses, String doseAplicada, String nomeVeterinario, String lote, String local, String notas) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.data = data;
        this.numeroDoses = numeroDoses;
        this.doseAplicada = doseAplicada;
        this.nomeVeterinario = nomeVeterinario;
        this.lote = lote;
        this.local = local;
        this.notas = notas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNumeroDoses() {
        return numeroDoses;
    }

    public void setNumeroDoses(String numeroDoses) {
        this.numeroDoses = numeroDoses;
    }

    public String getDoseAplicada() {
        return doseAplicada;
    }

    public void setDoseAplicada(String doseAplicada) {
        this.doseAplicada = doseAplicada;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nomeVeterinario = nomeVeterinario;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
