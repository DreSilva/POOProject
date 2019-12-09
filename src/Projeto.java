import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;

public class Projeto {
    protected String nome, acronimo;
    protected Data dataInicio, dataDeFim;
    protected int duracao;
    protected JFrame frameDisplay,frameProjetos,frameProjetosConc;
    protected static ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    public void DisplayProjeto(JFrame frame){
        this.frameProjetos = frame;
        if (dataDeFim.dia==0)
            new DarDisplayProj();
        else
            new DarDisplayProjConc();
            System.out.println("gay");
    }

    public class DarDisplayProj extends JFrame {
        protected JLabel labelNome,labelAcro,labelDataIn,labelDuracao;
        protected JPanel panel;
        protected JButton ok, voltar,listarTarefas,novaTarefa,listarTNInici,listarTNConc,listarTarefasConc,custoProjeto,novaPessoa,terminaProjeto;

        public DarDisplayProj() {
            super();

            frameDisplay = new JFrame();
            frameDisplay.setTitle("Informação do projeto");
            frameDisplay.setSize(500, 500);

            panel = new JPanel();
            panel.setLayout(new GridLayout(8,2));

            frameDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            labelNome = new JLabel(nome);
            labelAcro = new JLabel(acronimo);
            labelDataIn = new JLabel(String.valueOf(dataInicio));
            labelDuracao = new JLabel(String.valueOf(duracao));

            ok = new JButton("OK");
            ok.setActionCommand("OK");
            ok.addActionListener(new ProjetoListener());
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ProjetoListener());

            listarTarefas = new JButton("Listar Tarefas");
            listarTarefas.setActionCommand("LISTARTAREFAS");
            novaTarefa = new JButton("Criar nova Tarefa");
            listarTNInici = new JButton("Listar Tarefas não iniciadas");
            listarTNConc = new JButton("Listar Tarefas não concluidas");
            listarTarefasConc = new JButton("Listar Tarefas concluidas");
            novaPessoa = new JButton("Acrescentar pessoa ao Projeto");
            custoProjeto = new JButton("Calcular custo do Projeto");
            terminaProjeto = new JButton("Terminar Projeto");

            panel.add(labelNome);
            panel.add(labelAcro);
            panel.add(labelDataIn);
            panel.add(labelDuracao);
            panel.add(listarTarefas);
            panel.add(novaTarefa);
            panel.add(listarTNInici);
            panel.add(listarTNConc);
            panel.add(listarTarefasConc);
            panel.add(novaPessoa);
            panel.add(custoProjeto);
            panel.add(terminaProjeto);
            panel.add(ok);
            panel.add(voltar);

            frameDisplay.add(panel);
            frameDisplay.setVisible(true);
        }
    }
    private class ProjetoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if (cmd.equals("LISTARTAREFAS")){
                ListarTarefas(frameDisplay);
            }
            else if (cmd.equals("OK")) {

            }
            else if (cmd.equals("VOLTAR")){
                frameDisplay.dispose();
                frameProjetos.setVisible(true);
            }
        }
    }

    public class DarDisplayProjConc extends JFrame{
        protected JLabel labelNome,labelAcro,labelDataIn,labelDuracao,labelDataFim;
        protected JPanel panel;
        protected JButton voltar,listarTarefas;

        public DarDisplayProjConc(){
            super();

            frameProjetosConc = new JFrame();
            frameProjetosConc.setTitle("Informação de Projeto ( CONCLUIDO )");
            frameProjetosConc.setSize(400, 300);
            frameProjetosConc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(new GridLayout(8,2));

            labelNome = new JLabel(nome);
            labelAcro = new JLabel(acronimo);
            labelDataIn = new JLabel(String.valueOf(dataInicio));
            labelDuracao = new JLabel(String.valueOf(duracao));
            labelDataFim = new JLabel(String.valueOf(dataDeFim));

            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ProjetoConcListener());

            listarTarefas = new JButton("Listar Tarefas (Concluidas)");
            listarTarefas.setActionCommand("LISTARTAREFAS");
            listarTarefas.addActionListener(new ProjetoConcListener());
        }
    }
    private class ProjetoConcListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("VOLTAR")){
                frameProjetosConc.dispose();
                frameProjetos.setVisible(true);
            }else if (cmd.equals("LISTARTAREFAS")){
                ListarTarefas(frameProjetosConc);
            }
        }
    }


    /*public static void novaTarefa(int diaIn, int mesIn, int anoIn, int duracao, int percentagemConc ){
        Tarefa t1 = new Tarefa(2,12,2019, 3,0);
    }*/

    public Projeto(String nome, String acronimo, int diaIn, int mesIn, int anoIn, int duracao) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.dataDeFim = new Data(0,0,0);
    }

    public void stringsLabels(){
        String nomeS="Nome:";
        String acroS="Acrónimo";
        String dataInS="Data de Inicio:";
        String duracaoS="Duração:";
        String dataFimS="Data de Fim:";
    }

    public void ListarTarefas(JFrame frame){
        this.frameDisplay=frame;
        new ListaTarefas();
    }

    public class ListaTarefas extends JFrame{
        public ListaTarefas(){
            System.out.println("gay");
        }
    }


}
