
package com.urna.service;

public class VotarServlet {
    private int numero;
    private String nome;
    private String partido;
    private int totalVotos;

    public VotarServlet(int numero, String nome, String partido) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.totalVotos = 0;
    }

    public void incrementarVoto() {
        this.totalVotos++;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getTotalVotos() {
        return totalVotos;
    }
}