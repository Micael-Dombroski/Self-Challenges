#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 256

typedef struct node {
    unsigned char charater;
    unsigned int frequency;
    struct node *left, *right, *next;
} Node;

typedef struct {
    Node *start;
    int size;
} List;

//-----------Huffman Coding-----------//

//frequency table
void fill_frequency_table(unsigned char text[], unsigned int tab[]) {
    int i = 0;
    while(text[i] != '\0') {
        tab[text[i]]++;
        i++;
    }
}

void print_frequency_table(unsigned int tab[]) {
    for(int i = 0; i < SIZE; i++) {
        if(tab[i] > 0) {
            printf("\t%3d = %u = %c\n", i, tab[i], i);
        }
    }
}

//list
void create_list(List *list) {
    list -> start = NULL;
    list -> size = 0;
}

void ordered_insert(List *list, Node *node) {
    if(list -> start) {
        if(list -> start -> frequency > node -> frequency) {
            node -> next = list -> start;
            list -> start = node;
        } else {
            Node *sup = list -> start;
            while(sup -> next && sup -> next -> frequency <= node -> frequency) {
                sup = sup -> next;
            }
            node -> next = sup -> next;
            sup -> next = node;
        }
    } else {
        list -> start = node;
    }
    list -> size++;
}

void fill_list(unsigned int tab[], List *list) {
    for(int i = 0; i< SIZE; i++) {
        if(tab[i] > 0) {
            Node *new = malloc(sizeof(Node));
            if(new) {
                new -> charater = i;
                new -> frequency = tab[i];
                new -> next = NULL;
                new -> left = NULL;
                new -> right = NULL;
                ordered_insert(list, new);
            } else {
                printf("\tFailed to allocate memory in fill_list\n");
                break;
            }
        }
    }
}

void print_list(List *list) {
    if(list -> start) {
        Node *sup = list -> start;
        printf("\n\tOrdered list: Size: %d\n", list -> size);
        while(sup) {
            printf("\tCharacter: %c Frequency %u\n", sup -> charater, sup -> frequency);
            sup = sup -> next;
        }
    }
}

//binary-tree
Node* remove_start_node(List *list) {
    Node *sup = NULL;
    if(list -> start) {
        sup = list -> start;
        list -> start = sup -> next;
        list -> size--;
        sup -> next = NULL;
    }
    return sup;
}

Node* build_tree(List *list) {
    Node *first, *second, *new = NULL;
    while(list -> size > 1) {
        first = remove_start_node(list);
        second = remove_start_node(list);
        new = malloc(sizeof(Node));
        if(new) {
            new -> charater = '+';
            new -> frequency = first -> frequency + second -> frequency;
            new -> left = first;
            new -> right = second;
            new -> next = NULL;
            ordered_insert(list, new);
        } else {
            printf("\n\tFailed to allocate memory on build_tree\n");
            break;
        }
    }
    return list -> start;
}

void print_tree(Node *root, int size) {
    if(!root -> left && !root -> right) {
        printf("\tLeaf: %c\tHeight: %d\n", root -> charater, size);
    } else {
        print_tree(root -> left, size + 1);
        print_tree(root -> right, size +1);
    }
}

int tree_height(Node *root) {
    if(root) {
        int hLeft = tree_height(root -> left);
        int hRight = tree_height(root -> right);
        if(hLeft > hRight) {
            return hLeft + 1;
        } else {
            return hRight + 1;
        }
    } else {
        return -1;
    }
}

//Dictionary
char** dictionary_allocate(int collumns) {
    char **dictionary;
    if((dictionary = malloc(sizeof(char*) * SIZE))) {
        for(int i = 0; i < SIZE; i++) {
            if(!(dictionary[i] = calloc(collumns, sizeof(char)))) {
                printf("\n\tFailed to allocate memory on dictionary_allocate\n");
                return NULL;
            }
        }
        return dictionary;
    } else {
        printf("\n\tFailed to allocate memory on dictionary_allocate\n");
        return NULL;
    }
}

void dictionary_generate(char** dictionary, Node *root, char *path, int collumns) {
    char left[collumns], right[collumns];
    if(!root -> left && !root -> right) {
        strcpy(dictionary[root -> charater], path);
    } else {
        strcpy(left, path);
        strcpy(right, path);

        strcat(left, "0");
        strcat(right, "1");

        dictionary_generate(dictionary, root -> left, left, collumns);
        dictionary_generate(dictionary, root -> right, right, collumns);
    }
}

