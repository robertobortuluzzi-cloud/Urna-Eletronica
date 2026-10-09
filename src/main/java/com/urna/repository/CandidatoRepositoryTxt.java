package com.urna.repository; // Verifique se o pacote no seu projeto é com.urna.repository ou com.urna.model.repository

import com.urna.service.VotarServlet;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoRepositoryTxt {

    private String caminhoArquivo = "candidatos.txt";

    public CandidatoRepositoryTxt() {
        criarArquivoSeNaoExistir();
    }

    // Construtor necessário para a chamada new CandidatoRepositoryTxt(caminho) do JSP
    public CandidatoRepositoryTxt(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        criarArquivoSeNaoExistir();
    }

    private void criarArquivoSeNaoExistir() {
        try {
            File file = new File(caminhoArquivo);
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<VotarServlet> buscarTodos() {
        List<VotarServlet> candidatos = new ArrayList<>();
        File file = new File(caminhoArquivo);

        if (!file.exists()) {
            return candidatos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    int numero = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();
                    String partido = partes[2].trim();
                    int totalVotos = partes.length > 3 ? Integer.parseInt(partes[3].trim()) : 0;

                    VotarServlet candidato = new VotarServlet(numero, nome, partido);
                    for (int i = 0; i < totalVotos; i++) {
                        candidato.incrementarVoto();
                    }

                    candidatos.add(candidato);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return candidatos;
    }

    public VotarServlet buscarPorNumero(int numero) {
        for (VotarServlet candidato : buscarTodos()) {
            if (candidato.getNumero() == numero) {
                return candidato;
            }
        }
        return null;
    }

    public void salvar(VotarServlet candidato) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            String linha = candidato.getNumero() + ";" +
                    candidato.getNome() + ";" +
                    candidato.getPartido() + ";" +
                    candidato.getTotalVotos();
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarTodos(List<VotarServlet> candidatos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            for (VotarServlet candidato : candidatos) {
                String linha = candidato.getNumero() + ";" +
                        candidato.getNome() + ";" +
                        candidato.getPartido() + ";" +
                        candidato.getTotalVotos();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}