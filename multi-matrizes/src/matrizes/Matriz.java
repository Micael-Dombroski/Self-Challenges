package matrizes;

public class Matriz {
    private int id;
    private int row;
    private int col;
    private int[][] values;
    public Matriz(final int row, final int col) {
        this.row = row;
        this.col = col;
        values = new int[row][col];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void setValue(final int l, final int c, final int value) {
        values[l][c] = value;
    }
    public int getValue(final int l, final int c) {
        return values[l][c];
    }
    public int getId() {
        return id;
    }
    protected void setId(final int id) {
        this.id = id;
    }
    public void showMatriz() {
        System.out.println("Matriz de id: " + (id == 0 ? "Não cadastrada" : id));
        System.out.println("-----------------------------");
        for (int i = 0; i < row; i++) {
            System.out.print("|   ");
            for (int j = 0; j < col; j++) {
                System.out.printf("%-5s"+" ",getValue(i, j));
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }
}