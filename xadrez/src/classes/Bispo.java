package classes;

import classes.enums.NomePeca;
import classes.enums.SimboloPeca;

public class Bispo extends Peca {
    public Bispo(final int jogadorID, final int pecaID) {
        super(jogadorID,pecaID);
        nomePeca = NomePeca.BISPO;
        simboloPeca = SimboloPeca.B;
    }

    @Override
    protected boolean moverPeca(final int x, final int y,  final Peca[] pecasNoCaminho) {
        if(x > 7 || y > 7 || x < 0 || y < 0) return false;
        if(pecasNoCaminho.length > 4) return false;
        //0-Direita&Baixo, 1-Esquerda&Baixo, 2-Direita&Cima, 3-Esquerda&Cima
        if(pecasNoCaminho[0] != null && pecasNoCaminho[0].getPosicaoX() < x && pecasNoCaminho[0].getPosicaoY() < y) return false;
        if(pecasNoCaminho[1] != null && pecasNoCaminho[1].getPosicaoX() < x && pecasNoCaminho[1].getPosicaoY() > y) return false;
        if(pecasNoCaminho[2] != null && pecasNoCaminho[2].getPosicaoX() > x && pecasNoCaminho[2].getPosicaoY() < y) return false;
        if(pecasNoCaminho[3] != null && pecasNoCaminho[3].getPosicaoX() > x && pecasNoCaminho[3].getPosicaoY() > y) return false;
        if(Math.abs(x - getPosicaoX()) == Math.abs(y - getPosicaoY())) return true;
        return false;
    }
}
