import com.sun.tools.javac.Main;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentroDeInvestigacao {
    protected JList listaProjetos, listaPessoas;
    protected String nome;
    protected static ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    protected static ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

    protected JFrame frameProjetos,frameProjetosConc,frameOriginal,framePessoas,frameAdd;
    protected JList listaSelecionados;

    public CentroDeInvestigacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void ListarProjetos(JFrame frame) {
        this.frameOriginal = frame;
        new ListaProjetos(this.projetos);
    }

    public void AdicionarProjeto(JFrame frame){
        this.frameOriginal = frame;
        new AdicionaProjeto();
    }

    public void ListarPessoas(JFrame frame){
        this.frameOriginal = frame;
        new ListaPessoas(this.pessoas);
    }

    public void ListarProjetosNConcluidosATempo(JFrame frame) {
        ArrayList<Projeto> projetosNConcATempo = new ArrayList<Projeto>();
        this.frameOriginal=frame;
        for (Projeto i: projetos) {
            if (i.dataInicio.getDia() !=0){
                int diaEst=0,mesEst=0,anoEst=0;
                if (i.dataInicio.getMês() + i.duracao<=12){
                    diaEst=i.dataInicio.getDia();
                    mesEst=i.dataInicio.getMês() + i.duracao;
                    anoEst=i.dataInicio.getAno();
                }
                else{
                    diaEst=i.dataInicio.getDia();
                    mesEst=(i.dataInicio.getMês()+i.duracao)%12;
                    anoEst=((i.dataInicio.getMês()+i.duracao)/12)+i.dataInicio.getAno();
                }
                if (i.dataDeFim.getAno() > anoEst || (i.dataDeFim.getAno()==anoEst && i.dataDeFim.getMês()>mesEst)) {
                    projetosNConcATempo.add(i);
                }
            }
        }
        new ListaProjetos(projetosNConcATempo);
    }

    public void ListarProjetosConcluidos(JFrame frame) {
        ArrayList<Projeto> projetosConc = new ArrayList<Projeto>();
        this.frameOriginal=frame;
        for (Projeto i: projetos){
            if (i.dataDeFim.getDia() !=0)
                projetosConc.add(i);
        }
        new ListaProjetos(projetosConc);
    }

    /*public void ListarPessoasDoProjeto(JFrame frame) {
        this.frameOriginal = frame;
        novoProjeto(this.projetos);
        new ListaProjetos(this.projetos);
    }*/

    public class ListaProjetos extends JFrame {
        protected JLabel label;
        protected JPanel panel;
        protected JButton ok, voltar;
        protected JScrollPane listScroller;
        protected JFrame frameOriginal;

        public ListaProjetos(ArrayList<Projeto> Projetos) {
            super();

            frameProjetos = new JFrame();
            frameProjetos.setTitle("JList");
            frameProjetos.setSize(400, 300);
            frameProjetos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            label = new JLabel("Lista de Projetos (Selecione apenas 1):");
            label.setBounds(50, 10, 300, 25);

            DefaultListModel listProjects = new DefaultListModel();
            for (Projeto i : Projetos)
                listProjects.addElement(i.nome);

            listaSelecionados = new JList(listProjects);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);

            ok = new JButton("OK");
            ok.setActionCommand("OK");
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            ok.addActionListener(new OkVoltarListener());
            voltar.addActionListener(new OkVoltarListener());
            ok.setBounds(50, 200, 130, 25);
            voltar.setBounds(220, 200, 130, 25);

            panel = new JPanel();
            panel.setLayout(null);
            panel.add(label);
            panel.add(listScroller);
            panel.add(ok);
            panel.add(voltar);

            frameProjetos.add(panel);
            frameProjetos.setVisible(true);
        }
    }
    private class OkVoltarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String valoresDaLista;
            String cmd = e.getActionCommand();
            if (cmd.equals("OK")) {
                frameProjetos.setVisible(false);
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                System.out.println(valoresDaLista);
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar um projeto!", "WARNING", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(true);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    Projeto projetoDesejado = procuraProjetoNoCentro(valoresDaLista,projetos);
                    if(projetoDesejado==null) {
                        System.out.println(projetoDesejado.nome);
                        //DisplayProjeto();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar um projeto!", "WARNING", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(true);
                }
                //DisplayProjeto();
            } else if (cmd.equals("VOLTAR")){
                frameProjetos.dispose();
                frameOriginal.setVisible(true);
            }
        }

        int quantosSelecionados(String valoresDaLista) {
            int contador = 1;
            char caracter;
            for (int i = 0; i < valoresDaLista.length(); i++) {
                caracter = valoresDaLista.charAt(i);
                if (caracter == ';')
                    contador++;
            }
            return contador;
        }

        Projeto procuraProjetoNoCentro(String nome,ArrayList<Projeto> projetos){
            Projeto alvo=null;
            for(Projeto i:projetos){
                if(nome.equals(i.nome)){
                    alvo=i;
                }
            }
            return alvo;
        }
    }


    public class ListaPessoas extends JFrame{
        protected JLabel label;
        protected JPanel panel;
        protected JButton ok, voltar;
        protected JScrollPane listScroller;
        protected JFrame frameOriginal;

        public ListaPessoas(ArrayList<Pessoa> Pessoas) {
            super();

            framePessoas = new JFrame();
            framePessoas.setTitle("JList");
            framePessoas.setSize(400, 300);
            framePessoas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            label = new JLabel("Lista de Pessoas (Selecione apenas 1):");
            label.setBounds(50, 10, 300, 25);

            DefaultListModel listPeople = new DefaultListModel();
            for (Pessoa i : Pessoas)
                listPeople.addElement(i.nome);

            listaSelecionados = new JList(listPeople);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);

            ok = new JButton("OK");
            ok.setActionCommand("OK");
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            ok.addActionListener(new OkVoltarListener2());
            voltar.addActionListener(new OkVoltarListener2());
            ok.setBounds(50, 200, 130, 25);
            voltar.setBounds(220, 200, 130, 25);

            panel = new JPanel();
            panel.setLayout(null);
            panel.add(label);
            panel.add(listScroller);
            panel.add(ok);
            panel.add(voltar);

            framePessoas.add(panel);
            framePessoas.setVisible(true);
        }
    }
    private class OkVoltarListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String valoresDaLista;
            String cmd = e.getActionCommand();
            if (cmd.equals("OK")) {
                framePessoas.setVisible(false);
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                System.out.println(valoresDaLista);
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma pessoa!", "WARNING", JOptionPane.ERROR_MESSAGE);
                    framePessoas.setVisible(true);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    Pessoa pessoaDesejada = procuraPessoaNoCentro(valoresDaLista,pessoas);
                    if(pessoaDesejada==null) {
                        System.out.println(pessoaDesejada.nome);
                        //DisplayProjeto();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar uma pessoa!", "WARNING", JOptionPane.ERROR_MESSAGE);
                    framePessoas.setVisible(true);
                }
                //DisplayProjeto();
            } else if (cmd.equals("VOLTAR")){
                framePessoas.dispose();
                frameOriginal.setVisible(true);
            }
        }

        int quantosSelecionados(String valoresDaLista) {
            int contador = 1;
            char caracter;
            for (int i = 0; i < valoresDaLista.length(); i++) {
                caracter = valoresDaLista.charAt(i);
                if (caracter == ';')
                    contador++;
            }
            return contador;
        }

        Pessoa procuraPessoaNoCentro(String nome,ArrayList<Pessoa> pessoas){
            Pessoa alvo=null;
            for(Pessoa i:pessoas){
                if(nome.equals(i.nome)){
                    alvo=i;
                }
            }
            return alvo;
        }
    }

    public class AdicionaProjeto extends JFrame{
        protected JPanel panel;
        protected JLabel labelNome, labelAcro, labelDataIn, labelDuracao;
        protected JTextField fieldNome, fieldAcro, fieldDataIn, fieldDuracao;
        protected JButton ok, voltar;

        public AdicionaProjeto(){
            super();

            frameAdd = new JFrame();
            frameAdd.setTitle("Adicionar Projeto");
            frameAdd.setSize(500, 300);
            frameAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(new GridLayout(5,1));

            labelNome = new JLabel("Nome");
            labelAcro = new JLabel("Acrónimo");
            labelDataIn = new JLabel("Data de Inicio (dd mm aaaa)");
            labelDuracao = new JLabel("Duração Estimada (em meses)");

            fieldNome = new JTextField(20);
            fieldAcro = new JTextField(5);
            fieldDataIn = new JTextField(10);
            fieldDuracao = new JTextField(2);

            ok = new JButton("OK");
            ok.setActionCommand("OK");
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            ok.addActionListener(new AdicionaProjListener());
            voltar.addActionListener(new AdicionaProjListener());

            panel.add(labelNome);
            panel.add(fieldNome);
            panel.add(labelAcro);
            panel.add(fieldAcro);
            panel.add(labelDataIn);
            panel.add(fieldDataIn);
            panel.add(labelDuracao);
            panel.add(fieldDuracao);
            panel.add(ok);
            panel.add(voltar);

            frameAdd.add(panel);
            frameAdd.setVisible(true);
        }
    }
    private class AdicionaProjListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("OK")) {
                frameAdd.dispose();
                frameOriginal.setVisible(true);

            } else if (cmd.equals("VOLTAR")) {
                frameAdd.dispose();
                frameOriginal.setVisible(true);
            }
        }
    }
}

