import com.sun.tools.javac.Main;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentroDeInvestigacao {
    protected JList listaProjetos;
    protected String nome;
    protected static ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    protected ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

    protected JFrame frameProjetos, frameOriginal;
    protected JList listaSelecionados;

    public CentroDeInvestigacao(String nome) {
        this.nome = nome;
    }

    public void novoProjeto(ArrayList<Projeto> projetos) {
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


        projetos.add(p1);
        projetos.add(p2);
        projetos.add(p3);
        projetos.add(p4);
        projetos.add(p5);
        projetos.add(p6);
        projetos.add(p7);
        projetos.add(p8);
        projetos.add(p9);
        projetos.add(p10);
    }

    public String getNome() {
        return nome;
    }

    public void ListarProjetosNConcluidosATempo(JFrame frame) {
        this.frameOriginal=frame;

    }

    public void ListarProjetosConcluidos(JFrame frame) {
        this.frameOriginal=frame;
    }

    public void ListarPessoasDoProjeto(JFrame frame) {
        this.frameOriginal = frame;
        novoProjeto(this.projetos);
        new ListaProjetos(this.projetos);
    }

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
}

