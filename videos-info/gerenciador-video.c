#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int horas, minutos, segundos;
} Tempo;

typedef struct {
    char titulo[20];
    char descricao[200];
    int likes, dislikes, vizualizacoes;
    Tempo duracao;
} Video;

typedef struct no {
    Video video;
    struct no *proximo;
} No;

typedef struct {
    int tamanho;
    No *comeco;
} Lista;

void limparBuffer() {
    while((getchar()) != '\n');
}

int lerInteiroPositivo() {
    int num, ret;
    do {
        ret = scanf("%d", &num);
        limparBuffer();
    } while(ret != 1 || num < 0);
    return num;
}

Tempo lerTempo() {
    Tempo t;
    printf("Informe a duracao do video\n");
    printf("Horas: ");
    t.horas = lerInteiroPositivo();
    printf("Minutos: ");
    t.minutos = lerInteiroPositivo();
    printf("Segundos: ");
    t.segundos = lerInteiroPositivo();
    if(t.segundos > 59) {
        while(t.segundos > 59) {
            t.segundos -= 60;
            t.minutos++;
        }
    }
    if(t.minutos > 59) {
        while(t.minutos > 59) {
            t.minutos -= 60;
            t.horas++;
        }
    }
    return t;
}

int tempoMaisCurto(Tempo tempo, Tempo tempoComparado) {
    if(tempo.horas < tempoComparado.horas) {
        return 1;
    }
    if(tempo.horas == tempoComparado.horas) {
        if(tempo.minutos < tempoComparado.minutos) {
            return 1;
        }
        if(tempo.minutos == tempoComparado.minutos) {
            if(tempo.segundos < tempoComparado.segundos) {
                return 1;
            }
        }
    }
    return 0;
}

Video lerVideo() {
    Video v;
    printf("Informe o titulo do video: ");
    scanf("%19[^\n]", v.titulo);
    // ';' e usado como separador de elementos do struct video entao ele nao pode ser usado
    v.titulo[strcspn(v.titulo, ";")] = v.titulo[strcspn(v.titulo, ";")] == ';' ? ',' : '\0';
    limparBuffer();
    printf("Informe a descricao do video: ");
    scanf("%199[^\n]", v.descricao);
    v.descricao[strcspn(v.descricao, ";")] = v.descricao[strcspn(v.descricao, ";")] == ';' ? ',' : '\0';
    limparBuffer();
    v.duracao = lerTempo();
    printf("Informe o numero de likes: ");
    v.likes = lerInteiroPositivo();
    printf("Informe o numero de dislikes: ");
    v.dislikes = lerInteiroPositivo();
    printf("Informe o numero de visualizacoes: ");
    v.vizualizacoes = lerInteiroPositivo();
    return v;
}

void printTempo(Tempo t) {
    printf("%02d:%02d:%02d", t.horas, t.minutos, t.segundos);
}

void printVideo(Video *v) {
    if(v) {
        printf("Titulo: %s\n", v -> titulo);
        printf("Duracao: ");
        printTempo(v -> duracao);
        printf("\tVisualizacoes: %d\nLikes: %d\tDislikes: %d\nDescricao: %s\n", 
            v -> vizualizacoes, v -> likes, v -> dislikes, v -> descricao);
    }
}

void iniciarLista(Lista **lista) {
    *lista = malloc(sizeof(Lista));
    if(*lista) {
        (*lista) -> tamanho = 0;
        (*lista) -> comeco = NULL;
    }
}

void addVideo(Lista *lista, Video video) {
    No *no = malloc(sizeof(No));
    if(no) {
        no -> video = video;
        no -> proximo = NULL;
        if(lista -> comeco) {
            No *auxiliar = lista -> comeco;
            while(auxiliar -> proximo) {
                auxiliar = auxiliar -> proximo;
            }
            auxiliar -> proximo = no;
        } else {
            lista -> comeco = no;
        }
        lista -> tamanho++;
    }
    
}

Video* buscarVideo(Lista *lista, char *titulo) {
    Video *v = NULL;
    No *no = lista -> comeco;
    while(no && strcmp(titulo, no -> video.titulo) != 0) {
        no = no -> proximo;
    }
    if(no) {
        v = &no -> video;
    }
    return v;
}

