package com.example.trips;

public class Lugar {
    private String nome;
    private String latitude;
    private String longitude;
    private String dataCadastro;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Lugar(String nome, String latitude, String longitude, String dataCadastro) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataCadastro = dataCadastro;
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
}
