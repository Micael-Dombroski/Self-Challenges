package classes;

import classes.enums.NomePeca;
import classes.enums.SimboloPeca;

public class Torre extends Peca {

    public Torre(final int jogadorID, final int pecaID) {
        super(jogadorID, pecaID);
        this.nomePeca = NomePeca.TORRE;
        this.simboloPeca = SimboloPeca.T;
    }

    @Override
    protected boolean moverPeca(final int x, final int y,  final Peca[] pecasNoCaminho) {
        if(pecasNoCaminho.length > 4) return false;//0-esquerda, 1-direita, 2-cima, 3-baixo
        if(x > 7 || y > 7 || x < 0 || y < 0) return false;
        if(x != getPosicaoX() && y == getPosicaoY()) {
            if(getPosicaoY() == y && getPosicaoX() < x && pecasNoCaminho[0] != null)
                if(x > pecasNoCaminho[0].getPosicaoX()) return false;//verifica se pode ir para cima
            if(getPosicaoY() == y && getPosicaoX() > x && pecasNoCaminho[1] != null)
                if(x < pecasNoCaminho[1].getPosicaoX()) return false;//verifica se pode ir para baixo)
            if(getPosicaoX() == x && getPosicaoY() > y && pecasNoCaminho[2] != null )
                if (y > pecasNoCaminho[2].getPosicaoY()) return false;//verifica se pode ir para a direita
            if(getPosicaoX() == x && getPosicaoY() > y && pecasNoCaminho[3] != null)
                if(y < pecasNoCaminho[3].getPosicaoY()) return false;//verifica se pode ir para a esquerda
            return true;
        }
        if(x == getPosicaoX() && y != getPosicaoY()) {
            if(getPosicaoY() == y && getPosicaoX() < x && pecasNoCaminho[0] != null)
                if(x > pecasNoCaminho[0].getPosicaoX()) return false;//verifica se pode ir para cima
            if(getPosicaoY() == y && getPosicaoX() > x && pecasNoCaminho[1] != null)
                if(x < pecasNoCaminho[1].getPosicaoX()) return false;//verifica se pode ir para baixo)
            if(getPosicaoX() == x && getPosicaoY() > y && pecasNoCaminho[2] != null )
                if (y > pecasNoCaminho[2].getPosicaoY()) return false;//verifica se pode ir para a direita
            if(getPosicaoX() == x && getPosicaoY() > y && pecasNoCaminho[3] != null)
                if(y < pecasNoCaminho[3].getPosicaoY()) return false;//verifica se pode ir para a esquerda
            return true;
        }
        return false;
    }

}
