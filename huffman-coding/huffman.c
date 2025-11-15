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
    while(text[i] != '\n') {
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
            while(sup -> next && sup -> next -> frequency < node -> frequency) {
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
    Node *new;
    for(int i = 0; i < SIZE; i++) {
        if(tab[i] > 0) {
            if((new = calloc(1, sizeof(Node)))) {
                new -> charater = i;
                new -> frequency = tab[i];
                new -> next = NULL;
                new -> left = NULL;
                new -> right = NULL;
                ordered_insert(list, new);
            } else {
                printf("\n\tFailed to allocate memory on fill_list\n");
            }  
        }
    }
}

void print_list(List *list) {
    if(list -> start) {
        Node *sup = list -> start;
        printf("\n\tOrder List (Size: %d)\n", list -> size);
        while(sup) {
            printf("\tCharacter :%c\tFrequency: %u\n", sup -> charater, sup -> frequency);
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
        if((new = malloc(sizeof(Node)))) {
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
        printf("\tLeaft: %c\tHeight: %d\n", root -> charater, size);
    } else {
        print_tree(root -> left, size + 1);
        print_tree(root -> right, size + 1);
    }
}

int tree_height(Node *root) {
    if(root) {
        int height_left = tree_height(root -> left);
        int height_right = tree_height(root -> right);
        if(height_left > height_right) {
            return height_left + 1;
        } else {
            return height_right + 1;
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
void compact(unsigned char str[], char name[]) {
    FILE *file = fopen(name, "wb");
    int i = 0, j = 7;
    unsigned char byte = 0, mask;
    if(file) {
        while(str[i] != '\0') {
            mask = 1;
            if(str[i] == '1') {
                mask = mask << j;
                byte |= mask;
            }
            i++;
            j--;
            if(j < 0) {
                fwrite(&byte, sizeof(unsigned char), 1, file);
                byte = 0;
                j = 7;
            }
        }
        if(j != 7) {
            fwrite(&byte, sizeof(unsigned char), 1, file);
        }
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

//descompacting
void descompact(Node *root) {
    FILE *file;
    if((file = fopen("file.wg", "rb"))) {
        Node *sup = root;
        int i = 0;
        unsigned char byte;
        while(fread(&byte, sizeof(unsigned char), 1, file)) {
            for(i = 7; i >= 0; i--) {
                if(is_bit_one(byte, i)) {
                    sup = sup -> right;
                } else {
                    sup = sup -> left;
                }
                if(!sup -> left && !sup -> right) {
                    printf("%c", sup -> charater);
                    sup = root;
                }
            }
        }
        fclose(file);
    } else {
        printf("\n\tFailed to open the file on descompact\n");
    }
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
    char *file_name = calloc((strlen(name) + strlen(extension)), sizeof(char));
    if(file_name) {
        strcat(file_name, name);
        strcat(file_name, extension);
        printf("%s\n", file_name);
        long size = file_size(file_name);
        if(size == -1) {
            return NULL;
        }
        FILE *file;
        printf("File size: %ld\n", size);
        if((file = fopen(file_name, strcmp(extension, ".bin") == 0 || strcmp(extension, ".wd") ? "rb" : "r\0"))) {
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
            return text;
            fclose(file);
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

void compact_file_menu() {
    int opt, collumns;
    char file_name[50], *content = NULL, **dictionary = NULL;
    unsigned int frequency_table[SIZE] = {0};
    List list;
    list.size = 0;
    list.start = NULL;
    Node *tree;
    do {
        printf("\n\t====Compact File Menu====\n");
        printf("\t[1] Compact\n\t[2] Show Frequency Table\n\t[3] Show List\n\t[4] Show Binary-Tree\n\t[5] File Content\n\t[6] Dictionary\n\t[0] Return\n");
        opt = get_positive_integer();
        switch (opt)
        {
        case 1:
            printf("Enter the file name: \n");
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
            strcat(file_comp, ".wd");
            compact(content, file_comp);
            break;
        case 2:
            print_frequency_table(frequency_table);
            break;
        case 3:
            fill_list(frequency_table, &list);
            if(list.start) {
                print_list(&list);
            } else {
                printf("\n\tList is empty\n");
            }
            break;
        case 4:
            print_tree(tree, tree_height(tree));
            break;
        case 5:
            printf("File content: \n");
            if(content) {
                printf("%s\n", content);
            } else {
                printf("\n\tThere is no content on the file\n");
            }
            break;
        case 6:
            print_dictionary(dictionary); 
            break;
        case 0:
            printf("\nReturning...\n");
            break;
        default:
            printf("Invalid option\n");
            break;
        }
    } while (opt != 0);
}

void descompact_file_menu() {

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