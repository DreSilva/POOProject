import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

/**
 * Classe main, cria um novo centro chamado CISUC
 */
public class Main{
    protected NossaFrame frameCentros;
    protected CentroDeInvestigacao CISUC;
    public String DataHoje;

    public Main(){
        String DataHoje = JOptionPane.showInputDialog(null, "Insira a data de hoje", "Input", JOptionPane.QUESTION_MESSAGE);
        if (DataHoje.length()==0) {
            JOptionPane.showMessageDialog(null, "Tem que inserir uma data", "Inválido", JOptionPane.ERROR_MESSAGE);
        }
        else if (DataHoje==null){
            JOptionPane.showMessageDialog(null, "Tem que inserir uma data", "Inválido", JOptionPane.ERROR_MESSAGE);
        }
        else if (verificaData(DataHoje)==0) {
            JOptionPane.showMessageDialog(null, "Data Inválida!", "Inválido", JOptionPane.ERROR_MESSAGE);
        }
        else if (verificaData(DataHoje)==1) {
            CISUC = new CentroDeInvestigacao("CISUC");
            novoProjeto(CISUC);
            novaPessoa(CISUC);
            frameCentros = new NossaFrame(CISUC.getNome(),DataHoje);
            frameCentros.setTitle("Centro de Investigação");
            frameCentros.setSize(700, 150);
            frameCentros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameCentros.setVisible(true);
        }
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
                CISUC.ListarPessoas(frameCentros,DataHoje);
            }
            else if (cmd.equals("Listar Projetos")) {
                frameCentros.setVisible(false);
                System.out.println(DataHoje);
                CISUC.ListarProjetosDoCentro(frameCentros,DataHoje);
            }
            else if (cmd.equals("Adicionar Projeto")) {
                frameCentros.setVisible(false);
                System.out.println(DataHoje);
                CISUC.AdicionarProjeto(frameCentros,DataHoje);
            }
            else if (cmd.equals("Listar PNCNDE")) {
                frameCentros.setVisible(false);
                CISUC.ListarProjetosNConcluidosATempo(frameCentros,DataHoje);
            }
            else if (cmd.equals("Listar Projetos Concluidos")) {
                frameCentros.setVisible(false);
                CISUC.ListarProjetosConcluidos(frameCentros,DataHoje);
            }
            else if (cmd.equals("ALTERARDATAHOJE")) {
                frameCentros.setVisible(false);
                String DataHoje = JOptionPane.showInputDialog(null, "Introduza a data de hoje", "Input", JOptionPane.QUESTION_MESSAGE);
                if (DataHoje.length()==0) {
                    JOptionPane.showMessageDialog(null, "Tem que inserir uma data", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameCentros.setVisible(true);
                }
                else if (DataHoje==null){
                    JOptionPane.showMessageDialog(null, "Tem que inserir uma data", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameCentros.setVisible(true);
                }
                else if (verificaData(DataHoje)==0) {
                    JOptionPane.showMessageDialog(null, "Data Inválida!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameCentros.setVisible(true);
                }
                else if (verificaData(DataHoje)==1) {
                    frameCentros.DataDEHoje.setText(DataHoje);
                    frameCentros.setVisible(true);
                }
            }
        }
    }

    /**
     * Frame inicial que mostra o nome do centro, e os botões Listar Pessoas, Listar Projetos, Adicionar Projeto, Listar Projetos não concluidos na data estimada e Listar Projetos concluidos
     */
    public class NossaFrame extends JFrame{
        protected JLabel label, DataDEHoje;
        protected JPanel panelInicial;
        protected JButton listarPessoas,listarProjetos,adicionarProjeto,listarProjetosNaoConcluidosNaDataEstimada,listarProjetosConcluidos, alteraDataHoje;

        public NossaFrame(String nomeDoCentro,String DataHoje){
            super();

            this.panelInicial = new JPanel();
            panelInicial.setLayout(new GridLayout(4,2));

            listarPessoas = new JButton("Listar Pessoas");
            listarProjetos = new JButton("Listar Projetos");
            adicionarProjeto = new JButton("Adicionar Projeto");
            listarProjetosNaoConcluidosNaDataEstimada = new JButton("Listar Projetos não concluidos na data estimada");
            listarProjetosConcluidos = new JButton("Listar Projetos concluidos");
            alteraDataHoje = new JButton("Alterar data de hoje");
            DataDEHoje = new JLabel(DataHoje);
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

            alteraDataHoje.addActionListener(new ButtonListener());
            alteraDataHoje.setActionCommand("ALTERARDATAHOJE");

            panelInicial.add(label);
            panelInicial.add(listarPessoas);
            panelInicial.add(listarProjetos);
            panelInicial.add(adicionarProjeto);
            panelInicial.add(listarProjetosNaoConcluidosNaDataEstimada);
            panelInicial.add(listarProjetosConcluidos);
            panelInicial.add(alteraDataHoje);
            panelInicial.add(DataDEHoje);

            this.add(panelInicial);
        }
    }
    public void novoProjeto(CentroDeInvestigacao CISUC) {
        Projeto p1 = new Projeto("Projeto 1", "P1", 28, 11, 2019, 1);
        Projeto p2 = new Projeto("Projeto 2", "P2", 25, 11, 2019, 2);
        Projeto p3 = new Projeto("Projeto 3", "P3", 23, 11, 2019, 3);
        Projeto p4 = new Projeto("Projeto 4", "P4", 23, 11, 2019, 3);
        Projeto p5 = new Projeto("Projeto 5", "P5", 23, 11, 2019, 3);
        Projeto p6 = new Projeto("Projeto 6", "P6", 23, 11, 2019, 3);
        Projeto p7 = new Projeto("Projeto 7", "P7", 23, 11, 2019, 3);
        Projeto p8 = new Projeto("Projeto 8", "P8", 23, 11, 2019, 3);
        Projeto p9 = new Projeto("Projeto 9", "P9", 23, 11, 2019, 3);
        Projeto p10 = new Projeto("Projeto 10", "P10", 23, 11, 2019, 3);

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
        Docentes pe1 = new Docentes("tomas","blabla.mail.com",123,"Paneleirisses");
        Licenciado pe2 = new Licenciado("Toni", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Docentes pe3 = new Docentes("Zeca", "blabla.mail.com", 1,"gaydamerda");
        Mestre pe4 = new Mestre("João Pedro", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Doutorado pe5 = new Doutorado("Zé António", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Licenciado pe6 = new Licenciado("Zé Pedro", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Doutorado pe7 = new Doutorado("João Miguel", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Mestre pe8 = new Mestre("João António", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Doutorado pe9 = new Doutorado("João Zé", "blabla.mail.com", 1,1, 2020, 1,1,2021);
        Mestre pe10 = new Mestre("Zé João", "blabla.mail.com", 1,1, 2020, 1,1,2021);

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
    public int verificaData(String DataHoje) {
        String[] data;
        int testdia, testmes, testano;
        int counter=0;

        for (int i=0;i<DataHoje.length();i++){
            Character c1 = DataHoje.charAt(i);
            Character c2 = '/';
            if (c1.equals(c2)){
                counter++;
            }
        }
        if (counter!=2)
            return 0;

        data = DataHoje.split("/");
        testdia = Integer.parseInt(data[0]);
        testmes = Integer.parseInt(data[1]);
        testano = Integer.parseInt(data[2]);

        if (testano<1)
            return 0;
        if (testmes>12 || testmes<1)
            return 0;
        else if ((testmes==1||testmes==3||testmes==5||testmes==7||testmes==8||testmes==10||testmes==12) && (testdia>31 || testdia<1))
            return 0;
        else if ((testmes==4||testmes==6||testmes==9||testmes==11) && (testdia>30 || testdia<1))
            return 0;
        else if ((testmes==2) && (testdia>28 || testdia<1))
            return 0;

        return 1;
    }

    CentroDeInvestigacao loadCentro(String ficheiroObj,String ficheiroTxt){
        File f = new File("ficheiro.obj");
        CentroDeInvestigacao Centro=null;
        if(f.exists() && f.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Centro = (CentroDeInvestigacao) ois.readObject();
                ois.close();
                return Centro;
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro a converter objeto.");
            }
        }
        else {
            f = new File("ficheiro.txt");
            String[] in;
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line = br.readLine()) != null) {
                    in = line.split("/");
                    if(in[1].equals("Tarefa")){

                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        }
        return Centro;
    }
}
