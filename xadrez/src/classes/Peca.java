package classes;

import classes.enums.NomePeca;
import classes.enums.PecaStatus;
import classes.enums.SimboloPeca;

public abstract class Peca {
    private int jogadorID;
    private int pecaID;
    protected NomePeca nomePeca;
    protected SimboloPeca simboloPeca;
    protected PecaStatus pecaStatus;
    private int posicaoX;
    private int posicaoY;
    public Peca(final int jogadorID, final int pecaID) {
        this.jogadorID = jogadorID;
        this.pecaID = pecaID;
    }

    public int getJogadorID() {
        return jogadorID;
    }
    public int getPecaID() {
        return pecaID;
    }

    public void setJogador(final int jogadorID) {
        this.jogadorID = jogadorID;
    }

    public NomePeca getNomePeca() {
        return nomePeca;
    }

    public SimboloPeca getSimboloPeca() {
        return simboloPeca;
    }
    public PecaStatus getPecaStatus() {
        return pecaStatus;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    protected void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    protected void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    protected abstract boolean moverPeca(final int x, final int y, final Peca[] pecasNoCaminho);

    @Override
    public String toString() {
        return "" + simboloPeca;
    }
}
