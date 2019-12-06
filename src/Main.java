import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
    protected NossaFrame frameCentros;
    CentroDeInvestigacao CISUC;
    public Main(String nome){
        frameCentros = new NossaFrame(nome);
        frameCentros.setTitle("Centro de Investigação");
        frameCentros.setSize(700,150);
        frameCentros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCentros.setVisible(true);
        CISUC = new CentroDeInvestigacao("CISUC");
    }

    public static void main(String[] args) {
        new Main("CISUC");
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if (cmd.equals("Listar Pessoas")) {
                frameCentros.setVisible(false);
                System.out.println("a");
            }
            else if (cmd.equals("Listar Projetos")) {
                frameCentros.setVisible(false);
                CISUC.ListarPessoasDoProjeto(frameCentros);
                //frameCentros.setVisible(true);
            }
            else if (cmd.equals("Adicionar Projeto")) {
                frameCentros.setVisible(false);
                System.out.println("c");
            }
            else if (cmd.equals("Listar PNCNDE")) {
                frameCentros.setVisible(false);
                System.out.println("d");
            }
            else if (cmd.equals("Listar Projetos Concluidos")) {
                frameCentros.setVisible(false);
                System.out.println("e");
            }
        }
    }
    public class NossaFrame extends JFrame{
        protected JLabel label;
        protected JPanel panelInicial;
        protected JButton listarPessoas,listarProjetos,adicionarProjeto,listarProjetosNaoConcluidosNaDataEstimada,listarProjetosConcluidos;

        public NossaFrame(String nomeDoCentro){
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
    }
}
