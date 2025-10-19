package test;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class UserInterface {
    private Graph grafo;
    JButton verticeBotao = new JButton("Adicionar Vertice");
    JButton arestaBotao = new JButton("Adicionar Aresta");
    JButton grafoBotao = new JButton("Ver Grafo");
    JButton voltarBotao =  new JButton("Voltar");
    public UserInterface(Graph grafo) {
        this.grafo = grafo;
    }
    public UserInterface() {
        this.voltarBotao.setPreferredSize(new Dimension(10, 10));
        grafo = new SingleGraph("Grafo");
        grafo.setAttribute("ui.stylesheet",
                "node { fill-color: lightblue; size: 30px; text-alignment: center; text-size: 20; }" +
                        "edge { fill-color: gray; text-alignment: above; text-size: 15; }"
        );
    }
    public void start() {

    }
    public void painelInicial() {
        while (true) {
            JFrame frame = new JFrame("Grafos - Java Swing");
            frame.setSize(500, 300);
            JPanel painel = new JPanel();
            painel.setSize(300,300);
            painel.setLayout(new GridBagLayout());
            painel.add(verticeBotao);
            painel.add(arestaBotao);
            painel.add(grafoBotao);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            JPanel vertice = new JPanel(new GridLayout(0, 1, 10, 20));
            JButton adicionarVertice = new JButton("Adicionar");
            JLabel vNome = new JLabel("Nome do Vertice:" );
            JTextField nomeTexto = new JTextField(20);
            vertice.add(voltarBotao);
            vertice.add(vNome);
            vertice.add(nomeTexto);
            vertice.add(adicionarVertice);
            frame.add(vertice);
            frame.setVisible(true);
            JPanel aresta = new JPanel(new GridLayout(0, 1, 10, 20));
            JLabel aNome = new JLabel("Nome da Aresta: ");
            JTextField origemT = new JTextField(20);
            JTextField destinoT = new JTextField(20);
            JTextField pesoT = new JTextField(10);
            ((AbstractDocument) pesoT.getDocument()).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException, BadLocationException {
                    if (string.matches("\\d*")) {
                        super.insertString(fb, offset, string, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (text.matches("\\d*")) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });
            JButton adicionarAresta = new JButton("Adicionar");
            aresta.add(adicionarAresta);
            aresta.add(aNome);
            aresta.add(origemT);
            aresta.add(destinoT);
            aresta.add(pesoT);
            frame.add(aresta);
            adicionarVertice.addActionListener(e -> {
                painel.setVisible(false);
                String nome = nomeTexto.getText();
                addVertice(nome);
                nomeTexto.setText("");
                JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!");
            });
            voltarBotao.addActionListener(e -> {
                vertice.setVisible(false);
                painel.setVisible(true);
                painelInicial();
            });
            adicionarAresta.addActionListener(e -> {
                painel.setVisible(false);
                String origem = origemT.getText();
                String destino = destinoT.getText();
                String nome = aNome.getText();
                int peso = Integer.valueOf(pesoT.getText());
                if (addAresta(origem, destino, nome, peso)) {
                    JOptionPane.showMessageDialog(null, "Operação concluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Vertice não cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                origemT.setText("");
                destinoT.setText("");
                aNome.setText("");
                pesoT.setText("");
            });
            grafoBotao.addActionListener(e -> {
                grafo.display();
            });
        }
    }
    public void painelGrafo() {
        JPanel grafo = new JPanel(new GridLayout(0, 1, 100, 20));
    }

    public void addVertice(String nome) {
        Node node = grafo.addNode(nome);
        node.setAttribute("ui.label", nome);
    }
    public boolean addAresta(String origem, String destino, String nome, int peso) {
        if(grafo.getNode(origem) != null && grafo.getNode(destino) != null) {
            Edge edge = grafo.addEdge(origem + "-" + destino, origem, destino, false);
            edge.setAttribute("weight", peso);
            edge.setAttribute("ui.label", nome + "(" + peso + ")");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.painelInicial();
    }


}
