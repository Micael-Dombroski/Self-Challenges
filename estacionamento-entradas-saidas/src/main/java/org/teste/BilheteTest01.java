package org.teste;

import org.dominio.Bilhete;
import org.dominio.Veiculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BilheteTest01 {
    public static void main(String[] args) {
        Veiculo v1 = new Veiculo("AMD23O1", "Rodrigo");
        Veiculo v2 = new Veiculo("AMD2301", "Jessica");
        List<Bilhete> bilhetes = new ArrayList<>();
        Bilhete b1 = new Bilhete(1L, v1, LocalDateTime.now(), null);
        Bilhete b2 = new Bilhete(51L, v2, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        Bilhete b3 = new Bilhete(1L, v2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        System.out.println("Imprimindo bilhetes:");
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        bilhetes.add(b1);
        if(!bilhetes.contains(b2)) {
            bilhetes.add(b2);
        }
        if(!bilhetes.contains(b3)) {
            bilhetes.add(b3);
        }
        System.out.println("------------------------------");
        System.out.println("Imprimindo lista de bilhetes:");
        bilhetes.forEach(System.out::println);
    }
}
