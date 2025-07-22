import classes.Jogador;
import classes.mapa.Mapa;

import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Jogo de xadrez!");
        System.out.println("Informe o nome do jogador 1: ");
        String j1 = scan.next();
        System.out.println("Informe o nome do jogador 2: ");
        String j2 = scan.next();
        Mapa mapa = new Mapa(j1, j2);
        mapa.iniciarJogo();
        int opt = -1;
        do {
            System.out.println("Quem comeca? [1/2]");
            opt = scan.nextInt();
            scan.nextLine();
        } while(opt > 2 || opt < 1);
        jogar(opt, mapa);
    }
    public static void jogar(final int primeiroAJogar, final Mapa mapa) {
        if(primeiroAJogar == 1) {
            do {
                MenuJogadas(mapa, primeiroAJogar);
                if (!mapa.fimDeJogo()) MenuJogadas(mapa,primeiroAJogar+1);
            } while(!mapa.fimDeJogo());
        } else {
            do {
                MenuJogadas(mapa, primeiroAJogar);
                if (!mapa.fimDeJogo()) MenuJogadas(mapa,primeiroAJogar-1);
            } while(!mapa.fimDeJogo());
        }
        if(mapa.getJogador(1).reiMorto()) System.out.println("O jogador 2 " + mapa.getJogador(2).getNome() + " é o ganhador!");
        if(mapa.getJogador(2).reiMorto()) System.out.println("O jogador 1 " + mapa.getJogador(1).getNome() + " é o ganhador!");
    }
    public static void MenuJogadas(final Mapa mapa, final int jogadorID) {
        int opt = -1;
        boolean optValida = false;
        boolean jogadaFeita = false;
        do {
            do {
                System.out.println("O que deseja fazer jogador " + jogadorID + "?");
                System.out.println("[1] Ver pecas disponiveis");
                System.out.println("[2] Mover peca");
                System.out.println("[3] Localizar peca na mesa");
                opt = scan.nextInt();
                scan.nextLine();
                if(opt < 4 && opt > 0) optValida = true;
            } while(!optValida);
            switch (opt) {
                case 1:
                    verPecas(mapa, jogadorID);
                    break;
                case 2:
                    jogadaFeita = moverPeca(mapa, jogadorID);
                    System.out.println("\n --------------------------------- \n");
                    mapa.mostrarJogo();
                    break;
                case 3:
                    localizarPecaNaMesa(mapa, jogadorID);
                    break;
                default: System.out.println("Opcao invalida");
            }
        } while(!jogadaFeita);
    }
    public static void verPecas(final Mapa mapa, final int jogadorID) {
        mapa.getJogador(jogadorID).showPecasVivas();
    }
    public static boolean moverPeca(final Mapa mapa, final int jogadorID) {
        System.out.println("Informe o ID da peca que deseja mover: ");
        int pecaID = scan.nextInt();
        scan.nextLine();
        if(mapa.getJogador(jogadorID).getPeca(pecaID) == null) return false;
        mapa.verMovimentosPossiveis(mapa.getJogador(jogadorID), mapa.getJogador(jogadorID).getPeca(pecaID));
        System.out.println("Informe a posicao para qual voce deseja mover a peca no eixo X: ");
        int x = scan.nextInt();
        scan.nextLine();
        System.out.println("Informe a posicao para qual voce deseja mover a peca no eixo Y: ");
        int y = scan.nextInt();
        scan.nextLine();
        if(pecaID > 8 && pecaID <= 16) {
            if(!mapa.getJogador(jogadorID).podeMover(pecaID, x, y, mapa.getPecasComiveis()) && !mapa.getJogador(jogadorID).peaoPodeComer(pecaID, x, y)) {
                System.out.println("Movimento inválido");
                return false;
            }
            mapa.mover(mapa.getJogador(jogadorID), pecaID, mapa.getJogador(jogadorID).getPeca(pecaID), x, y);
            return true;
        } else {
            if(!mapa.mover(mapa.getJogador(jogadorID), mapa.getJogador(jogadorID).getPeca(pecaID), x, y)) {
                System.out.println("Movimento inválido");
                return false;
            }
            return true;
        }
    }
    public static void localizarPecaNaMesa(final Mapa mapa, final int jogadorID) {
        int opt = -1;
        do {
            System.out.println("Deseja localizar a peca: [1] pela posicao na mesa ou [2] pelo ID?");
            opt = scan.nextInt();
            scan.nextLine();
        } while(opt!= 1 && opt != 2);
        if(opt == 1) {
            System.out.println("Informe o posicao da peca no eixo X: ");
            int x = scan.nextInt();
            scan.nextLine();
            System.out.println("Informe o posicao da peca no eixo Y: ");
            int y = scan.nextInt();
            scan.nextLine();
            if(mapa.getJogador(jogadorID).getPeca(x,y) == null) {
                System.out.println("Peca nao encontrada");
                return;
            }
            mapa.localizarPeca(mapa.getJogador(jogadorID).getPeca(x,y));
        } else if(opt == 2) {
            System.out.println("Informe o ID da peca: ");
            int idPeca = scan.nextInt();
            scan.nextLine();
            if(mapa.getJogador(jogadorID).getPeca(idPeca) == null) {
                System.out.println("Peca nao encontrada");
                return;
            }
            mapa.localizarPeca(mapa.getJogador(jogadorID).getPeca(idPeca));
        } else  {
            System.out.println("ID de jogador invalido");
        }
    }
}