void print_dictionary(char **dictionary) {
    for(int i = 0; i < SIZE; i++) {
        if(strlen(dictionary[i]) > 0) {
            if (i < 32 || i > 126) // caracteres não imprimíveis
                printf("\t%3d: 0x%02X : %s\n", i, i, dictionary[i]);
            else
                printf("\t%3d: '%c' : %s\n", i, i, dictionary[i]);
        }
    }
}

//coding text
int string_size_calc(char **dicionary, unsigned char *text) {
    int i = 0, size = 0;
    while(text[i] != '\0') {
        size = size + strlen(dicionary[text[i]]);
        i++;
    }
    return size + 1;
}

char* coding(char **dictionary, unsigned char *text) {
    int size = string_size_calc(dictionary, text);
    char *code;
    if((code = calloc(size, sizeof(char)))) {
        int i = 0;
        while(text[i] != '\0') {
            strcat(code, dictionary[text[i]]);
            i++;
        }
    } else {
        printf("\n\tFailed to allocate memory on coding\n");
    }
    return code;
}

//decoding text
char* decoding(char *code, Node *root) {
    char *decode;
    if((decode = calloc(strlen(code), sizeof(char)))) {
        int i = 0;
        Node *sup = root;
        while(code[i] != '\0') {
            if(!sup -> left && !sup -> right) {
                char str[2];
                str[0] = sup -> charater;
                str[1] = '\0';
                strcat(decode, str);
                sup = root;
            }
            if(code[i] ==  '0') {
                sup = sup -> left;
            }
            if(code[i] == '1') {
                sup = sup -> right;
            }
            i++;
        }
    } else {
        printf("\n\tFailed to allocate memory on decoding\n");
    }
    return decode;
}

//compacting
void compact(unsigned char str[], char name[], unsigned int freq_table[]) {
    FILE *file = fopen(name, "wb");
    int i = 0, j = 7;
    unsigned char byte = 0, mask;
    unsigned int buffer_size = 0;
    int compact_bytes = 0;
    if(file) {
        unsigned char *buffer = malloc(1);
        for(i = 0; i < SIZE; i++) {
            if(freq_table[i] > 0) {
                unsigned char c = (unsigned char) i;
                fwrite(&c, sizeof(unsigned char), 1, file);
                fwrite(&freq_table[i], sizeof(unsigned int), 1, file);
            }
        }
        unsigned char end = 0xFF;
        unsigned int zero = 0;

        fwrite(&end, sizeof(unsigned char), 1, file);
        fwrite(&zero, sizeof(unsigned int), 1, file);
        i = 0;
        while(str[i] != '\0') {
            mask = 1;
            if(str[i] == '1') {
                mask = mask << j;
                byte |= mask;
            }
            i++;
            j--;
            if(j < 0) {
                unsigned char *tmp = realloc(buffer, buffer_size + 1);
                if(!tmp) {
                    printf("\n\tFailed to realloc memory on compactin\n");
                    free(buffer);
                    fclose(file);
                    return;
                }
                buffer = tmp;
                buffer[buffer_size++] = byte;
                compact_bytes++;
                byte = 0;
                j = 7;
            }
        }
        if(j != 7) {
            unsigned char *tmp = realloc(buffer, buffer_size + 1);
            if(!tmp) {
                printf("\n\tFailed to realloc memory on compactin\n");
                free(buffer);
                fclose(file);
                return;
            }
            buffer = tmp;
            buffer[buffer_size++] = byte;
            compact_bytes++;
        }
        unsigned char valid_bits = 8 - (j + 1); 
        fwrite(&valid_bits, sizeof(unsigned char), 1, file);
        fwrite(&compact_bytes, sizeof(unsigned int), 1, file);
        for(i = 0; i < buffer_size; i++) {
            fwrite(&buffer[i], sizeof(unsigned char), 1, file);
        }
        free(buffer);
        fclose(file);
    } else {
        printf("\n\tFailed to open/creat the file on compact\n");
    }
}

void print_compact_file() {
    FILE *file;
    if((file = fopen("file.wg", "rb"))) {
        unsigned char byte;
        while(fread(&byte, sizeof(unsigned char), 1, file)) {
            printf("%c", byte);
        }
        printf("\n");
        fclose(file);
    } else {
        printf("\n\tFailed to open the file on print_compact_file\n");
    }
}

