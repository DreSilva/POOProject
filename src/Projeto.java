import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

public class Projeto {
    protected String nome, acronimo;
    protected Data dataInicio;
    protected Data dataDeFim;
    protected int duracao;
    protected JFrame frameDisplay, frameProjetos, frameProjetosConc, frameTarefas, framePessoas, frameCriaTarefa, framePessoasProj;
    protected static ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    protected static ArrayList<Docentes> docentes = new ArrayList<Docentes>();
    protected static ArrayList<Bolseiros> bolseiros = new ArrayList<Bolseiros>();
    protected Docentes investigadorPrincipal;
    protected JList listaSelecionados;


    public void DisplayProjeto(JFrame frame) {
        this.frameProjetos = frame;
        if (dataDeFim.getDia() == 0)
            new DarDisplayProj();
        else
            new DarDisplayProjConc();
    }

    public class DarDisplayProj extends JFrame {
        protected JLabel labelNome, labelAcro, labelDataIn, labelDuracao;
        protected JPanel panel;
        protected JButton voltar, listarTarefas, listarPessoas, novaTarefa, listarTNInici, listarTNConc, listarTarefasConc, custoProjeto, novaPessoa, terminaProjeto;

        public DarDisplayProj() {
            super();

            frameDisplay = new JFrame();
            frameDisplay.setTitle("Informação do projeto");
            frameDisplay.setSize(500, 500);

            panel = new JPanel();
            panel.setLayout(new GridLayout(7, 2));

            frameDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            labelNome = new JLabel(formatLabelNome(nome));
            labelAcro = new JLabel(formatLabelAcro(acronimo));
            labelDataIn = new JLabel(formatLabelDataDeIni(dataInicio));
            labelDuracao = new JLabel(formatLabelDuracao(duracao));

            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ProjetoListener());
            listarTarefas = new JButton("Listar Tarefas");
            listarTarefas.setActionCommand("LISTARTAREFAS");
            listarTarefas.addActionListener(new ProjetoListener());
            listarPessoas = new JButton("Listar Pessoas");
            listarPessoas.setActionCommand("LISTARPESSOAS");
            listarPessoas.addActionListener(new ProjetoListener());
            novaTarefa = new JButton("Criar nova Tarefa");
            novaTarefa.setActionCommand("CRIARTAREFA");
            novaTarefa.addActionListener(new ProjetoListener());
            listarTNInici = new JButton("Listar Tarefas não iniciadas");
            listarTNInici.setActionCommand("LISTARTNINI");
            listarTNInici.addActionListener(new ProjetoListener());
            listarTNConc = new JButton("Listar Tarefas não concluidas");
            listarTNConc.setActionCommand("LISTARTNC");
            listarTNConc.addActionListener(new ProjetoListener());
            listarTarefasConc = new JButton("Listar Tarefas concluidas");
            listarTarefasConc.setActionCommand("LISTARTCONC");
            listarTarefasConc.addActionListener(new ProjetoListener());
            novaPessoa = new JButton("Acrescentar pessoa ao Projeto");
            novaPessoa.setActionCommand("NOVAPESSOA");
            novaPessoa.addActionListener(new ProjetoListener());
            custoProjeto = new JButton("Calcular custo do Projeto");
            custoProjeto.setActionCommand("CUSTOPROJ");
            custoProjeto.addActionListener(new ProjetoListener());
            terminaProjeto = new JButton("Terminar Projeto");
            terminaProjeto.setActionCommand("TERMINAPROJ");
            terminaProjeto.addActionListener(new ProjetoListener());

            panel.add(labelNome);
            panel.add(labelAcro);
            panel.add(labelDataIn);
            panel.add(labelDuracao);
            panel.add(listarTarefas);
            panel.add(listarPessoas);
            panel.add(novaTarefa);
            panel.add(listarTNInici);
            panel.add(listarTNConc);
            panel.add(listarTarefasConc);
            panel.add(novaPessoa);
            panel.add(custoProjeto);
            panel.add(terminaProjeto);
            panel.add(voltar);

