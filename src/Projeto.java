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
    protected JFrame frameDisplay,frameProjetos;
    protected static ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    public void DisplayProjeto(JFrame frame){
        this.frameProjetos = frame;
        new DarDisplay();
    }

    public class DarDisplay extends JFrame {
        protected JLabel labelNome,labelAcro,labelDataIn,labelDuracao;
        protected JPanel panel;
        protected JButton ok, voltar,listarTarefas,novaTarefa,listarTNInici,listarTNConc,listarTarefasConc,custoProjeto,novaPessoa,terminaProjeto;

        public DarDisplay() {
            super();

            frameDisplay = new JFrame();
            frameDisplay.setTitle("Informação do projeto");
            frameDisplay.setSize(500, 500);
            frameDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            labelNome = new JLabel(nome);
            labelAcro = new JLabel(acronimo);
            //labelDataIn = new JLabel((Icon) dataInicio);
            //labelDuracao = new JLabel(String.valueOf(duracao));

            ok = new JButton("OK");
            ok.setActionCommand("OK");
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            listarTarefas = new JButton("Listar Tarefas");
            listarTarefas.setActionCommand("LISTARTAREFAS");
            novaTarefa = new JButton("Criar nova Tarefa");
            listarTNInici = new JButton("Listar Tarefas não iniciadas");
            listarTNConc = new JButton("Listar Tarefas não concluidas");
            listarTarefasConc = new JButton("Listar Tarefas concluidas");
            novaPessoa = new JButton("Acrescentar pessoa ao Projeto");
            custoProjeto = new JButton("Calcular custo do Projeto");
            terminaProjeto = new JButton("Terminar Projeto");

            panel = new JPanel();
            panel.add(labelNome);
            panel.add(labelAcro);
            //panel.add(labelDataIn);
            //panel.add(labelDuracao);
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
                ***.ListarTarefas(frameDisplay);
            }
            else if (cmd.equals("OK")) {

            }
            else if (cmd.equals("VOLTAR")){
                frameDisplay.dispose();
                frameProjetos.setVisible(true);
            }
        }
    }

    /*public static void novaPessoa(String nome, String email){
        Pessoa pe1 = new Pessoa("Tomás Ventura", "ventura.tfp@gmail.com");
        Pessoa pe2 = new Pessoa("André Silva", "2000.andre.silva@gmail.com");
    }

    public static void novaTarefa(int diaIn, int mesIn, int anoIn, int duracao, int percentagemConc ){
        Tarefa t1 = new Tarefa(2,12,2019, 3,0);
    }*/

    public Projeto(String nome, String acronimo, int diaIn, int mesIn, int anoIn, int duracao) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.dataDeFim = new Data(0,0,0);

    }
}
