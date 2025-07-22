package matrizes;

import java.util.ArrayList;
import java.util.List;

public class MatrizList {
    private static int idCount;
    private final List<Matriz> matrizList;
    public MatrizList() {
        this.matrizList = new ArrayList<>();
    }

    public void add(final Matriz matriz) {
        if(getById(matriz.getId()) == null && matriz.getId() == 0) {
            idCount++;
            matriz.setId(idCount);
        }
        matrizList.add(matriz);
    }

    public List<Matriz> get() {
        return matrizList;
    }

    public Matriz getById(final int id) {
        Matriz matriz = null;
        for(Matriz m : matrizList) {
            if (m.getId() == id) {
                matriz = m;
                break;
            }
        }
        return matriz;
    }

    public void remove(final int id) {
        for(Matriz m : matrizList) {
            if (m.getId() == id) {
                matrizList.remove(m);
                return;
            }
        }
    }

    public Matriz update(final Matriz updMatriz, final int id) {
        if(getById(id) != null){
            remove(id);
            updMatriz.setId(id);
            add(updMatriz);
            if(getById(id) != updMatriz) {
                return null;
            }
            return updMatriz;
        }
        return null;
    }

}
