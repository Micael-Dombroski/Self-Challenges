package classes.mapa;

import classes.Jogador;
import classes.Peao;
import classes.Peca;
import classes.enums.NomePeca;
import classes.enums.PecaStatus;

public class Mapa {
    private Peca[][] jogo;
    private Jogador jogador1;
    private Jogador jogador2;
    private String[][] mapaJogo;
    private Peca pecasNoCaminho[] = null;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String PURPLE = "\u001B[35m";


    public Mapa(final String nome1, final String nome2) {
        this.jogo = new Peca[8][8];
        this.jogador1 = new Jogador(1, nome1);
        this.jogador2 = new Jogador(2, nome2);
    }

    public Jogador getJogador(final int id) {
        if(id == 1) return jogador1;
        if(id == 2) return jogador2;
        return null;
    }
    public Peca[] getPecasComiveis() {
        return pecasNoCaminho;
    }

    public void verMovimentosPossiveis(final Jogador jogador, final Peca peca) {
        mapaJogo= new String[8][9];
        pecasNoCaminho = null;
        if(peca.getPecaStatus() == PecaStatus.MORTA) return;
        if(peca.getNomePeca() == NomePeca.PEAO) {
            pecasNoCaminho = new Peca[1];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String celula = "";
                    if(jogo[i][j] != null) {
                        if(jogador.peaoPodeComer(peca.getPecaID(), i, j) && jogo[i][j].getJogadorID() != jogador.getID() && peca.getPosicaoY() != j) {
                            celula = PURPLE + jogo[i][j];
                        } else {
                            celula = jogo[i][j].getJogadorID() == 1? RED + jogo[i][j] : GREEN + jogo[i][j];
                        }
                        mapaJogo[i][j] = " | " + celula + RESET;
                        if(((((jogador.getID() == 1 && peca.getPosicaoX()+2 == i) && ((Peao)peca).getPrimeiroMovimento()) || (jogador.getID() == 1 && peca.getPosicaoX()+1 == i)) ||
                                (((jogador.getID() == 2 && peca.getPosicaoX()-2 == i) && ((Peao)peca).getPrimeiroMovimento()) || (jogador.getID() == 2 && peca.getPosicaoX()-1 == i)))) {
                            pecasNoCaminho[0] = jogo[i][j];
                        }
                    } else if (jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho)) {
                        if(jogador.getPeca(peca.getPecaID()).getNomePeca() == NomePeca.PEAO) {
                            if((jogador.getID() == 1 && i < peca.getPosicaoX()) ||
                                    (jogador.getID() == 2 && i > peca.getPosicaoX())) {
                                mapaJogo[i][j] =" |  ";
                                if(((jogador.getID() == 1 && peca.getPosicaoX()+2 == i) ||
                                        (jogador.getID() == 2 && peca.getPosicaoX()-2 == i)) && ((Peao)peca).getPrimeiroMovimento()) {
                                    mapaJogo[i][j] = " | o";
                                }
                            } else {
                                mapaJogo[i][j] = " | o";
                            }
                        }
                    } else {
                        mapaJogo[i][j] = " |  ";
                    }
                    System.out.print(mapaJogo[i][j]);
                }
                mapaJogo[i][8] = " | ";
                System.out.println(mapaJogo[i][8]);
            }
            showMapaJogo();
        }
        if(peca.getNomePeca() == NomePeca.TORRE) {
            pecasNoCaminho = new Peca[4];//0-baixo, 1-cima, 2-direita, 3-esquerda
            for (int i = peca.getPosicaoX()+1; i < 8; i++) {
                Peca p = jogo[i][peca.getPosicaoY()];
                if(p != null) {
                    pecasNoCaminho[0] = p;
                    break;
                }
            }
            for (int i = peca.getPosicaoX()-1; i >= 0; i--) {
                Peca p = jogo[i][peca.getPosicaoY()];
                if(p != null) {
                    pecasNoCaminho[1] = p;
                    break;
                }
            }
            for (int i = peca.getPosicaoY()+1; i < 8; i++) {
                Peca p = jogo[peca.getPosicaoX()][i];
                if(p != null) {
                    pecasNoCaminho[3] = p;
                    break;
                }
            }
            for (int i = peca.getPosicaoY()-1; i >= 0; i--) {
                Peca p = jogo[peca.getPosicaoX()][i];
                if(p != null) {
                    pecasNoCaminho[3] = p;
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String celula = "";
                    //pecaNoCaminho = dirPecaNoCaminho(pecaNoCaminho, jogo[i][j], peca);
                    if(jogo[i][j] != null) {
                        if(jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho) && jogo[i][j].getJogadorID() != jogador.getID()) {
                            for(Peca p : pecasNoCaminho) {
                                if(jogo[i][j] == p) {
                                    celula = PURPLE + jogo[i][j];
                                    break;
                                }
                            }
                            if(celula == "") celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                        } else {
                            celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                        }
                        mapaJogo[i][j] = " | " + celula + RESET;
                        System.out.print(" | " + celula + RESET);
                    } else if (jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho)) {
                        mapaJogo[i][j] = " | o";
                        System.out.print(mapaJogo[i][j]);
                    } else {
                        mapaJogo[i][j] = " |  ";
                        System.out.print(mapaJogo[i][j]);
                    }
                }
                System.out.println(" | ");
                mapaJogo[i][8] = " | ";
            }
            showMapaJogo();
        } else if(peca.getNomePeca() == NomePeca.CAVALO) {
            pecasNoCaminho = null;//não se aplica
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String celula = "";
                    if(jogo[i][j] != null) {
                        if(jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho) && jogo[i][j].getJogadorID() != jogador.getID()) {
                            celula = PURPLE + jogo[i][j];
                        } else {
                            celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                        }
                        mapaJogo[i][j] = " | " + celula + RESET;
                    } else if (jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho)) {
                        mapaJogo[i][j] = " | o";
                    } else {
                        mapaJogo[i][j] = " |  ";
                    }
                }
                mapaJogo[i][8] = " | ";
            }
            showMapaJogo();
        } else if(peca.getNomePeca() == NomePeca.BISPO) {
            pecasNoCaminho = new Peca[4];
            int posX = peca.getPosicaoX(), posY = peca.getPosicaoY(), incremento = 1;
            for(int i = posX+1; i < 8; i++) {//0-Direita&Baixo
                int j= posY + incremento;
                if(j > 7) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[0] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            incremento = 1;
            for(int i = posX+1; i < 8; i++) {//1-Esquerda&Baixo
                int j= posY - incremento;
                if(j < 0) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[1] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            incremento = 1;
            for(int i = posX-1; i >= 0; i--) {//2-Direita&Cima
                int j= posY + incremento;
                if(j > 7) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[2] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            incremento = 1;
            for(int i = posX-1; i >= 0; i--) {//3-Esquerda&Cima
                int j= posY - incremento;
                if(j < 0) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[3] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String celula = "";
                    int dirY = j - peca.getPosicaoY() > 0 ? 1 : -1;
                    int dirX = i - peca.getPosicaoX() > 0 ? 1 : -1;
                    if(jogo[i][j] != null) {
                        if(jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho) && jogo[i][j].getJogadorID() != jogador.getID()) {
                            celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                            for(Peca p : pecasNoCaminho) {
                                if(p == jogo[i][j]) {
                                    celula = PURPLE + jogo[i][j];
                                    break;
                                }
                            }
                        } else {
                            celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                        }
                        mapaJogo[i][j] = " | " + celula + RESET;
                    } else if (jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho)) {
                        mapaJogo[i][j] = " | o";
                    } else {
                        mapaJogo[i][j] = " |  ";
                    }
                }
                mapaJogo[i][8] = " | ";
            }
            showMapaJogo();
        } else if(peca.getNomePeca() == NomePeca.REI) {
            pecasNoCaminho = null;//não se aplica
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String celula = "";
                    if(jogo[i][j] != null) {
                        if(jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho) && jogo[i][j].getJogadorID() != jogador.getID()) {
                            celula = PURPLE + jogo[i][j];
                        } else {
                            celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                        }
                        mapaJogo[i][j] = " | " + celula + RESET;
                    } else if (jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho)) {
                        mapaJogo[i][j] = " | o";
                    } else {
                        mapaJogo[i][j] = " |  ";
                    }
                }
                mapaJogo[i][8] = " | ";
            }
            showMapaJogo();
        } else if(peca.getNomePeca() == NomePeca.RAINHA) {
            pecasNoCaminho= new Peca[8];//0-baixo, 1-cima, 2-direita, 3-esquerda, 4-Direita&Baixo, 5-Esquerda&Baixo, 6-Direita&Cima, 7-Esquerda&Cima
            for (int i = peca.getPosicaoX()+1; i < 8; i++) {
                Peca p = jogo[i][peca.getPosicaoY()];
                if(p != null) {
                    pecasNoCaminho[0] = p;
                    break;
                }
            }
            for (int i = peca.getPosicaoX()-1; i >= 0; i--) {
                Peca p = jogo[i][peca.getPosicaoY()];
                if(p != null) {
                    pecasNoCaminho[1] = p;
                    break;
                }
            }
            for (int i = peca.getPosicaoY()+1; i < 8; i++) {
                Peca p = jogo[peca.getPosicaoX()][i];
                if(p != null) {
                    pecasNoCaminho[2] = p;
                    break;
                }
            }
            for (int i = peca.getPosicaoY()-1; i >= 0; i--) {
                Peca p = jogo[peca.getPosicaoX()][i];
                if(p != null) {
                    pecasNoCaminho[3] = p;
                    break;
                }
            }
            int posX = peca.getPosicaoX(), posY = peca.getPosicaoY(), incremento = 1;
            for(int i = posX+1; i < 8; i++) {//4-Direita&Baixo
                int j= posY + incremento;
                if(j > 7) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[4] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            incremento = 1;
            for(int i = posX+1; i < 8; i++) {//5-Esquerda&Baixo
                int j= posY - incremento;
                if(j < 0) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[5] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            incremento = 1;
            for(int i = posX-1; i >= 0; i--) {//6-Direita&Cima
                int j= posY + incremento;
                if(j > 7) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[6] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            incremento = 1;
            for(int i = posX-1; i >= 0; i--) {//7-Esquerda&Cima
                int j= posY - incremento;
                if(j < 0) break;
                if(jogo[i][j] != null) {
                    pecasNoCaminho[7] = jogo[i][j];
                    break;
                }
                incremento++;
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String celula = "";
                    if(jogo[i][j] != null) {
                        if(jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho) && jogo[i][j].getJogadorID() != jogador.getID()) {
                            celula = PURPLE + jogo[i][j];
                        } else {
                            celula = jogo[i][j].getJogadorID() == 1 ? RED + jogo[i][j] : GREEN + jogo[i][j];
                        }
                        mapaJogo[i][j] = " | " + celula + RESET;
                    } else if (jogador.podeMover(peca.getPecaID(), i, j, pecasNoCaminho)) {
                        mapaJogo[i][j] = " | o";
                    } else {
                        mapaJogo[i][j] = " |  ";
                    }
                }
                mapaJogo[i][8] = " | ";
            }
            showMapaJogo();
        }
    }
    public boolean mover(final Jogador jogador, final int pecaID, final Peca peca, final int x, final int y) {
        if(x > 7 || y > 7 || x < 0 || y< 0) return false;
        if(peca.getPecaStatus() == PecaStatus.MORTA) return false;
        if(peca.getNomePeca() == NomePeca.PEAO && pecaID == peca.getPecaID()) {
            if(peca.getPosicaoX() == x) return false;
            if(jogo[x][y] != null && jogo[x][y].getJogadorID() == peca.getJogadorID()) return false;
            int direcao = jogador.getID() == 1 ? 1 : -1;
            if(direcao == 1) {
                if(peca.getPosicaoX() > x) return false;
            } else {
                if(peca.getPosicaoX() < x) return false;
            }
            if(jogador.podeMover(peca.getPecaID(), x, y, pecasNoCaminho)) {
                jogador.mover(peca.getPecaID(), x, y);
                updtPosPeca(peca, jogador, x, y);
                return true;
            }
            if(jogo[x][y] != null) {
                if(Math.abs(y-peca.getPosicaoY()) != 1) return false;
                if(jogador.peaoPodeComer(peca.getPecaID(), x, y)) {
                    jogador.mover(peca.getPecaID(), x, y);
                    perdeuPeca(peca, x, y);
                    updtPosPeca(peca, jogador, x, y);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean mover(final Jogador jogador, final Peca peca, final int x, final int y) {
        if(x > 7 || y > 7 || x < 0 || y< 0) return false;
        if(peca.getPecaStatus() == PecaStatus.MORTA) return false;
        if(peca.getNomePeca() == NomePeca.TORRE) {
            if(jogo[x][y] != null && jogo[x][y].getJogadorID() == peca.getJogadorID()) return false;
            if(jogador.podeMover(peca.getPecaID(), x,y, pecasNoCaminho)) {
                jogador.mover(peca.getPecaID(), x, y);
                perdeuPeca(peca, x, y);
                updtPosPeca(peca, jogador, x, y);
                return true;
            }
            for(int i = 0; i < pecasNoCaminho.length; i++) {
                if(pecasNoCaminho[i] != null && pecasNoCaminho[i].getPosicaoX() == x && pecasNoCaminho[i].getPosicaoY() == y) {
                    jogador.mover(peca.getPecaID(), x, y);
                    perdeuPeca(peca, x, y);
                    updtPosPeca(peca, jogador, x, y);
                    return true;
                }
            }
            return false;
        } else if(peca.getNomePeca() == NomePeca.CAVALO) {
            if(jogo[x][y] != null && jogo[x][y].getJogadorID() == peca.getJogadorID()) return false;
            if(jogador.podeMover(peca.getPecaID(), x,y, pecasNoCaminho)) {
                jogador.mover(peca.getPecaID(), x , y);
                perdeuPeca(peca, x, y);
                updtPosPeca(peca, jogador, x, y);
                return true;
            }
            return false;
        } else if(peca.getNomePeca() == NomePeca.BISPO) {
            if(jogo[x][y] != null && jogo[x][y].getJogadorID() == peca.getJogadorID()) return false;
            if(jogador.podeMover(peca.getPecaID(), x,y, pecasNoCaminho)) {
                jogador.mover(peca.getPecaID(), x , y);
                perdeuPeca(peca, x, y);
                updtPosPeca(peca, jogador, x, y);
                return true;
            }
            return false;
        } else if(peca.getNomePeca() == NomePeca.REI) {
            if(jogo[x][y] != null && jogo[x][y].getJogadorID() == peca.getJogadorID()) return false;
            if(jogador.podeMover(peca.getPecaID(), x,y, pecasNoCaminho)) {
                jogador.mover(peca.getPecaID(), x , y);
                perdeuPeca(peca, x, y);
                updtPosPeca(peca, jogador, x, y);
                return true;
            }
            return false;
        } else if(peca.getNomePeca() == NomePeca.RAINHA) {
            if(jogo[x][y] != null && jogo[x][y].getJogadorID() == peca.getJogadorID()) return false;
            if(jogador.podeMover(peca.getPecaID(), x,y, pecasNoCaminho)) {
                jogador.mover(peca.getPecaID(), x , y);
                perdeuPeca(peca, x, y);
                updtPosPeca(peca, jogador, x, y);
                return true;
            }
            return false;
        }
        return false;
    }
    public void iniciarJogo() {
        jogador1.start(1);
        jogador2.start(2);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(jogador1.getPeca(i,j) != null) jogo[i][j] = jogador1.getPeca(i,j);
                if(jogador2.getPeca(i,j) != null) jogo[i][j] = jogador2.getPeca(i,j);
            }
        }
        mostrarJogo();
    }
    public void mostrarJogo() {
        mapaJogo = new String[8][9];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String celula = "";
                if(jogo[i][j] != null) {
                    celula = jogo[i][j].getJogadorID() == 1? RED + jogo[i][j] : GREEN + jogo[i][j];
                    mapaJogo[i][j] = " | " + celula + RESET;
                } else {
                    mapaJogo[i][j] = " |  ";
                }
            }
            mapaJogo[i][8] = " | ";
        }
        showMapaJogo();
    }
    public boolean poderComer(final Peca peca1, final Peca peca2) {
        if(peca1.getJogadorID() != peca2.getJogadorID()) return true;
        return false;
    }
    public void updtPosPeca(final Peca peca, final Jogador jogador, final int x, final int y) {
        for (int i=0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(jogo[i][j] != null && jogo[i][j].getJogadorID() == jogador.getID() && jogo[i][j].getPecaID() == jogador.getPeca(x,y).getPecaID()) {
                    jogo[i][j] = null;
                    jogador.mover(peca.getPecaID(), x, y);
                    jogo[x][y] = jogador.getPeca(x,y);
                    return;
                }
            }
        }
    }
    public boolean perdeuPeca(final Peca peca, final int x, final int y) {
        if(jogo[x][y] != null && jogo[x][y].getJogadorID() != peca.getJogadorID()) {
            if(peca.getJogadorID() == 1) {
                jogador2.pecaMorreu(jogo[x][y].getPecaID());
            } else {
                jogador1.pecaMorreu(jogo[x][y].getPecaID());
            }
            return true;
        }
        return false;
    }
    public void localizarPeca(final Peca peca) {
        boolean pecaEncontrada = false;
        mapaJogo = new String[8][9];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String celula = " |  ";
                if(jogo[i][j] != null) {
                    if(jogo[i][j].getPecaID() == peca.getPecaID() &&
                            jogo[i][j].getJogadorID() == peca.getJogadorID()) {
                        pecaEncontrada = true;
                        celula = " | " + (peca.getJogadorID() == 1 ? RED : GREEN) + jogo[i][j] + RESET;
                    } else {
                        celula = " | " + jogo[i][j];
                    }
                }
                mapaJogo[i][j] = celula;
            }
            mapaJogo[i][8] = " | ";
        }
        showMapaJogo();

        if(!pecaEncontrada) {
            System.out.println("Peça não encontrada");
        }
    }
    public boolean fimDeJogo() {
        if(jogador1.reiMorto()) return true;
        if(jogador2.reiMorto()) return true;
        return false;
    }
    private void showMapaJogo() {
        System.out.println("X");
        System.out.println("v");
        for (int i = 0; i < 8; i++) {
            System.out.print(i);
            for (int j = 0; j < 8; j++) {
                System.out.print(mapaJogo[i][j]);
            }
            System.out.println(mapaJogo[i][8]);
        }
        System.out.println("    0   1   2   3   4   5   6   7   > Y");
    }
}
