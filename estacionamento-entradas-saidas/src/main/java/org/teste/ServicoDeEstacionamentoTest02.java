package org.teste;

import org.dominio.ServicoDeEstacionamento;
import org.dominio.Veiculo;

public class ServicoDeEstacionamentoTest02 {
    public static void main(String[] args) {
        ServicoDeEstacionamento estacionamento = new ServicoDeEstacionamento(3);
        estacionamento.imprimirInfos();
        Veiculo v1 = new Veiculo("JCD9U84", "Jorginho");
        Veiculo v2 = new Veiculo("ABG34J3", "Marcia");
        Veiculo v3 = new Veiculo("JCD9U84", "Sidney");
        Veiculo v4 = new Veiculo("HFG8A74", "Bela");
        Veiculo v5 = new Veiculo("JDC4R32", "Lima");
        System.out.println("\n==============================\n");
        estacionamento.registrarEntrada(v1);
        estacionamento.registrarEntrada(v2);
        estacionamento.registrarEntrada(v4);
        estacionamento.registrarEntrada(v5);
        estacionamento.registrarEntrada(v3);
        System.out.println("\n==============================\n");
        estacionamento.imprimirInfos();
        System.out.println("\n==============================\n");
        estacionamento.registrarSaida(2L);
        estacionamento.getBilhete(2L).setSaida(estacionamento.getBilhete(2L).getSaida().plusHours(2));
        estacionamento.registrarSaida(1L);
        estacionamento.getBilhete(1L).setSaida(estacionamento.getBilhete(1L).getSaida().plusMinutes(10));
        estacionamento.registrarSaida(4L);
        System.out.println("\n==============================\n");
        estacionamento.imprimirInfos();
    }
}