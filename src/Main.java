import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JLabel label;
    private JPanel panelInicial;
    private JButton listarPessoas,listarProjetos,adicionarProjeto,listarProjetosNaoConcluidosNaDataEstimada,listarProjetosConcluidos;

    public Main(String nomeDoCentro){
        super();

        this.panelInicial = new JPanel();
        panelInicial.setLayout(new GridLayout(3,2));

        listarPessoas = new JButton("Listar Pessoas");
        listarProjetos = new JButton("Listar Projetos");
        adicionarProjeto = new JButton("Adicionar Projeto");
        listarProjetosNaoConcluidosNaDataEstimada = new JButton("Listar Projetos não concluidos na data estimada");
        listarProjetosConcluidos = new JButton("Listar Projetos concluidos");
        label = new JLabel(nomeDoCentro);

        listarPessoas.addActionListener(new ButtonListener());
        listarPessoas.setActionCommand("Listar Pessoas");

        listarProjetos.addActionListener(new ButtonListener());
        listarProjetos.setActionCommand("Listar Projetos");

        adicionarProjeto.addActionListener(new ButtonListener());
        adicionarProjeto.setActionCommand("Adicionar Projeto");

        listarProjetosNaoConcluidosNaDataEstimada.addActionListener(new ButtonListener());;
        listarProjetosNaoConcluidosNaDataEstimada.setActionCommand("Listar PNCNDE");

        listarProjetosConcluidos.addActionListener(new ButtonListener());
        listarProjetosConcluidos.setActionCommand("Listar Projetos Concluidos");

        panelInicial.add(label);
        panelInicial.add(listarPessoas);
        panelInicial.add(listarProjetos);
        panelInicial.add(adicionarProjeto);
        panelInicial.add(listarProjetosNaoConcluidosNaDataEstimada);
        panelInicial.add(listarProjetosConcluidos);

        this.add(panelInicial);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if (cmd.equals("Listar Pessoas"))
                System.out.println("a");
            else if (cmd.equals("Listar Projetos"))
                System.out.println("b");
            else if (cmd.equals("Adicionar Projeto"))
                System.out.println("c");
            else if (cmd.equals("Listar PNCNDE"))
                System.out.println("d");
            else if (cmd.equals("Listar Projetos Concluidos"))
                System.out.println("e");
        }
    }

    public static void main(String[] args) {
        CentroDeInvestigacao CISUC = new CentroDeInvestigacao("CISUC");
        Main frameCentros = new Main(CISUC.getNome());
        frameCentros.setTitle("Centro de Investigação");
        frameCentros.setSize(700,150);
        frameCentros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCentros.setVisible(true);
    }
}

