package classes;

import classes.enums.NomePeca;
import classes.enums.SimboloPeca;

public class Rainha extends Peca{
    public Rainha(final int jogadorID, final int pecaID) {
        super(jogadorID, pecaID);
        nomePeca = NomePeca.RAINHA;
        simboloPeca = SimboloPeca.Q;
    }

    @Override
    protected boolean moverPeca(final int x, final int y,  final Peca[] pecasNoCaminho) {
        if(x > 7 || y > 7 || x < 0 || y < 0) return false;
        if(pecasNoCaminho.length != 8) return false;
        //0-baixo, 1-cima, 2-direita, 3-esquerda, 4-Direita&Baixo, 5-Esquerda&Baixo,6-Direita&Cima,7-Esquerda&Cima
        if((x != getPosicaoX() && y == getPosicaoY()) || (x == getPosicaoX() && y != getPosicaoY())) {
            if((getPosicaoY() == y && getPosicaoX() < x && pecasNoCaminho[0] != null)
                && (x > pecasNoCaminho[0].getPosicaoX())) return false;//verifica se pode ir para cima
            if((getPosicaoY() == y && getPosicaoX() > x && pecasNoCaminho[1] != null)
                && (x < pecasNoCaminho[1].getPosicaoX())) return false;//verifica se pode ir para baixo)
            if((getPosicaoX() == x && getPosicaoY() < y && pecasNoCaminho[2] != null )
                && (y > pecasNoCaminho[2].getPosicaoY())) return false;//verifica se pode ir para a direita
            if((getPosicaoX() == x && getPosicaoY() > y && pecasNoCaminho[3] != null)
                && (y < pecasNoCaminho[3].getPosicaoY())) return false;//verifica se pode ir para a esquerda
            return true;
        }
        if(Math.abs(x - getPosicaoX()) <= 1 && Math.abs(y - getPosicaoY()) <= 1) return true;
        if(pecasNoCaminho[4] != null && pecasNoCaminho[4].getPosicaoX() < x && pecasNoCaminho[4].getPosicaoY() < y) return false;
        if(pecasNoCaminho[5] != null && pecasNoCaminho[5].getPosicaoX() < x && pecasNoCaminho[5].getPosicaoY() > y) return false;
        if(pecasNoCaminho[6] != null && pecasNoCaminho[6].getPosicaoX() > x && pecasNoCaminho[6].getPosicaoY() < y) return false;
        if(pecasNoCaminho[7] != null && pecasNoCaminho[7].getPosicaoX() > x && pecasNoCaminho[7].getPosicaoY() > y) return false;
        if(Math.abs(x - getPosicaoX()) == Math.abs(y - getPosicaoY())) return true;
        return false;
    }
}
