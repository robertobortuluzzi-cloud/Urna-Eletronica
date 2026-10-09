package com.urna.service;

import com.urna.repository.CandidatoRepositoryTxt;
import java.util.List;

public class UrnaService {

    private CandidatoRepositoryTxt repository;
    private int votosBrancos = 0;
    private int votosNulos = 0;

    public UrnaService() {
        this.repository = new CandidatoRepositoryTxt();
    }

    public UrnaService(CandidatoRepositoryTxt repository) {
        this.repository = repository;
    }

    public boolean registrarVoto(int numero) {
        if (numero == 0) {
            votosBrancos++;
            return true;
        }

        List<VotarServlet> candidatos = repository.buscarTodos();
        for (VotarServlet candidato : candidatos) {
            if (candidato.getNumero() == numero) {
                candidato.incrementarVoto();
                repository.salvarTodos(candidatos);
                return true;
            }
        }

        votosNulos++;
        return false;
    }

    public void cadastrarCandidato(VotarServlet candidato) {
        repository.salvar(candidato);
    }

    public List<VotarServlet> getCandidatos() {
        return repository.buscarTodos();
    }

    public VotarServlet getVencedor() {
        List<VotarServlet> candidatos = getCandidatos();
        if (candidatos == null || candidatos.isEmpty()) {
            return null;
        }

        VotarServlet vencedor = null;
        int maxVotos = 0;

        for (VotarServlet c : candidatos) {
            if (c.getTotalVotos() > maxVotos) {
                maxVotos = c.getTotalVotos();
                vencedor = c;
            }
        }
        return vencedor;
    }

    public int getVotosBrancos() {
        return votosBrancos;
    }

    public int getVotosNulos() {
        return votosNulos;
    }

    public int getTotalVotosGerais() {
        int totalCandidatos = 0;
        List<VotarServlet> candidatos = getCandidatos();
        if (candidatos != null) {
            for (VotarServlet c : candidatos) {
                totalCandidatos += c.getTotalVotos();
            }
        }
        return totalCandidatos + votosBrancos + votosNulos;
    }
}