void listarVideos(Lista *lista) {
    No *no = lista -> comeco;
    printf("\n-------Lista de Videos-------\n");
    printf("Tamanho da lista: %d\n", lista -> tamanho);
    printf("-----------------------------\n");
    while (no) {
        printVideo(&no -> video);
        no = no -> proximo;
        printf("-----------------------------\n");
    }
}

No* removerVideo(Lista *lista, char *titulo) {
    No *no = NULL;
    if(lista && lista -> comeco) {
        if(strcmp(titulo, lista -> comeco -> video.titulo) == 0) {
            no = lista -> comeco;
            if(lista -> comeco -> proximo) {
                lista -> comeco = no -> proximo;
            } else {
                lista -> comeco = NULL;
            }
            lista -> tamanho--;
        } else {
            No *suporte = lista -> comeco;
            while(suporte -> proximo && strcmp(titulo, suporte -> proximo -> video.titulo) != 0) {
                suporte = suporte -> proximo;
            }
            if(suporte) {
                no = suporte -> proximo;
                suporte -> proximo = no -> proximo;
            }
        }
    }

    return no;
}

void salvarLista(Lista *lista) {
    FILE *f;
    f = fopen("videos.txt", "w");
    if(f) {
        if(lista && lista -> comeco) {
            No *no = lista -> comeco;
            while(no) {
                Video video = no -> video;
                fprintf(f, "%s;%d:%d:%d;%d;%d;%d;%s\n", video.titulo, video.duracao.horas, video.duracao.minutos, 
                    video.duracao.segundos, video.likes, video.dislikes, video.vizualizacoes,
                    video.descricao);
                no = no -> proximo;
            }
            printf("\nLista salva com sucesso!\n");
        } else {
            printf("\n\tLista vazia!\n");
        }
        fclose(f);
    } else {
        printf("\n\tERRO: Não foi possível salvar a lista\n");
    }
}

void resgatarLista(Lista *lista) {
    FILE *f;
    f = fopen("videos.txt", "r");
    if(f) {
        Video video;
        while(fscanf(f, "%19[^;];%d:%d:%d;%d;%d;%d;%199[^\n]", video.titulo, &video.duracao.horas, &video.duracao.minutos, 
        &video.duracao.segundos, &video.likes, &video.dislikes, &video.vizualizacoes, video.descricao) == 8) 
        {
            addVideo(lista, video);
            fgetc(f);
        }
        printf("\nLista resgatada com sucesso!");
        fclose(f);
    } else {
        printf("\n\tERRO: Não foi possível ler o arquivo\n");
    }
}

Tempo somarTempos(Tempo t1, Tempo t2) {
    Tempo soma;
    soma.horas = 0;
    soma.minutos = 0;
    soma.segundos = t1.segundos + t2.segundos;
    while(soma.segundos > 59) {
        soma.segundos -= 60;
        soma.minutos++;
    }
    soma.minutos += t1.minutos + t2.minutos;
    while(soma.minutos > 59) {
        soma.minutos -= 60;
        soma.horas++;
    }
    soma.horas += t1.horas + t2.horas;
    return soma;
}

