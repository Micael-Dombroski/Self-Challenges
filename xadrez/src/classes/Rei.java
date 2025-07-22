package classes;

import classes.enums.NomePeca;
import classes.enums.SimboloPeca;

public class Rei extends Peca{
    public Rei(final int jogadorID, final int pecaID) {
        super(jogadorID, pecaID);
        nomePeca = NomePeca.REI;
        simboloPeca = SimboloPeca.K;
    }

    @Override
    protected boolean moverPeca(final int x, final int y,  final Peca[] pecasNoCaminho) {
        if(x > 7 || y > 7 || x < 0 || y < 0) return false;
        if(Math.abs(x - getPosicaoX()) <= 1 && Math.abs(y - getPosicaoY()) <= 1) {
            return true;
        }
        return false;
    }
}
