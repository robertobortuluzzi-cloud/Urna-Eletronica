package com.urna.model.repository;

import com.urna.service.VotarServlet;
import java.util.ArrayList;
import java.util.List;

public class CandidatoRepository {

    private List<VotarServlet> candidatos = new ArrayList<>();

    // Salva/cadastra um novo candidato na lista
    public void salvar(VotarServlet candidato) {
        candidatos.add(candidato);
    }

    // Retorna todos os candidatos
    public List<VotarServlet> buscarTodos() {
        return candidatos;
    }

    // Busca um candidato pelo número
    public VotarServlet buscarPorNumero(int numero) {
        for (VotarServlet candidato : candidatos) {
            if (candidato.getNumero() == numero) {
                return candidato;
            }
        }
        return null; // Retorna null se não encontrar
    }
}