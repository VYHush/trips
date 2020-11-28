package com.example.trips;

import java.util.Date;

public class Lugar implements Comparable<Lugar>{
    private String nome;
    private String latitude;
    private String longitude;
    private String dataCadastro;
    private String id;
    private Date dataAtual;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Lugar(String nome, String latitude, String longitude, String dataCadastro, Date dataAtual) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataCadastro = dataCadastro;
        this.dataAtual = dataAtual;
    }
    public Lugar(){}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    @Override
    public int compareTo(Lugar l) {
        return this.dataAtual.compareTo(l.dataAtual);
    }
}
