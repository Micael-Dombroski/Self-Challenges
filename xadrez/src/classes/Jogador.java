package classes;

import classes.*;
import classes.enums.NomePeca;
import classes.enums.PecaStatus;

public class Jogador {
    private int id;
    private String nome;
    private Peca[] pecas;
    public Jogador(final int id, final String nome) {
        this.id = id;
        this.nome = nome;
        pecas = new Peca[] {
                new Rei(id,1), new Rainha(id,2),
                new Cavalo(id,3), new Cavalo(id,4),
                new Bispo(id,5), new Bispo(id,6),
                new Torre(id,7), new Torre(id,8),
                new Peao(id, 9), new Peao(id, 10),
                new Peao(id, 11), new Peao(id, 12),
                new Peao(id, 13), new Peao(id, 14),
                new Peao(id, 15), new Peao(id, 16)
        };
        for(Peca peca : pecas) peca.pecaStatus = PecaStatus.VIVA;
    }

    public String getNome() {
        return nome;
    }
    public Peca getPeca(final int x, final  int y) {
        for(Peca peca : pecas) {
            if(peca.getPosicaoX() == x && peca.getPosicaoY() == y) return peca;
        }
        return null;
    }
    public Peca getPeca(final int id) {
        for(Peca peca : pecas) {
            if(peca.getPecaID() == id) return peca;
        }
        return null;
    }

