import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main{
    protected NossaFrame frameCentros;
    protected CentroDeInvestigacao CISUC;

    public Main(){
        CISUC = new CentroDeInvestigacao("CISUC");
        novoProjeto(CISUC);
        novaPessoa(CISUC);
        frameCentros = new NossaFrame(CISUC.getNome());
        frameCentros.setTitle("Centro de Investigação");
        frameCentros.setSize(700,150);
        frameCentros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCentros.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if (cmd.equals("Listar Pessoas")) {
                frameCentros.setVisible(false);
                CISUC.ListarPessoas(frameCentros);
                //System.out.println("a");
            }
            else if (cmd.equals("Listar Projetos")) {
                frameCentros.setVisible(false);
                CISUC.ListarProjetos(frameCentros);
                //CISUC.ListarPessoasDoProjeto(frameCentros);
                //frameCentros.setVisible(true);
            }
            else if (cmd.equals("Adicionar Projeto")) {
                frameCentros.setVisible(false);
                CISUC.AdicionarProjeto(frameCentros);
                //System.out.println("c");
            }
            else if (cmd.equals("Listar PNCNDE")) {
                frameCentros.setVisible(false);
                CISUC.ListarProjetosNConcluidosATempo(frameCentros);
                //System.out.println("d");
            }
            else if (cmd.equals("Listar Projetos Concluidos")) {
                frameCentros.setVisible(false);
                CISUC.ListarProjetosConcluidos(frameCentros);
                //System.out.println("e");
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
    public void novoProjeto(CentroDeInvestigacao CISUC) {
        Projeto p1 = new Projeto("Projeto POO", "PPOO", 28, 11, 2019, 1);
        Projeto p2 = new Projeto("Projeto IRC", "PIRC", 25, 11, 2019, 2);
        Projeto p3 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p4 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p5 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p6 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p7 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p8 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p9 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);
        Projeto p10 = new Projeto("Projeto TI", "PTI", 23, 11, 2019, 3);

        CISUC.projetos.add(p1);
        CISUC.projetos.add(p2);
        CISUC.projetos.add(p3);
        CISUC.projetos.add(p4);
        CISUC.projetos.add(p5);
        CISUC.projetos.add(p6);
        CISUC.projetos.add(p7);
        CISUC.projetos.add(p8);
        CISUC.projetos.add(p9);
        CISUC.projetos.add(p10);
    }

    public void novaPessoa(CentroDeInvestigacao CISUC){
        Pessoa pe1 = new Pessoa("André Silva", "blabla.mail.com");
        Pessoa pe2 = new Pessoa("Tomás Ventura", "blabla.mail.com");
        Pessoa pe3 = new Pessoa("Zé Miguel", "blabla.mail.com");
        Pessoa pe4 = new Pessoa("João Pedro", "blabla.mail.com");
        Pessoa pe5 = new Pessoa("Zé António", "blabla.mail.com");
        Pessoa pe6 = new Pessoa("Zé Pedro", "blabla.mail.com");
        Pessoa pe7 = new Pessoa("João Miguel", "blabla.mail.com");
        Pessoa pe8 = new Pessoa("João António", "blabla.mail.com");
        Pessoa pe9 = new Pessoa("João Zé", "blabla.mail.com");
        Pessoa pe10 = new Pessoa("Zé João", "blabla.mail.com");

        CISUC.pessoas.add(pe1);
        CISUC.pessoas.add(pe2);
        CISUC.pessoas.add(pe3);
        CISUC.pessoas.add(pe4);
        CISUC.pessoas.add(pe5);
        CISUC.pessoas.add(pe6);
        CISUC.pessoas.add(pe7);
        CISUC.pessoas.add(pe8);
        CISUC.pessoas.add(pe9);
        CISUC.pessoas.add(pe10);
    }
}
