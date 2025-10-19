package test;


import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Test0 {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph grafo = new SingleGraph("Grafo Visual");
        grafo.setAttribute("ui.stylesheet",
                "node { fill-color: lightblue; size: 30px; text-alignment: center; text-size: 20; }" +
                        "edge { fill-color: gray; text-alignment: above; text-size: 15; }"
        );

        Node a = grafo.addNode("A");
        a.setAttribute("ui.label", "A");
        Node b = grafo.addNode("B");
        b.setAttribute("ui.label", "B");
        Node c = grafo.addNode("C");
        c.setAttribute("ui.label", "C");

        Edge ab = grafo.addEdge("AB", "A", "B");
        ab.setAttribute("weight", 5);
        ab.setAttribute("ui.label", "AB (5)");
        Edge bc = grafo.addEdge("BC", "B", "C");
        bc.setAttribute("weight", 3);
        bc.setAttribute("ui.label", "BC (3)");
        Edge ac = grafo.addEdge("AC", "A", "C");
        ac.setAttribute("weight", 7);
        ac.setAttribute("ui.label", "AC (7)");

        grafo.display();
    }
}
