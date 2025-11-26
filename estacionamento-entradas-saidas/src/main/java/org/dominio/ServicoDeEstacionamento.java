package org.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicoDeEstacionamento {
    private static Long ids;
    private Integer numeroDeVagas;
    private Integer vagasOcupadas;
    private List<Bilhete> bilhetes;
    public ServicoDeEstacionamento(int numeroDeVagas) {
        this.ids = 1L;
        this.numeroDeVagas = Math.max(numeroDeVagas, 0);
        this.vagasOcupadas = 0;
        this.bilhetes = new ArrayList<>();
    }

    public void registrarEntrada(Veiculo v) {
        if(v == null) {
            return;
        }
        for(Bilhete b : bilhetes) {
            if(b.getVeiculo().equals(v)) {
                if(b.getSaida() == null) {
                    System.out.println("Um veículo com a placa \'" + v.getPlaca() + "\' já foi registrado!");
                    return;
                }
            }
        }
        if(numeroDeVagas.equals(vagasOcupadas)) {
            System.out.println("Todas as vagas já estão ocupadas!");
            return;
        }
        Bilhete b = new Bilhete(ids, v, LocalDateTime.now(), null);
        while(bilhetes.contains(b)) {
            ids++;
            b.setId(ids);
        }
        bilhetes.add(b);
        this.vagasOcupadas++;
    }

    public void registrarSaida(Long bilheteId) {
        if(vagasOcupadas == 0) {
            System.out.println("Não há nenhuma vaga ocupada!\nBilhete inválido!");
            return;
        }
        Bilhete b = new Bilhete(bilheteId);
        if(bilhetes.contains(b)) {
            bilhetes.get(bilhetes.indexOf(b)).setSaida(LocalDateTime.now());
            this.vagasOcupadas--;
        } else {
            System.out.println("Bilhete não cadastrado!");
        }
    }

    public static Long getIds() {
        return ids;
    }

    public static void setIds(Long ids) {
        ServicoDeEstacionamento.ids = ids;
    }

    public Integer getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(Integer numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }

    public Integer getVagasOcupadas() {
        return vagasOcupadas;
    }

    public void setVagasOcupadas(Integer vagasOcupadas) {
        this.vagasOcupadas = vagasOcupadas;
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(List<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public void imprimirInfos() {
        System.out.println("Servico de Estacionamento");
        System.out.println("-------------------------");
        System.out.println("Total de vagas: " + this.numeroDeVagas);
        System.out.println("Vagas ocupadas: " + this.vagasOcupadas);
        System.out.println("Bilhetes:");
        if(bilhetes.isEmpty()) {
            System.out.println("Nenhum bilhete cadastrado!");
        }
        bilhetes.forEach(bilhete -> {
            System.out.println("-------------------------");
            System.out.println(bilhete);
        });
    }
    public Bilhete getBilhete(Long id) {
        for(Bilhete b : bilhetes) {
            if(b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }
}
