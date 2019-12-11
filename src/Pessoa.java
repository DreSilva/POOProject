import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pessoa {
    protected String nome,email;
    protected ArrayList<Tarefa> tarefas= new ArrayList<Tarefa>();
    protected ArrayList<Projeto> projeto= new ArrayList<Projeto>();
    protected JFrame framePessoasCarac, framePessoas, frameProjPessoa, frameTarefasPessoa;

    public int isDocente(){
        return 0;
    }
    public int isMestre(){
        return 0;
    }
    public int isDoutorado(){
        return 0;
    }
    public int isLicenciado(){
        return 0;
    }

    public int custo(){
        return 0;
    }

    public void DisplayPessoa(JFrame frame){
        this.framePessoas = frame;
        new DarDisplayPessoa();
    }
    public class DarDisplayPessoa extends JFrame {
        protected JLabel labelNome, labelMail;
        protected JPanel panel;
        protected JButton voltar,listarProjetosPessoa,listarTarefasPessoa;

        public DarDisplayPessoa(){
            super();

            framePessoasCarac = new JFrame();
            framePessoasCarac.setTitle("Informação da pessoa");
            framePessoasCarac.setSize(500, 500);

            panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            framePessoasCarac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            labelNome = new JLabel(formatLabelNome(nome));
            labelMail = new JLabel(formatLabelMail(email));

            listarProjetosPessoa = new JButton("Listar Projetos da Pessoa");
            listarProjetosPessoa.setActionCommand("LISTARPROJPESSOA");
            listarProjetosPessoa.addActionListener(new DarDisplayPessoaListener());
            listarTarefasPessoa = new JButton("Listar Tarefas da Pessoa");
            listarTarefasPessoa.setActionCommand("LISTARTAREFAPESSOA");
            listarTarefasPessoa.addActionListener(new DarDisplayPessoaListener());
            voltar= new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new DarDisplayPessoaListener());

            panel.add(labelNome);
            panel.add(labelMail);
            panel.add(listarProjetosPessoa);
            panel.add(listarTarefasPessoa);
            panel.add(voltar);

            framePessoasCarac.add(panel);
            framePessoasCarac.setVisible(true);
        }
        private class DarDisplayPessoaListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("VOLTAR")) {
                    framePessoasCarac.dispose();
                    framePessoas.setVisible(true);
                }
                else if (cmd.equals("LISTARPROJPESSOA")) {
                    listarProjPessoa(framePessoas);
                    //framePessoas.setVisible(true);
                }
                else if (cmd.equals("LISTARTAREFASPESSOA")) {
                    listarTarefasPessoa(framePessoas);
                    //framePessoas.setVisible(true);
                }

            }
        }
    }
    public void listarProjPessoa(JFrame frame){
        this.framePessoas = frame;
        new ListarProjPessoa();
    }
    public class ListarProjPessoa extends JFrame{
        protected JLabel label;
        protected JPanel panel;
        protected JButton voltar;
        protected JScrollPane listScroller;

        public ListarProjPessoa() {
            super();

            frameProjPessoa = new JFrame();
            frameProjPessoa.setTitle("Lista de Projetos da pessoa");
            frameProjPessoa.setSize(400, 300);
            frameProjPessoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DefaultListModel listProjetosPessoa = new DefaultListModel();
            for (Projeto i : CentroDeInvestigacao.projetos){
                for (Pessoa l : i.pessoas){
                    if (l.nome.equals(nome)){
                        listProjetosPessoa.addElement(l.nome);
                    }
                }
            }

            JList listaSelecionados = new JList(listProjetosPessoa);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);

            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ListaProjPessoaListener());

            panel = new JPanel();
            panel.add(listScroller);
            panel.add(voltar);

            frameProjPessoa.add(panel);
            frameProjPessoa.setVisible(true);
        }
    }
    private class ListaProjPessoaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("VOLTAR")) {
                frameProjPessoa.dispose();
                framePessoas.setVisible(true);
            }
        }
    }

    public void listarTarefasPessoaPessoa(JFrame frame){
        this.framePessoas = frame;
        new ListarTarefasPessoa();
    }
    public class ListarTarefasPessoa extends JFrame{
        protected JLabel label;
        protected JPanel panel;
        protected JButton voltar;
        protected JScrollPane listScroller;

        public ListarTarefasPessoa() {
            super();

            frameTarefasPessoa = new JFrame();
            frameTarefasPessoa.setTitle("Lista de Tarefas da pessoa");
            frameTarefasPessoa.setSize(400, 300);
            frameTarefasPessoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DefaultListModel listTarPessoa = new DefaultListModel();
            for (Projeto i : CentroDeInvestigacao.projetos){
                for (Tarefa l : i.tarefas){
                    if (l.nome.equals(nome)){
                        listTarPessoa.addElement(*.nome);
                    }
                }
            }

            JList listaSelecionados = new JList(listTarPessoa);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);

            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ListaTarPessoaListener());

            panel = new JPanel();
            panel.add(listScroller);
            panel.add(voltar);

            frameTarefasPessoa.add(panel);
            frameTarefasPessoa.setVisible(true);
        }
    }
    private class ListaTarPessoaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("VOLTAR")) {
                frameTarefasPessoa.dispose();
                framePessoas.setVisible(true);
            }
        }
    }

    public String formatLabelNome(String nome) {
        String nomeS = "Nome: ";
        return nomeS + nome;
    }
    public String formatLabelMail(String email) {
        String mailS = "email: ";
        return mailS + email;
    }

    /**
     * Construtor da pessoa
     * @param nome nome da pessoa
     * @param email email da pessoa
     */
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}
