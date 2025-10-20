package domain;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelGrafo extends JPanel {
    private BufferedImage imagem;
    private MutableGraph grafoAtual;

    public void atualizarGrafo(MutableGraph grafo) {
        this.grafoAtual = grafo;
        redesenharGrafo();
    }
    private void redesenharGrafo() {
        if(grafoAtual == null) return;
        int largura = getWidth() > 0 ? getWidth() : 800;
        int altura = getHeight() > 0 ? getHeight() : 600;
        imagem = Graphviz.fromGraph(grafoAtual)
                .width(largura)
                .height(altura)
                .render(Format.PNG)
                .toImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(imagem != null)
            g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        redesenharGrafo(); //refaz o grafo ao redimensionar
    }
}