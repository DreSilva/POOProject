import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JPanel panelInicial;
    private JFrame frameCentros;

    public Main(String nomeDoCentro){
        super();
        this.panelInicial = new JPanel();
        panelInicial.setLayout(new GridLayout(3,2));
        JButton listarPessoas = new JButton("Listar Pessoas");
        JButton listarProjetos = new JButton("Listar Projetos");
        JButton adicionarProjeto = new JButton("Adicionar Projeto");
        JButton listarProjetosNaoConcluidosNaDataEstimada = new JButton("Listar Projetos não concluidos na data estimada");
        JButton listarProjetosConcluidos = new JButton("Listar Projetos concluidos");
        JLabel label = new JLabel(nomeDoCentro);

        panelInicial.add(label);
        panelInicial.add(listarPessoas);
        panelInicial.add(listarProjetos);
        panelInicial.add(adicionarProjeto);
        panelInicial.add(listarProjetosNaoConcluidosNaDataEstimada);
        panelInicial.add(listarProjetosConcluidos);
        this.add(panelInicial);

    }
    public void main(String[] args) {
        CentroDeInvestigacao CISUC = new CentroDeInvestigacao("CISUC");
        this.frameCentros = new JFrame();
        this.frameCentros.setTitle("Centro de Investigação");
        this.frameCentros.setSize(400,150);
        this.frameCentros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameCentros.setVisible(true);
    }
}

