package domain;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static guru.nidi.graphviz.model.Factory.*;

public class GerenciadorGrafo {
    private MutableGraph grafo;
    private Map<String, MutableNode> vertices;
    private JFrame frame;
    private PainelGrafo painelGrafo;
    private JPanel painelPrincipal;
    private JPanel painelVertice;
    private JPanel painelAresta;
    private JPanel painelContainer;
    private CardLayout layoutCards;

    public GerenciadorGrafo() {
        grafo = mutGraph("Grafo").setDirected(false);
        vertices = new HashMap<>();
        inicializarIU();
    }
    private void inicializarIU() {
        frame = new JFrame("Editor de Grafos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //encrerra o programa ao fechar a janela
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        painelGrafo = new PainelGrafo();
        frame.add(painelGrafo, BorderLayout.CENTER);

        // Container de painéis de controle (parte esquerda)
        layoutCards = new CardLayout();
        painelContainer = new JPanel(layoutCards);
        frame.add(painelContainer, BorderLayout.WEST);

        // Criar os painéis e adicioná-los ao container
        painelPrincipal = criarPaineis();
        painelContainer.add(painelPrincipal, "principal");
        painelContainer.add(painelVertice, "vertice");
        painelContainer.add(painelAresta, "aresta");

        frame.setVisible(true);
    }
    public JPanel criarPaineis() {
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setPreferredSize(new Dimension(250, 700));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelPrincipal.setBackground(new Color(245, 245, 250));

        JLabel principalTitulo = new JLabel("Painel Principal", SwingConstants.CENTER);
        principalTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        principalTitulo.setAlignmentX((Component.CENTER_ALIGNMENT));
        painelPrincipal.add(principalTitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        painelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Botões
        JButton verticeBotao = new JButton("Adicionar Vértice");
        JButton arestaBotao = new JButton("Adicionar Aresta");
        JButton limparBotao = new JButton("Limpar Grafo");
        verticeBotao.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticeBotao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        arestaBotao.setAlignmentX(Component.CENTER_ALIGNMENT);
        arestaBotao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        limparBotao.setAlignmentX(Component.CENTER_ALIGNMENT);
        limparBotao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        painelPrincipal.add(verticeBotao);
        painelPrincipal.add(arestaBotao);
        painelPrincipal.add(limparBotao);

        painelVertice = new JPanel();
        painelVertice.setLayout(new BoxLayout(painelVertice, BoxLayout.Y_AXIS));
        painelVertice.setPreferredSize(new Dimension(250, 700));
        painelVertice.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelVertice.setBackground(new Color(245, 245, 250));

        JLabel verticeTitulo = new JLabel("Painel Vertice", SwingConstants.CENTER);
        painelVertice.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticeTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        verticeTitulo.setAlignmentX((Component.CENTER_ALIGNMENT));
        painelVertice.add(verticeTitulo);
        painelVertice.add(Box.createRigidArea(new Dimension(0, 15)));
        JTextField campoVNome = new JTextField();
        campoVNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JButton addVerticeBotao = new JButton("Adicionar Vértice");
        addVerticeBotao.setAlignmentX(Component.CENTER_ALIGNMENT);
        addVerticeBotao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        painelVertice.add(new JLabel("Nome do Vértice: "));
        painelVertice.add(campoVNome);
        painelVertice.add(Box.createRigidArea(new Dimension(0, 10)));
        painelVertice.add(addVerticeBotao);
        painelVertice.add(Box.createRigidArea(new Dimension(0, 20)));
        JButton voltarBotaoVertice = new JButton("Voltar");
        voltarBotaoVertice.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltarBotaoVertice.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        painelVertice.add(voltarBotaoVertice);
        painelVertice.add(Box.createRigidArea(new Dimension(0, 20)));

        painelAresta = new JPanel();
        painelAresta.setLayout(new BoxLayout(painelAresta, BoxLayout.Y_AXIS));
        painelAresta.setPreferredSize(new Dimension(250, 700));
        painelAresta.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelAresta.setBackground(new Color(245, 245, 250));

        JLabel arestaTitulo = new JLabel("Painel Aresta", SwingConstants.CENTER);
        arestaTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        arestaTitulo.setAlignmentX((Component.CENTER_ALIGNMENT));
        painelAresta.add(arestaTitulo);
        painelAresta.add(Box.createRigidArea(new Dimension(0, 15)));
        painelAresta.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoANome = new JTextField();
        JTextField campoOrigem = new JTextField();
        JTextField campoDestino = new JTextField();
        JTextField campoPeso = new JTextField();

        JTextField[] camposAresta = {campoANome, campoOrigem, campoDestino, campoPeso};
        String[] labels = {"Nome da Aresta:","Origem:", "Destino:", "Peso:"};

        for(int i = 0; i < 4; i++) {
            painelAresta.add(new JLabel((labels[i])));
            painelAresta.add(camposAresta[i]);
            camposAresta[i].setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            painelAresta.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JButton addArestaBotao = new JButton("Adicionar Aresta");
        addArestaBotao.setAlignmentX(Component.CENTER_ALIGNMENT);
        addArestaBotao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        painelAresta.add(addArestaBotao);
        JButton voltarBotaoAresta = new JButton("Voltar");
        voltarBotaoAresta.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltarBotaoAresta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        painelAresta.add(voltarBotaoAresta);
        painelAresta.add(Box.createRigidArea(new Dimension(0, 10)));

        // Filtro numérico
        ((AbstractDocument) campoPeso.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) super.insertString(fb, offset, string, attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d*")) super.replace(fb, offset, length, text, attrs);
            }
        });
        painelAresta.setVisible(false);
        painelVertice.setVisible(false);


        //Ações
        arestaBotao.addActionListener(e -> layoutCards.show(painelContainer, "aresta"));
        verticeBotao.addActionListener(e -> layoutCards.show(painelContainer, "vertice"));

        verticeBotao.addActionListener(e -> {
            painelPrincipal.setVisible(false);
            painelVertice.setVisible(true);
        });

        addVerticeBotao.addActionListener(e -> {
            String nome = campoVNome.getText().trim();
            if(!nome.isEmpty()) {
                if(vertices.containsKey(nome)) {
                    JOptionPane.showMessageDialog(frame, "Vértice já existe!");
                } else {
                    MutableNode vertice = mutNode(nome);
                    vertices.put(nome, vertice);
                    grafo.add(vertice);
                    campoVNome.setText("");//Limpa o campo
                    painelGrafo.atualizarGrafo(grafo);
                }
            }
        });

        voltarBotaoVertice.addActionListener(e -> layoutCards.show(painelContainer, "principal"));

        voltarBotaoAresta.addActionListener(e -> layoutCards.show(painelContainer, "principal"));

        addArestaBotao.addActionListener(e -> {
            String orig = campoOrigem.getText().trim();
            String dest = campoDestino.getText().trim();
            String nome = campoANome.getText().trim();
            String pesoTexto = campoPeso.getText().trim();

            if(orig.isEmpty() || dest.isEmpty() || nome.isEmpty() || pesoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
                return;
            }
            if(!vertices.containsKey(orig) || !vertices.containsKey(dest)) {
                JOptionPane.showMessageDialog(frame, "Um ou mais vértices não exitem!");
                return;
            }

            int peso = Integer.parseInt(pesoTexto);
            MutableNode origem = vertices.get(orig);
            MutableNode destino = vertices.get(dest);

            origem.addLink(to(destino).with(guru.nidi.graphviz.attribute.Label.of(nome + " (" + peso + ")")));
            painelGrafo.atualizarGrafo(grafo);
            for (JTextField campo : camposAresta)
                campo.setText("");
        });

        limparBotao.addActionListener(e -> {
            grafo = mutGraph("Grafo").setDirected(false);
            vertices.clear();
            painelGrafo.atualizarGrafo(grafo);

        });

        return painelPrincipal;
    }
}
