package classes;

import classes.enums.NomePeca;
import classes.enums.SimboloPeca;

public class Cavalo extends Peca{
    public Cavalo(final int jogadorID, final int pecaID) {
        super(jogadorID, pecaID);
        nomePeca = NomePeca.CAVALO;
        simboloPeca = SimboloPeca.C;
    }

    @Override
    protected boolean moverPeca(final int x, final int y,  final Peca[] pecasNoCaminho) {
        if(x > 7 || y > 7 || x < 0 || y < 0) return false;
        if(getPosicaoX()-1 == x && (getPosicaoY()-2 == y || getPosicaoY()+2 == y)) return true;
        if(getPosicaoX()-2 == x && (getPosicaoY()-1 == y || getPosicaoY()+1 == y)) return true;
        if(getPosicaoX()+1 == x && (getPosicaoY()-2 == y || getPosicaoY()+2 == y)) return true;
        if(getPosicaoX()+2 == x && (getPosicaoY()-1 == y || getPosicaoY()+1 == y)) return true;
        return false;
    }
}
