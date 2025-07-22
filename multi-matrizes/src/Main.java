import matrizes.Matriz;
import matrizes.MatrizList;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static MatrizList matrizList = new MatrizList();
    public static void main(String[] args) {
        int opt = -1;
        do {
            System.out.println("-----------Menu-----------");
            System.out.println("[1] Inserir Matriz");
            System.out.println("[2] Atualizar Matriz");
            System.out.println("[3] Excluir Matriz");
            System.out.println("[4] Exibir Matriz");
            System.out.println("[5] Listar Matrizes");
            System.out.println("[6] Multiplicar Matrizes");
            System.out.println("[0] Sair");
            System.out.println("--------------------------");
            System.out.println("informe a opção desejada: ");
            scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            switch (opt) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> remove();
                case 4 -> show();
                case 5 -> showAll();
                case 6 -> multiple();
                case 0 -> System.out.println("Saindo....");
                default -> System.out.println("[3RR0R] Opção inválida");
            }
        } while(opt != 0);
        scanner.close();
    }
    public static void add() {
        int row;
        do {
            System.out.println("Informe o número de linhas da matriz: ");
            row = scanner.nextInt();
            if(row < 1) {
                System.out.println("[3RR0R] O número de linhas deve ser maior que zero");
            }
        } while (row < 1);
        int col;
        do {
            System.out.println("Informe o número de colunas da matriz: ");
            col = scanner.nextInt();
            if(col < 1) {
                System.out.println("[3RR0R] O número de colunas deve ser maior que zero");
            }
        } while (col < 1);
        Matriz matriz = new Matriz(row, col);
        matriz = insertMatrizValues(matriz);
        matrizList.add(matriz);
    }
    public static void update() {
        System.out.println("Informe o Id da matriz que deseja atualizar: ");
        int id = scanner.nextInt();
        if(!existentId(id)) {
            System.out.println("[3RR0R] Id inexistente");
            return;
        }
        int row;
        do {
            System.out.println("Informe o novo número de linhas da matriz: ");
            row = scanner.nextInt();
            if(row < 1) {
                System.out.println("[3RR0R] O número de linhas deve ser maior que zero");
            }
        } while (row < 1);
        int col;
        do {
            System.out.println("Informe o novo número de colunas da matriz: ");
            col = scanner.nextInt();
            if(col < 1) {
                System.out.println("[3RR0R] O número de colunas deve ser maior que zero");
            }
        } while (col < 1);
        Matriz matriz = new Matriz(row, col);
        matriz = insertMatrizValues(matriz);
        if (matrizList.update(matriz, id) == null) {
            System.out.println("[3RR0R] Um erro inesperado aconteceu ao atualizar a matriz");
            return;
        }
        System.out.println("Matriz atualizada com sucesso");
    }
    public static void remove() {
        System.out.println("Informe o Id da matriz que deseja excluir: ");
        int id = scanner.nextInt();
        if(!existentId(id)) {
            System.out.println("[3RR0R] Id inexistente");
            return;
        }
        matrizList.remove(id);
        if(existentId(id)) {
            System.out.println("[3RR0R] Um erro inesperado aconteceu ao excluir a matriz");
            return;
        }
        System.out.println("Matriz excluida com sucesso");
    }
    public static void show() {
        System.out.println("Informe o id da matriz que deseja ver: ");
        int id = scanner.nextInt();
        if(!existentId(id)) {
            System.out.println("[3RR0R] Id inexistente");
            return;
        }
        Matriz matriz = matrizList.getById(id);
        matriz.showMatriz();
    }
    public static void showAll() {
        if(matrizList.get().isEmpty()) {
            System.out.println("[3RR0R] Nenhuma matriz foi cadastrada");
            return;
        }
        for (Matriz m : matrizList.get()) {
            m.showMatriz();
        }
    }
    public static void multiple() {
        Matriz matriz1 = null;
        int id=-1;
        do {
            System.out.println("Informe o id da primeira matriz: ");
            id = scanner.nextInt();
            if(!existentId(id)) {
                System.out.println("[3RR0R] Id inexistente");
            }
        } while(!existentId(id));
        matriz1 = matrizList.getById(id);
        Matriz matriz2 = null;
        id=-1;
        do {
            System.out.println("Informe o id da primeira matriz: ");
            id = scanner.nextInt();
            if(!existentId(id)) {
                System.out.println("[3RR0R] Id inexistente");
            }
        } while(!existentId(id));
        matriz2 = matrizList.getById(id);
        if(matriz1.getCol() != matriz2.getRow()) {
            System.out.println("O número de linhas da segunda matriz deve ser igual ao número de colunas da primeira matriz");
            return;
        }
        Matriz multMatriz = matrizMultResult(matriz1,matriz2);
        multMatriz.showMatriz();
        System.out.println("Deseja salvar essa nova matriz? S/N");
        String answer = scanner.next();
        if(answer.toUpperCase().equals("S")) {
            matrizList.add(multMatriz);
        }
    }

    public static Matriz insertMatrizValues(final Matriz matriz) {
        int value;
        for (int i = 0; i < matriz.getRow(); i++) {
            for (int j = 0; j < matriz.getCol(); j++) {
                System.out.println("Informe o valor do termo presente na linha " + (i+1) + " e coluna " + (j+1) +": ");
                value = scanner.nextInt();
                matriz.setValue(i, j, value);
            }
        }
        return matriz;
    }
    public static boolean existentId(final int id) {
        if(matrizList.getById(id) != null) {
            return true;
        }
        return false;
    }
    public static Matriz matrizMultResult(final Matriz matriz1, final Matriz matriz2) {
        int rows1 = matriz1.getRow();
        int cols1 = matriz1.getCol();
        int cols2 = matriz2.getCol();
        Matriz matrizResult = new Matriz(rows1, cols2);
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                int calc = 0;
                for (int k = 0; k < cols1; k++) {
                    calc += matriz1.getValue(i,k) * matriz2.getValue(k,j);

                }
                matrizResult.setValue(i,j,calc);
            }
        }
        return matrizResult;
    }
}