unsigned int is_bit_one(unsigned char byte, int i) {
    unsigned char mask = (1 << i);
    return byte & mask;
}

void read_freq_table(FILE *file, unsigned int freq_table[]) {
    unsigned char c;
    unsigned int freq;
    while(1) {
        if(fread(&c, sizeof(unsigned char), 1, file) != 1) {
            printf("\n\tError on read_freq_table\n");
            return;
        }
        if(fread(&freq, sizeof(unsigned int), 1, file) != 1) {
            printf("\n\tError on read_freq_table\n");
            return;
        }
        if(c == 0xFF && freq == 0) {
            break;
        }
        freq_table[c] = freq;
    }
}

//descompacting
unsigned char* descompact(char *file_name, unsigned int freq_table[]) {
    unsigned char *text = calloc(1, sizeof(unsigned char));
    char str[2];
    FILE *file;
    if((file = fopen(file_name, "rb"))) {
        fseek(file, 0, SEEK_END);
        long file_size = ftell(file);
        rewind(file);
        printf("\n\tGetting frequency table...\n");
        read_freq_table(file, freq_table);
        unsigned char last_bits;
        fread(&last_bits, sizeof(unsigned char), 1, file);
        unsigned int compact_bytes;
        fread(&compact_bytes, sizeof(unsigned int), 1, file);
        List list;
        printf("\n\tCreating the list...\n");
        create_list(&list);
        fill_list(freq_table, &list);
        printf("\n\tBuilding binary-tree...\n");
        Node *root = build_tree(&list);
        Node *sup = root;
        int i = 0;
        unsigned char byte;
        printf("\n\tDescompacting the text...\n");
        while(fread(&byte, sizeof(unsigned char), 1, file)) {
            int max_bits = 8;
            if (compact_bytes == 0) {
                max_bits = last_bits;
            }
            for (i = 7; i >= 8 - max_bits; i--) {
                if(is_bit_one(byte, i)) {
                    sup = sup -> right;
                } else {
                    sup = sup -> left;
                }
                if(!sup -> left && !sup -> right) {
                    str[0] = sup -> charater;
                    str[1] = '\0';
                    strcat(text, str);
                    text = realloc(text, (strlen(text) + 2));
                    sup = root;
                }
            }
            compact_bytes--;
        }
        fclose(file);
    } else {
        printf("\n\tFailed to open the file on descompact\n");
        return NULL;
    }
    return text;
}

void get_file_name(char name[]) {
    scanf("%49[^\n]", name);
    while((getchar()) != '\n');
}

long file_size(char name[]) {
    FILE *file;
    if((file = fopen(name, "r"))) {
        fseek(file, 0, SEEK_END);
        long size = ftell(file);
        fclose(file);
        return size;
    } else {
        printf("\n\tFailed to open file on file_size\n");
        return -1;
    }
}

char* read_file(char name[], char *extension) {
    char *file_name = malloc(sizeof(char) * ((strlen(name) + strlen(extension)) + 1));
    if(file_name) {
        file_name[0] = '\0';
        strcat(file_name, name);
        strcat(file_name, extension);
        printf("%s\n", file_name);
        long size = file_size(file_name);
        if(size == -1) {
            return NULL;
        }
        FILE *file;
        printf("File size: %ld\n", size);
        if((file = fopen(file_name, (strcmp(extension, ".bin") == 0 || strcmp(extension, ".wd") == 0) ? "rb" : "r"))) {
            char *text, byte;
            if((text = calloc(size, sizeof(char)))) {
                while(fread(&byte, sizeof(char), 1, file)) {
                    char str[2];
                    str[0] = byte;
                    str[1] = '\0';
                    strcat(text, str);
                }
            } else {
                printf("\n\tFailed to allocate memory on read_file\n");
                return NULL;
            }
            fclose(file);
            return text;
        } else {
            printf("\n\tFailed to open file on read_file\n");
            return NULL;
        }
    } else {
        printf("\n\tFailed to allocate memory on read_file\n");
    }
    return NULL;;
}

int get_positive_integer() {
    int ret = 0, num;
    do {
        scanf("%d", &num);
        while((getchar()) != '\n');
    } while(ret != 0 || num  < 0);
    return num;
}

void free_tree(Node *root) {
    if (!root) return;
    free_tree(root->left);
    free_tree(root->right);
    free(root);
}