    public int getID() {
        return id;
    }
    public void start(final int jogadorID) {
        int numPeca = 0;
        if(jogadorID == 1) {
            pecas[0].setPosicaoY(4);//Rei
            pecas[0].setPosicaoX(0);//Rei
            pecas[1].setPosicaoY(3);//Rainha
            pecas[1].setPosicaoX(0);//Rainha
            pecas[2].setPosicaoY(1);//Cavalo1
            pecas[2].setPosicaoX(0);//Cavalo1
            pecas[3].setPosicaoY(6);//Cavalo2
            pecas[3].setPosicaoX(0);//Cavalo2
            pecas[4].setPosicaoY(2);//Bispo1
            pecas[4].setPosicaoX(0);//Bispo1
            pecas[5].setPosicaoY(5);//Bispo2
            pecas[5].setPosicaoX(0);//Bispo2
            pecas[6].setPosicaoY(0);//Torre1
            pecas[6].setPosicaoX(0);//Torre1
            pecas[7].setPosicaoY(7);//Torre2
            pecas[7].setPosicaoX(0);//Torre2
            pecas[8].setPosicaoY(0);//Peao1
            pecas[8].setPosicaoX(1);//Peao1
            pecas[9].setPosicaoY(1);//Peao2
            pecas[9].setPosicaoX(1);//Peao2
            pecas[10].setPosicaoY(2);//Peao3
            pecas[10].setPosicaoX(1);//Peao3
            pecas[11].setPosicaoY(3);//Peao4
            pecas[11].setPosicaoX(1);//Peao4
            pecas[12].setPosicaoY(4);//Peao5
            pecas[12].setPosicaoX(1);//Peao5
            pecas[13].setPosicaoY(5);//Peao6
            pecas[13].setPosicaoX(1);//Peao6
            pecas[14].setPosicaoY(6);//Peao7
            pecas[14].setPosicaoX(1);//Peao7
            pecas[15].setPosicaoY(7);//Peao8
            pecas[15].setPosicaoX(1);//Peao8
        } else if (jogadorID == 2) {
            pecas[0].setPosicaoY(4);//Rei
            pecas[0].setPosicaoX(7);//Rei
            pecas[1].setPosicaoY(3);//Rainha
            pecas[1].setPosicaoX(7);//Rainha
            pecas[2].setPosicaoY(1);//Cavalo1
            pecas[2].setPosicaoX(7);//Cavalo1
            pecas[3].setPosicaoY(6);//Cavalo2
            pecas[3].setPosicaoX(7);//Cavalo2
            pecas[4].setPosicaoY(2);//Bispo1
            pecas[4].setPosicaoX(7);//Bispo1
            pecas[5].setPosicaoY(5);//Bispo2
            pecas[5].setPosicaoX(7);//Bispo2
            pecas[6].setPosicaoY(0);//Torre1
            pecas[6].setPosicaoX(7);//Torre1
            pecas[7].setPosicaoY(7);//Torre2
            pecas[7].setPosicaoX(7);//Torre2
            pecas[8].setPosicaoY(0);//Peao1
            pecas[8].setPosicaoX(6);//Peao1
            pecas[9].setPosicaoY(1);//Peao2
            pecas[9].setPosicaoX(6);//Peao2
            pecas[10].setPosicaoY(2);//Peao3
            pecas[10].setPosicaoX(6);//Peao3
            pecas[11].setPosicaoY(3);//Peao4
            pecas[11].setPosicaoX(6);//Peao4
            pecas[12].setPosicaoY(4);//Peao5
            pecas[12].setPosicaoX(6);//Peao5
            pecas[13].setPosicaoY(5);//Peao6
            pecas[13].setPosicaoX(6);//Peao6
            pecas[14].setPosicaoY(6);//Peao7
            pecas[14].setPosicaoX(6);//Peao7
            pecas[15].setPosicaoY(7);//Peao8
            pecas[15].setPosicaoX(6);//Peao8
        }
    }
    public boolean Promocao(final int pecaID) {
        if((pecas[pecaID-1].getPosicaoX() == 0 || pecas[pecaID-1].getPosicaoX() == 7) && pecas[pecaID-1].getNomePeca() == NomePeca.PEAO) {
            return true;
        }
        return false;
    }
    public boolean podeMover(final int idPeca, final int x, final int y, final Peca[] pecasNoCaminho) {
        Peca peca = pecas[idPeca-1];
        if(peca.getNomePeca() == NomePeca.PEAO) {
            if(((Peao)peca).moverPeca(x, y, pecasNoCaminho)) return true;
        }
        if(peca.moverPeca(x,y, pecasNoCaminho)) return true;
        return false;
    }
    public boolean peaoPodeComer(final int idPeca, final int x, final int y) {
        Peca peca = pecas[idPeca-1];
        if(peca.getNomePeca() == NomePeca.PEAO) {
            if(((Peao)peca).moverPraComer(x, y)) return true;
        }
        return false;
    }
    public boolean mover(final int idPeca, final int x, final int y) {
        for(Peca peca : pecas) {
            if(peca.getPecaID() == idPeca) {
                if(peca.getNomePeca() == NomePeca.PEAO && ((Peao)peca).getPrimeiroMovimento()) ((Peao)peca).mover1st(x,y);
                peca.setPosicaoX(x);
                peca.setPosicaoY(y);
                if(Promocao(idPeca)) {
                    Peca rainha = new Rainha(id, idPeca);
                    rainha.setPosicaoX(x);
                    rainha.setPosicaoY(y);
                    pecas[idPeca-1] = rainha;
                }
                return true;
            }
        }
        return false;
    }
    public void pecaMorreu(final int idPeca) {
        for(Peca peca: pecas) {
            if(peca.getPecaID() == idPeca) {
                peca.pecaStatus = PecaStatus.MORTA;
                return;
            }
        }
    }
    public boolean reiMorto() {
        if(pecas[0].getPecaStatus() == PecaStatus.MORTA) return true;
        return false;
    }
    public void showPecasVivas() {
        System.out.println("Peças disponíveis: ");
        System.out.println("------------------------------");
        for(Peca peca : pecas) {
            if(peca.getPecaStatus() == PecaStatus.VIVA) {
                String nomePeca = peca.getNomePeca() == NomePeca.REI ?
                        "Rei" : peca.getNomePeca() == NomePeca.RAINHA ?
                        "Rainha" : peca.getNomePeca() == NomePeca.CAVALO ?
                        "Cavalo" : peca.getNomePeca() == NomePeca.BISPO ?
                        "Bispo" : peca.getNomePeca() == NomePeca.TORRE ?
                        "Torre" : peca.getNomePeca() == NomePeca.PEAO ?
                        "Peao" : "N/A";
                System.out.println("Peca: " + nomePeca);
                System.out.println("ID da Peca " + peca.getPecaID());
                System.out.println("Posicao da Peca (x,y): " + peca.getPosicaoX() + ", " + peca.getPosicaoY());
                System.out.println("------------------------------");
            }
        }
    }
}
