package domain;

import guru.nidi.graphviz.model.MutableNode;

public class Aresta {
    private String nome;
    private String origem;
    private String destino;
    private int peso;


    public Aresta(String nome, String origem, String destino, int peso) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj) {
            return true;
        }
        if(!(obj instanceof Aresta)) {
            return false;
        }
        Aresta a = (Aresta) obj;

        return this.getNome().equals(a.getNome());
    }
}