void compact_file_menu() {
    int opt, collumns;
    char file_name[50], *content = NULL, **dictionary = NULL;
    unsigned int frequency_table[SIZE] = {0};
    List list;
    create_list(&list);
    Node *tree = NULL;
    do {
        printf("\n\t====Compact File Menu====\n");
        printf("\t[1] Compact\n\t[2] Show Frequency Table\n\t[3] Show List\n\t[4] File Content\n\t[5] Dictionary\n\t[0] Return\n");
        opt = get_positive_integer();
        switch (opt)
        {
        case 1:
            if(content) {
                free(content);
                content = NULL;
            }
            printf("Enter the file name: \n");
            memset(frequency_table, 0, sizeof(frequency_table));
            create_list(&list);
            get_file_name(file_name);
            content = read_file(file_name, ".txt");
            if(content) {
                fill_frequency_table(content, frequency_table);
            } else {
                printf("\n\tThere is no content to create the frequency table\n");
            }
            fill_list(frequency_table, &list);
            if(list.start) {
                tree = build_tree(&list);
            } else {
                printf("\n\tThere is no content in the list to create a binary-tree\n");
            }
            if(tree) {
                collumns = tree_height(tree);
                dictionary = dictionary_allocate(collumns);
                dictionary_generate(dictionary, tree, "", collumns);
            } else {
                printf("\n\tYou can't create a dictionary with an empty binary-tree\n");
            }
            char *file_comp = calloc((strlen(file_name) + 5), sizeof(char));
            strcat(file_comp, file_name);
            strcat(file_comp, ".bin");
            char *code_content = coding(dictionary, content);
            compact(code_content, file_comp, frequency_table);
            free(file_comp);
            free(code_content);
            break;
        case 2:
            if(content) {
                print_frequency_table(frequency_table);
            } else {
                printf("\n\tThere is no content to create the frequency table\n");
            }
            break;
        case 3:
            if(list.start) {
                print_list(&list);
            } else {
                printf("\n\tList is empty\n");
            }
            break;
        case 4:
            printf("File content: \n");
            if(content) {
                printf("%s\n", content);
            } else {
                printf("\n\tThere is no content on the file\n");
            }
            break;
        case 5:
            if(tree) {
                print_dictionary(dictionary); 
            } else {
                printf("\n\tYou can't have a dictionary with an empty binary-tree\n");
            }
            break;
        case 0:
            printf("\nReturning...\n");
            break;
        default:
            printf("Invalid option\n");
            break;
        }
    } while (opt != 0);
    Node *sup = list.start;
    while(sup) {
        Node *node = sup;
        sup = node -> next;
        free(node);
    }
    if(tree) {
        for(int i = 0; i < SIZE; i++) {
            free(dictionary[i]);
        }
        free(dictionary);
        free_tree(tree);
    }
    free(content);
}

void descompact_file_menu() {
    int opt;
    char file_name[50], *content = NULL;
    unsigned int frequency_table[SIZE] = {0};
    do {
        printf("\n\t=====Descompact File Menu=====\n");
        printf("\t[1] Descompact\n\t[2] Show Content\n\t[0] Return\n");
        opt = get_positive_integer();
        switch (opt)
        {
        case 1:
            if(content) {
                free(content);
                content = NULL;
            }
            printf("Enter the file name: \n");
            get_file_name(file_name);
            strcat(file_name, ".bin");
            memset(frequency_table, 0, sizeof(frequency_table));
            content = descompact(file_name, frequency_table);
            if(!content) {
                printf("\n\tThere is no content to create the frequency table\n");
            }
            break;
        case 2:
            printf("\nDescompacted content: \n");
            printf("%s\n", content);
            break;
        case 0:
            printf("\n\tReturning...\n");
            break;
        default:
            printf("Invalid option\n");
            break;
        }
    } while(opt != 0);
    free(content);
}

int main(void) {
    int opt;
    do {
        printf("\n\t=====Menu=====\n");
        printf("\t[1] Compact Menu\n\t[2] Descompact Menu\n\t[0] Exit\n");
        opt = get_positive_integer();
        switch (opt)
        {
        case 1:
            compact_file_menu();
            break;
        case 2:
            descompact_file_menu();
            break;
        case 0:
            printf("\n\tExiting...\n");
            break;
        default:
            printf("Invalid option\n");
            break;
        }
    } while(opt != 0);
    return EXIT_SUCCESS;
}