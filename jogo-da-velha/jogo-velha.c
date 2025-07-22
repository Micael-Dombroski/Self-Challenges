#include <stdio.h>
#include <stdbool.h>
#include <time.h>
#include <stdlib.h>
void showGame(char game[]) {
    for (short i = 0; i < 9; i++)
    {
        if(game[i] == ' ') {
            printf("|%hu", i);
        } else {
            printf("|%c", game[i]);
        }
        if(i == 2 || i == 5 || i == 8) {
            printf("|\n");
        }
    }
}
bool validMove(char game[], int movePos) {
    if(game[movePos]== ' ') {
        return true;
    }
    return false;
}
short winLine(char game[], char value) {
    if(game[0] == value && game[1] == value && game[2] == ' ') {
        return 2;
    } else if(game[0] == value && game[2] == value && game[1] == ' ') {
        return 1;
    } else if(game[1] == value && game[2] == value && game[0] == ' ') {
        return 0;
    } else if(game[3] == value && game[4] == value && game[5] == ' ') {
        return 5;
    } else if(game[3] == value && game[5] == value && game[4] == ' ') {
        return 4;
    } else if(game[4] == value && game[5] == value && game[3] == ' ') {
        return 3;
    } else if(game[6] == value && game[7] == value && game[8] == ' ') {
        return 8;
    } else if(game[7] == value && game[8] == value && game[6] == ' ') {
        return 6;
    } else if(game[0] == value && game[3] == value && game[6] == ' ') {
        return 6;
    } else if(game[3] == value && game[6] == value && game[0] == ' ') {
        return 0;
    } else if(game[0] == value && game[6] == value && game[3] == ' ') {
        return 3;
    } else if(game[1] == value && game[4] == value && game[7] == ' ') {
        return 7;
    } else if(game[4] == value && game[7] == value && game[1] == ' ') {
        return 1;
    } else if(game[2] == value && game[5] == value && game[8] == ' ') {
        return 8;
    } else if(game[5] == value && game[8] == value && game[2] == ' ') {
        return 2;
    } else if(game[2] == value && game[8] == value && game[5] == ' ') {
        return 5;
    } else if(game[0] == value && game[4] == value && game[8] == ' ') {
        return 8;
    } else if(game[4] == value && game[8] == value && game[0] == ' ') {
        return 0;
    } else if(game[0] == value && game[8] == value && game[4] == ' ') {
        return 4;
    } else if(game[2] == value && game[4] == value && game[6] == ' ') {
        return 6;
    } else if(game[4] == value && game[6] == value && game[2] == ' ') {
        return 2;
    } else if(game[0] == value && game[2] == value && game[1] == ' ') {
        return 1;
    } else if(game[0] == value && game[8] == value && game[4] == ' ') {
        return 4;
    } else if(game[0] == value && game[6] == value && game[3] == ' ') {
        return 3;
    } else if(game[1] == value && game[7] == value && game[4] == ' ') {
        return 4;
    } else if(game[2] == value && game[8] == value && game[5] == ' ') {
        return 4;
    } else if(game[6] == value && game[8] == value && game[7] == ' ') {
        return 7;
    }
     else if(game[2] == value && game[6] == value && game[4] == ' ') {
        return 4;
    }
    return -1;
}
short winMove(char game[]) {
    return winLine(game, 'X');
}
short killWin(char game[]) {
    return winLine(game, 'O');
}
short nextBotMove(char game[], short count) {
    char value = 'X';
    if (count == 2) {
        if(game[4] == value && game[0] == ' ') {
            return 0;
        }
        if(game[4] == value && game[2] == ' ') {
            return 2;
        }
        if(game[4] == value && game[6] == ' ') {
            return 6;
        }
        if(game[4] == value && game[8] == ' ') {
            return 8;
        }
        if(game[4] == value && game[8] == ' ') {
            return 8;
        }
    }
    if (count == 4) {
        if(game[4] == value && game[0] == value && game[2] == ' ') {
            return 2;
        } else if(game[4] == value && game[0] == value && game[6] == ' ') {
            return 6;
        } else if(game[4] == value && game[2] == value && game[0] == ' ') {
            return 0;
        } else if(game[4] == value && game[2] == value && game[8] == ' ') {
            return 8;
        } else if(game[4] == value && game[6] == value && game[0] == ' ') {
            return 0;
        } else if(game[4] == value && game[6] == value && game[8] == ' ') {
            return 8;
        } else if(game[4] == value && game[8] == value && game[2] == ' ') {
            return 2;
        } else if(game[4] == value && game[8] == value && game[6] == ' ') {
            return 6;
        } else if(game[4] == value && game[8] == value && game[2] == ' ') {
            return 2;
        } else if(game[4] == value && game[8] == value && game[6] == ' ') {
            return 6;
        }
    }
    short nextMove;
    do {
            nextMove = rand() % 9;
    } while(!validMove(game, nextMove));
    return nextMove;
}
short botMove(char game[], short moveNumber) {
    short move;
    if (moveNumber > 2) {
        move = winMove(game);
        if(move>=0) {
            return move;
        }
        move = killWin(game);
        if(move>=0) {
            return move;
        }
    }
    if (moveNumber == 0) {
        move = 4;
        return move;
    }
    move = nextBotMove(game, moveNumber);
    return move;
}
bool gameIsFinish(char game[], char value) {
    if(game[0] == value && game[1] == value && game[2] == value) {
        return true;
    } else if(game[3] == value && game[4] == value && game[5] == value) {
        return true;
    } else if(game[6] == value && game[7] == value && game[8] == value) {
        return true;
    } else if(game[0] == value && game[3] == value && game[6] == value) {
        return true;
    } else if(game[1] == value && game[4] == value && game[7] == value) {
        return true;
    } else if(game[2] == value && game[5] == value && game[8] == value) {
        return true;
    } else if(game[0] == value && game[4] == value && game[8] == value) {
        return true;
    } else if(game[2] == value && game[4] == value && game[6] == value) {
        return true;
    }
    return false;
}
bool winX(char game[]) {
    bool finishGame = gameIsFinish(game, 'X');
    return finishGame;
}
bool winO(char game[]) {
    bool finishGame = gameIsFinish(game, 'O');
    return finishGame;
}
void pause() {
    while (getchar() != '\n');  // limpa o buffer
    getchar(); // espera uma tecla
}
int main(void) {
    char game[9] = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
    short chosenPos;
    printf("Jogo da Velha\n");
    showGame(game);
    for (short i = 0; i < 9; i++)
    {
        if(i%2==1) {
            do {
                printf("Escolha uma posicao no jogo (de 0 a 8): \n");
                scanf("%hu", &chosenPos);
                if(!validMove(game, chosenPos)) {
                    printf("Posicao invalida\n");
                }
            } while (!validMove(game, chosenPos));
            game[chosenPos] = 'O';
            showGame(game);
            if(winO(game)) {
                printf("O jogador O ganhou\n");
                printf("Pressione qualquer tecla para encerrar...");
                getchar();
                getchar();
                return 0;
            }
        } else {
            printf("Jogada do bot\n");
            game[botMove(game, i)] = 'X';
            showGame(game);
            if(winX(game)) {
                printf("O bot X ganhou\n");
                printf("Pressione qualquer tecla para encerrar...");
                getchar();
                getchar();
                return 0;
            }
        }
    }
    printf("Deu velha\n");
    showGame(game);
    printf("Pressione qualquer tecla para encerrar...\n");
    pause();
    return 0;
}