            frameDisplay.add(panel);
            frameDisplay.setVisible(true);
        }
    }
    private class ProjetoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("LISTARTAREFAS")) {
                frameDisplay.dispose();
                listarTarefas(frameDisplay);
            }else if(cmd.equals("LISTARPESSOAS")){
                listarPessoas(frameDisplay);
                frameDisplay.setVisible(false);
            }else if (cmd.equals("CRIARTAREFA")){
                criarTarefa(frameDisplay);
                frameDisplay.setVisible(false);
            }else if (cmd.equals("LISTARTNINI")) {
                listarTarefasNaoIniciadas(frameDisplay);
                frameDisplay.setVisible(false);
            }else if (cmd.equals("LISTARTNC")) {
                listarTarefasNaoConcluidas(frameDisplay);
                frameDisplay.setVisible(false);
            }else if (cmd.equals("LISTARTCONC")) {
                listarTarefasConcluidas(frameDisplay);
                frameDisplay.setVisible(false);
            }
            else if (cmd.equals("NOVAPESSOA")) {
                novaPessoa(frameDisplay);

            }else if (cmd.equals("CUSTOPROJ")) {
                custoProjeto(frameDisplay);

            }else if (cmd.equals("TERMINAPROJ")){
                terminarProjeto(frameDisplay);
                frameProjetos.setVisible(true);

            } else if (cmd.equals("VOLTAR")) {
                frameDisplay.dispose();
                frameProjetos.setVisible(true);
            }
        }
    }

    public class DarDisplayProjConc extends JFrame {
        protected JLabel labelNome, labelAcro, labelDataIn, labelDuracao, labelDataFim;
        protected JPanel panel;
        protected JButton voltar, listarTarefas, listarPessoas;

        public DarDisplayProjConc() {
            super();

            frameProjetosConc = new JFrame();
            frameProjetosConc.setTitle("Informação de Projeto ( CONCLUIDO )");
            frameProjetosConc.setSize(400, 300);
            frameProjetosConc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(new GridLayout(8, 2));

            labelNome = new JLabel(formatLabelNome(nome));
            labelAcro = new JLabel(formatLabelAcro(acronimo));
            labelDataIn = new JLabel(formatLabelDataDeIni(dataInicio));
            labelDuracao = new JLabel(formatLabelDuracao(duracao));
            labelDataFim = new JLabel(formatLabelDataDeFim(dataDeFim));

            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ProjetoConcListener());

            listarTarefas = new JButton("Listar Tarefas (Concluidas)");
            listarTarefas.setActionCommand("LISTARTAREFAS");
            listarTarefas.addActionListener(new ProjetoConcListener());
            listarPessoas = new JButton("Listar Pessoas");
            listarPessoas.setActionCommand("LISTARPESSOAS");
            listarPessoas.addActionListener(new ProjetoConcListener());

            panel.add(labelNome);
            panel.add(labelAcro);
            panel.add(labelDataIn);
            panel.add(labelDuracao);
            panel.add(labelDataFim);
            panel.add(listarPessoas);
            panel.add(listarTarefas);
        }
    }
    private class ProjetoConcListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("VOLTAR")) {
                frameProjetosConc.dispose();
                frameProjetos.setVisible(true);
            } else if (cmd.equals("LISTARTAREFAS")) {
                listarTarefas(frameProjetosConc);
            } else if (cmd.equals("LISTARPESSOAS")) {
                //listarPessoas(frameProjetosConc);
            }
        }
    }


    public void criarTarefa(JFrame frame){
        this.frameDisplay = frame;
        new CriaTarefa();
    }

    public class CriaTarefa extends JFrame{
        protected JPanel panel;
        protected JLabel labelDataIn, labelDuracao, labelPercConc;
        protected JTextField fieldDataIn, fieldDuracao, fieldPercConc;
        protected JButton ok, voltar;

        public CriaTarefa(){
            super();

            frameCriaTarefa = new JFrame();
            frameCriaTarefa.setTitle("Criar Tarefa");
            frameCriaTarefa.setSize(500, 300);
            frameCriaTarefa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(new GridLayout(4,2));

            labelDataIn = new JLabel("Data de Inicio (dd/mm/aaaa)");
            labelDuracao = new JLabel("Duração Estimada (em meses)");
            labelPercConc = new JLabel("Percentagem de Conclusão");

            fieldDataIn = new JTextField(10);
            fieldDuracao = new JTextField(5);
            fieldPercConc = new JTextField(3);

            ok = new JButton("OK");
            ok.setActionCommand("OK");
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            ok.addActionListener(new CriaTarefaListener());
            voltar.addActionListener(new CriaTarefaListener());

            panel.add(labelDataIn);
            panel.add(fieldDataIn);
            panel.add(labelDuracao);
            panel.add(fieldDuracao);
            panel.add(labelPercConc);
            panel.add(fieldPercConc);
            panel.add(ok);
            panel.add(voltar);

            fieldDataIn.setDocument(new JTextFieldLimit(10));
            fieldDuracao.setDocument(new JTextFieldLimit(5));
            fieldPercConc.setDocument(new JTextFieldLimit(3));

            frameCriaTarefa.add(panel);
            frameCriaTarefa.setVisible(true);
        }
        private class CriaTarefaListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("OK")) {
                    try{
                        double duracaoOut;
                        String[] data;
                        int testdia, testmes, testano;

                        String dataInInput = fieldDataIn.getText();
                        String duracaoInput = fieldDuracao.getText();
                        String percConcInput = fieldPercConc.getText();

                        duracaoOut=Double.parseDouble(duracaoInput);

                        if(verificaData(dataInInput) ==1){
                            data = dataInInput.split("/");
                            testdia = Integer.parseInt(data[0]);
                            testmes = Integer.parseInt(data[1]);
                            testano = Integer.parseInt(data[2]);
                            Tarefa t1=new Tarefa(testdia,testmes,testano,duracaoOut,Integer.parseInt(percConcInput));
                            tarefas.add(t1);
                            JOptionPane.showMessageDialog(null, "Tarefa criada e adicionada com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos de forma correta.", "Inválido", JOptionPane.ERROR_MESSAGE);
                        }

                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos de forma correta.", "Inválido", JOptionPane.ERROR_MESSAGE);
                    }
                    frameCriaTarefa.setVisible(false);
                    frameDisplay.setVisible(true);

                } else if (cmd.equals("VOLTAR")) {
                    frameCriaTarefa.dispose();
                    frameDisplay.setVisible(true);
                }
            }
            public int verificaData(String dataInInput) {
                String[] data;
                int testdia, testmes, testano;
                int counter=0;
                data = dataInInput.split("/");
                testdia = Integer.parseInt(data[0]);
                testmes = Integer.parseInt(data[1]);
                testano = Integer.parseInt(data[2]);

                for (int i=0;i<dataInInput.length();i++){
                    Character c1 = dataInInput.charAt(i);
                    Character c2 = '/';
                    if (c1.equals(c2)){
                        counter++;
                    }
                }

                if (counter!=2)
                    return 0;
                if (testmes>12)
                    return 0;
                if ((testmes==1||testmes==3||testmes==5||testmes==7||testmes==8||testmes==10||testmes==12) && (testdia>31))
                    return 0;
                if ((testmes==4||testmes==6||testmes==9||testmes==11) && (testdia>30))
                    return 0;
                if ((testmes==2) && (testdia>28))
                    return 0;

                return 1;
            }
        }
    }
    public class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    public void listarTarefasNaoIniciadas(JFrame frame) {
        ArrayList<Tarefa> tarefasNaoIni = new ArrayList<Tarefa>();
        this.frameDisplay=frame;
        for (Tarefa i:tarefas){
            if (i.percentagemConc==0)
                tarefasNaoIni.add(i);
        }
        new ListaTarefas(tarefas);
    }
    public void listarTarefasNaoConcluidas(JFrame frame) {
        ArrayList<Tarefa> tarefasNaoIni = new ArrayList<Tarefa>();
        this.frameDisplay=frame;
        for (Tarefa i:tarefas){
            if (i.percentagemConc!=100)
                tarefasNaoIni.add(i);
        }
        new ListaTarefas(tarefas);
    }
    public void listarTarefasConcluidas(JFrame frame) {
        ArrayList<Tarefa> tarefasNaoIni = new ArrayList<Tarefa>();
        this.frameDisplay=frame;
        for (Tarefa i:tarefas){
            if (i.percentagemConc==100)
                tarefasNaoIni.add(i);
        }
        new ListaTarefas(tarefas);
    }

    public void listarTarefas(JFrame frame) {
        this.frameDisplay = frame;
        new ListaTarefas(this.tarefas);
    }
    public class ListaTarefas extends JFrame {
        protected JLabel label;
        protected JPanel panel;
        protected JButton ok, voltar,removerTarefa,atribuirTarefa;
        protected JScrollPane listScroller;

        public ListaTarefas(ArrayList<Tarefa> tarefas) {
            super();

            frameTarefas = new JFrame();
            frameTarefas.setTitle("Lista de Tarefas");
            frameTarefas.setSize(300, 300);
            frameTarefas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ok = new JButton("Ok");
            ok.setActionCommand("OK");
            ok.addActionListener(new ListaTarefasListener());
            removerTarefa = new JButton("Remover Tarefa");
            removerTarefa.setActionCommand("REMOVER");
            removerTarefa.addActionListener(new ListaTarefasListener());
            atribuirTarefa = new JButton("Atribuir Tarefa");
            atribuirTarefa.setActionCommand("ATRIBUIR");
            atribuirTarefa.addActionListener(new ListaTarefasListener());
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ListaTarefasListener());

            DefaultListModel listTarefas = new DefaultListModel();
            for (Tarefa i : tarefas)
                listTarefas.addElement(i);
            listaSelecionados = new JList(listTarefas);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);


            panel = new JPanel();
            panel.add(listScroller);
            panel.add(removerTarefa);
            panel.add(atribuirTarefa);
            panel.add(ok);
            panel.add(voltar);

            frameTarefas.add(panel);
            frameTarefas.setVisible(true);
        }
    }

    private class ListaTarefasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String valoresDaLista;
            if (cmd.equals("VOLTAR")) {
                //frameTarefas.dispose();
                frameDisplay.setVisible(true);
            } else if (cmd.equals("OK")) {
                if (cmd.equals("OK")) {
                    //frameTarefas.setVisible(false);
                    valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                    if (valoresDaLista.isEmpty() == true) {
                        JOptionPane.showMessageDialog(null, "Tem de selecionar uma tarefa!", "WARNING", JOptionPane.ERROR_MESSAGE);
                        frameProjetos.setVisible(true);
                    } else if (quantosSelecionados(valoresDaLista) == 1) {
                        int a=listaSelecionados.getSelectedIndex();
                        Tarefa tarefaDesejada = tarefas.get(a);
                        if (tarefaDesejada != null) {
                            //tarefaDesejada.DisplayTarefa(frameProjetos);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Só pode selecionar uma tarefa!", "WARNING", JOptionPane.ERROR_MESSAGE);
                        frameProjetos.setVisible(true);
                    }
                } else if (cmd.equals("REMOVER")) {
                    System.out.println("2");
                } else if (cmd.equals("ATRIBUIR")) {
                    System.out.println("3");
                }
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
    }

    public void listarPessoas(JFrame frame) {
        this.frameDisplay = frame;
        new ListarPessoas();
    }
    public class ListarPessoas extends JFrame {
        protected JLabel label;
        protected JPanel panel;
        protected JButton ok, voltar;
        protected JScrollPane listScroller;

        public ListarPessoas() {
            super();

            framePessoas = new JFrame();
            framePessoas.setTitle("Lista de Pessoas");
            framePessoas.setSize(400, 300);
            framePessoas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DefaultListModel listPessoas = new DefaultListModel();
            if (investigadorPrincipal!=null)
                listPessoas.addElement(investigadorPrincipal + " <investigador principal>");
            for (Docentes i : docentes)
                listPessoas.addElement(i.nome + " <docente>");
            for (Bolseiros i : bolseiros)
                listPessoas.addElement(i.nome+ " <bolseiro>");

            ok = new JButton("Ok");
            ok.setActionCommand("OK");
            ok.addActionListener(new ListaPessoasListener());
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ListaPessoasListener());

            listaSelecionados = new JList(listPessoas);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);

            panel = new JPanel();
            panel.add(listScroller);
            panel.add(ok);
            panel.add(voltar);

            framePessoas.add(panel);
            framePessoas.setVisible(true);
        }
    }
    private class ListaPessoasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String valoresDaLista;
            String cmd = e.getActionCommand();
            if (cmd.equals("OK")) {
                framePessoas.setVisible(false);
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma pessoa!", "WARNING", JOptionPane.ERROR_MESSAGE);
                    framePessoas.setVisible(true);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    Pessoa pessoaDesejada = procuraPessoaNoCentro(valoresDaLista, CentroDeInvestigacao.pessoas);
                    if(pessoaDesejada!=null) {
                        System.out.println("yo");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar um projeto!", "WARNING", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(true);
                }
            } else if (cmd.equals("VOLTAR")){
                framePessoas.dispose();
                frameDisplay.setVisible(true);
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

    public void terminarProjeto(JFrame frame) {
        int contaTar=0;
        int conta100=0;
        this.frameDisplay=frame;
        for (Tarefa i:tarefas){
            contaTar++;
            if (i.percentagemConc==100)
                conta100++;
        }
        if (contaTar==conta100){
            String value = JOptionPane.showInputDialog(null, "Introduza a data de fim do Projeto", "Terminar Projeto", JOptionPane.QUESTION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Projeto terminado com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            String[] dataFim = value.split("/");
            this.dataDeFim.setDia(Integer.parseInt(dataFim[0]));
            this.dataDeFim.setMês(Integer.parseInt(dataFim[1]));
            this.dataDeFim.setAno(Integer.parseInt(dataFim[2]));
            frameDisplay.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Não pode terminar o projeto pois há tarefas qua ainda não foram concluidas!", "Inválido", JOptionPane.ERROR_MESSAGE);
            frameDisplay.setVisible(true);
        }
    }

    public void novaPessoa(JFrame frame){
        /*String value = JOptionPane.showInputDialog(null, "Introduza o nome da pessoa", "Adicionar Projeto", JOptionPane.QUESTION_MESSAGE);
        for (Pessoa i: pessoas)*/
    }

    public void custoProjeto(JFrame frame){

    }

    public String formatLabelNome(String nome) {
        String nomeS = "Nome: ";
        return nomeS + nome;
    }
    public String formatLabelAcro(String acro) {
        String acroS = "Acrónimo: ";
        return acroS + acro;
    }
    public String formatLabelDataDeIni(Data data) {
        String dataDeIniS = "Data de Início: ";
        return dataDeIniS + data.getDia() + "/" + data.getMês() + "/" + data.getAno();
    }
    public String formatLabelDuracao(int duracao) {
        String duracaoS = "Duração (meses): ";
        return duracaoS + duracao;
    }
    public String formatLabelDataDeFim(Data data) {
        String dataDeFim = "Data de Fim: ";
        return dataDeFim + data.getDia() + "/" + data.getMês() + "/" + data.getAno();
    }

    /**
     * Construtor do projeto
     * @param nome nome do projeto
     * @param acronimo acronimo do projeto
     * @param diaIn dia de inicio do projeto
     * @param mesIn mes de inicio do projeto
     * @param anoIn ano de inicio do projeto
     * @param duracao duração do projeto
     */
    public Projeto(String nome, String acronimo, int diaIn, int mesIn, int anoIn, int duracao) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.dataDeFim = new Data(0, 0, 0);
    }
}
