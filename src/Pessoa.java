import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pessoa {
    protected String nome,email;
    protected ArrayList<Tarefa> tarefas= new ArrayList<Tarefa>();
    protected ArrayList<Projeto> projeto= new ArrayList<Projeto>();
    protected JFrame framePessoasCarac, framePessoas;

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

                    //framePessoas.setVisible(true);
                }
                else if (cmd.equals("LISTARTAREFASPESSOA")) {

                    //framePessoas.setVisible(true);
                }

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
