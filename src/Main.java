import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

/**
 * Classe main, cria um novo centro chamado CISUC
 */
public class Main implements Serializable{
    protected NossaFrame frameCentros;
    protected CentroDeInvestigacao CISUC;
    public String DataHoje;

    public Main(){
        DataHoje = JOptionPane.showInputDialog(null, "Insira a data de hoje", "Input", JOptionPane.QUESTION_MESSAGE);
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
            CISUC = loadCentro("ficheiro.obj","ficheiro.txt");
            frameCentros = new NossaFrame(CISUC.getNome(),DataHoje);
            frameCentros.setTitle("Centro de Investigação");
            frameCentros.setSize(700, 150);
            frameCentros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameCentros.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    escreveFich(CISUC);
                }
            });
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

    public int verificaData(String DataHoje) {
        String[] data,dataMesmoHoje;
        int testdia, testmes, testano, testMesmodia, testMesmomes, testMesmoano;
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

        dataMesmoHoje = DataHoje.split("/");
        testMesmodia = Integer.parseInt(dataMesmoHoje[0]);
        testMesmomes = Integer.parseInt(dataMesmoHoje[1]);
        testMesmoano = Integer.parseInt(dataMesmoHoje[2]);

        if (testMesmoano>testano)
            return 2;
        if ((testano==testMesmoano) && (testMesmomes>testmes))
            return 2;
        if ((testano==testMesmoano) && (testMesmomes==testmes) && (testMesmodia>testdia))
            return 2;

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
            String[] dataInicio,dataFim;
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                Centro = new CentroDeInvestigacao("CISUC");
                while((line = br.readLine()) != null) {
                    in = line.split(",");
                    if(in[0].equalsIgnoreCase("Docente")){
                        Docentes docente=new Docentes(in[2],in[3],Integer.parseInt(in[1]),in[4]);
                        Centro.pessoas.add(docente);
                    }
                    else if(in[0].equals("Mestre")){
                        dataInicio=in[4].split("/");
                        dataFim=in[5].split("/");
                        Mestre mestre=new Mestre(in[2],in[3],Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Integer.parseInt(dataFim[0]),Integer.parseInt(dataFim[1]),Integer.parseInt(dataFim[2]));
                        Centro.pessoas.add(mestre);
                    }
                    else if(in[0].equals("Licenciado")){
                        dataInicio=in[4].split("/");
                        dataFim=in[5].split("/");
                        Licenciado licenciado=new Licenciado(in[2],in[3],Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Integer.parseInt(dataFim[0]),Integer.parseInt(dataFim[1]),Integer.parseInt(dataFim[2]));
                        Centro.pessoas.add(licenciado);
                    }
                    else if(in[0].equals("Doutorado")){
                        dataInicio=in[4].split("/");
                        dataFim=in[5].split("/");
                        Doutorado doutorado=new Doutorado(in[2],in[3],Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Integer.parseInt(dataFim[0]),Integer.parseInt(dataFim[1]),Integer.parseInt(dataFim[2]));
                        Centro.pessoas.add(doutorado);
                    }
                    else if(in[0].equals("Projeto")){
                        dataInicio=in[4].split("/");
                        dataFim=in[5].split("/");
                        Projeto projeto=new Projeto(in[1],in[2],Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Integer.parseInt(in[3]),Integer.parseInt(dataFim[0]),Integer.parseInt(dataFim[1]),Integer.parseInt(dataFim[2]));
                        Centro.projetos.add(projeto);
                        for (Pessoa p : CentroDeInvestigacao.pessoas) {
                            if (p.nome.equals(in[6])){
                                projeto.investigadorPrincipal=p;
                            }
                        }
                    }
                    else if(in[0].equals("Documentacao")){
                        dataInicio=in[1].split("/");
                        dataFim=in[2].split("/");
                        Documentacao tarefa=new Documentacao(Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Double.parseDouble(in[3]),0);
                        for (Projeto i: Centro.projetos){
                            if(i.nome.equals(in[4])){
                                i.tarefas.add(tarefa);
                            }
                        }
                    }
                    else if(in[0].equals("Design")){
                        dataInicio=in[1].split("/");
                        dataFim=in[2].split("/");
                        Design tarefa=new Design(Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Double.parseDouble(in[3]),0);
                        for (Projeto i: Centro.projetos){
                            if(i.nome.equals(in[4])){
                                i.tarefas.add(tarefa);
                            }
                        }
                    }
                    else if(in[0].equals("Desenvolvimento")){
                        dataInicio=in[1].split("/");
                        dataFim=in[2].split("/");
                        Desenvolvimento tarefa=new Desenvolvimento(Integer.parseInt(dataInicio[0]),Integer.parseInt(dataInicio[1]),Integer.parseInt(dataInicio[2]),Double.parseDouble(in[3]),0);
                        for (Projeto i: Centro.projetos){
                            if(i.nome.equals(in[4])){
                                i.tarefas.add(tarefa);
                            }
                        }
                    }
                }
                br.close();
            }catch (FileNotFoundException ex1) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex1) {
                System.out.println("Erro a ler ficheiro de texto.");
            }

        }
        return Centro;
    }

    public void escreveFich(CentroDeInvestigacao Centro){
        File f = new File("ficheiro.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Centro);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }
}
