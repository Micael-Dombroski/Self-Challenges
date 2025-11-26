package org.teste;

import org.dominio.Veiculo;

public class VeiculoTest01 {
    public static void main(String[] args) {
        Veiculo v1 = new Veiculo("AMD23O1", "Rodrigo");
        Veiculo v2 = new Veiculo("AMD2301", "Jessica");
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v1.equals(v2) ? "Iguais" : "Diferentes");
    }
}