void informacoesExtras(Lista *lista) {
    if(lista && lista -> comeco) {
        No *no = lista -> comeco;
        Video vMaisLongo = no -> video;
        Video vMaisCurto = no -> video;
        Video vMaisGostado = no -> video;
        Video vMenosGostado = no -> video;
        Video vMaisVisualizado = no -> video;
        int totalLikes = 0;
        int totalDislikes = 0;
        int totalVisualizacoes = 0;
        double mediaVisualizacoes = 0.0;
        double mediaLikes = 0.0;
        double mediaDislikes = 0.0;
        Tempo tempoTotalVideos;
        tempoTotalVideos.horas = 0;
        tempoTotalVideos.minutos = 0;
        tempoTotalVideos.segundos = 0;
        while(no) {
            if(tempoMaisCurto(no -> video.duracao, vMaisCurto.duracao)) {
                vMaisCurto = no -> video;
            }
            if(!tempoMaisCurto(no -> video.duracao, vMaisLongo.duracao)) {
                vMaisLongo = no -> video;
            }
            if(vMaisGostado.likes < no -> video.likes) {
                vMaisGostado = no -> video;
            }
            if(vMenosGostado.dislikes < no -> video.dislikes) {
                vMenosGostado = no -> video;
            }
            if(vMaisVisualizado.vizualizacoes < no -> video.vizualizacoes) {
                vMaisVisualizado = no -> video;
            }
            totalLikes += no -> video.likes;
            totalDislikes += no -> video.dislikes;
            totalVisualizacoes += no -> video.vizualizacoes;
            tempoTotalVideos = somarTempos(tempoTotalVideos, no -> video.duracao);
            no = no -> proximo;
        }
        mediaLikes = (double) totalLikes / lista -> tamanho;
        mediaDislikes = (double) totalDislikes / lista -> tamanho;
        mediaVisualizacoes = (double) totalVisualizacoes / lista -> tamanho;

        printf("\n------Informacoes Extras------\n");
        printf("Total de videos: %d\n", lista -> tamanho);
        printf("\nTotal de visualizacoes: %d\n", totalVisualizacoes);
        printf("\nTotal de likes: %d\n", totalLikes);
        printf("\nTotal de dislikes: %d\n", totalDislikes);
        printf("\nMedia de visualizacoes: %.2f\n", mediaVisualizacoes);
        printf("\nMedia de likes: %.2f\n", mediaLikes);
        printf("\nMedia de dislikes: %.2f\n", mediaDislikes);
        printf("\nTempo total de conteudo: ");
        printTempo(tempoTotalVideos);
        printf("\n------------------------------\n");
        printf("Video mais longo: \n");
        printVideo(&vMaisLongo);
        printf("------------------------------\n");
        printf("Video mais curto: \n");
        printVideo(&vMaisCurto);
        printf("------------------------------\n");
        printf("Video mais visualizado: \n");
        printVideo(&vMaisVisualizado);
        printf("------------------------------\n");
        printf("Video mais gostado: \n");
        printVideo(&vMaisGostado);
        printf("------------------------------\n");
        printf("Video menos gostado: \n");
        printVideo(&vMenosGostado);
        printf("------------------------------\n");
    } else {
        printf("Nenhuma informacao extra disponivel no momento\n");
    }
}

int main(void) {
    Lista *lista = NULL;
    iniciarLista(&lista);
    resgatarLista(lista);
    int opcao;
    char titulo[20];
    do {
        printf("\n\t---Menu---\n");
        printf("\t0 - Sair\n\t1 - Adicionar\n\t2 - Buscar\n\t3 - Listar\n\t4 - Remover\n\t5 - Salvar\n\t6 - Extras\n");
        printf("\t-> ");
        opcao = lerInteiroPositivo();
        switch (opcao)
        {
        case 0:
            printf("\n\tSaindo...\n");
            break;
        case 1:
            addVideo(lista, lerVideo());
            salvarLista(lista);
            break;
        case 2:
            printf("Informe o titulo do video que deseja buscar: ");
            scanf("%19[^\n]", titulo);
            limparBuffer();
            Video *video = buscarVideo(lista, titulo);
            if(video) {
                printf("Video encontrado!\n\n");
                printVideo(video);
            } else {
                printf("\n\tVideo nao encontrado\n");
            }
            break;
        case 3:
            listarVideos(lista);
            break;
        case 4:
            printf("Informe o titulo do video que deseja remover: ");
            scanf("%19[^\n]", titulo);
            limparBuffer();
            No *remover = removerVideo(lista, titulo);
            if(remover) {
                printf("Video removido!\n\n");
                printVideo(&remover -> video);
                free(remover);
            } else {
                printf("\n\tVideo nao encontrado\n");
            }
            salvarLista(lista);
            break;
        case 5:
            salvarLista(lista);
            break;
        case 6:
            informacoesExtras(lista);
            break;
        default:
        printf("\n\tOpcao invalida\n");
            break;
        }
    } while(opcao != 0);

    salvarLista(lista);

    return EXIT_SUCCESS;
}