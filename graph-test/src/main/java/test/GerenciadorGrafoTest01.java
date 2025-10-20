package test;

import javax.swing.SwingUtilities;

import domain.GerenciadorGrafo;

public class GerenciadorGrafoTest01 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenciadorGrafo::new);
    }
}