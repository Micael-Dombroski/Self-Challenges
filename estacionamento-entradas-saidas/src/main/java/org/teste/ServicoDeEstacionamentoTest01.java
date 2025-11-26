package org.teste;

import org.dominio.ServicoDeEstacionamento;

public class ServicoDeEstacionamentoTest01 {
    public static void main(String[] args) {
        ServicoDeEstacionamento estacionamento = new ServicoDeEstacionamento(-1);
        System.out.println("Numero total de vagas: " + estacionamento.getNumeroDeVagas());
    }
}
