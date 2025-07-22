package classes;

import classes.enums.NomePeca;
import classes.enums.SimboloPeca;

public class Peao extends Peca {
    private boolean primeiroMovimento;
    public Peao(final int jogadorID, final int pecaID) {
        super(jogadorID, pecaID);
        nomePeca = NomePeca.PEAO;
        simboloPeca = SimboloPeca.i;
        primeiroMovimento = true;
    }

    public boolean getPrimeiroMovimento() {
        return primeiroMovimento;
    }

    @Override
    protected boolean moverPeca(final int x, final int y, final Peca[] pecasNoCaminho) {
        if(x > 7 || y > 7 || x < 0 || y < 0) return false;
        if(pecasNoCaminho.length != 1) return false;
        if(y != getPosicaoY()) return false;
        if(pecasNoCaminho[0] != null && y == pecasNoCaminho[0].getPosicaoY() && pecasNoCaminho[0].getPosicaoX() == x) return false;
        if(primeiroMovimento && (Math.abs(getPosicaoX() - x) == 2)) {
            return true;
        }
        if(Math.abs(getPosicaoX() - x) == 1) return true;
        return false;
    }
    protected boolean mover1st(final int x, final int y) {
        if(getPosicaoY() != y) return false;
        if(primeiroMovimento && (Math.abs(getPosicaoX() - x) == 2)) {
            primeiroMovimento = false;
            return true;
        }
        if(Math.abs(getPosicaoX() - x) == 1) return true;
        return false;
    }
    protected boolean moverPraComer(final int x, final int y) {
        if((Math.abs(getPosicaoY() - y) == 1 && Math.abs(getPosicaoX() - x) == 1)) return true;
        return false;
    }
}
