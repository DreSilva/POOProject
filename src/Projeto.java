import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Projeto {
    protected String nome, acronimo;
    protected Data dataInicio;
    protected Data dataDeFim;
    protected int duracao;
    protected JFrame frameDisplay, frameProjetos, frameProjetosConc, frameTarefas, frameTarefasConc,framePessoas, frameCriaTarefa, framePessoasProj;
    protected static ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    protected static ArrayList<Docentes> docentes = new ArrayList<Docentes>();
    protected static ArrayList<Bolseiros> bolseiros = new ArrayList<Bolseiros>();
    protected Pessoa investigadorPrincipal;
    protected JList listaSelecionados;


    public void DisplayProjeto(JFrame frame) {
        this.frameProjetos = frame;
        if (dataDeFim.getDia() == 0)
            new DarDisplayProj();
        else
            new DarDisplayProjConc();
    }

    /**
     * Classe que mostra as informações do projeto (projeto que ainda não foi terminado), nome, acrónimo, data de inicio, duração estimada
     * Mostra também os botões Listar Tarefas, Listar Pessoas, Criar nova Tarefa, Listar tarefas não iniciadas, Listar tarefas não concluidas,
     * Listar tarefas concluidas, Acrescentar uma pessoa ao projeto, Calcular o custo do projeto, Terminar o projeto e Voltar á frame anterior.
     */
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
            } else if (cmd.equals("LISTARPESSOAS")) {
                listarPessoas(frameDisplay);
                frameDisplay.setVisible(false);
            } else if (cmd.equals("CRIARTAREFA")) {
                criarTarefa(frameDisplay);
                frameDisplay.setVisible(false);
            } else if (cmd.equals("LISTARTNINI")) {
                listarTarefasNaoIniciadas(frameDisplay);
                frameDisplay.setVisible(false);
            } else if (cmd.equals("LISTARTNC")) {
                listarTarefasNaoConcluidas(frameDisplay);
                frameDisplay.setVisible(false);
            } else if (cmd.equals("LISTARTCONC")) {
                listarTarefasConcluidas(frameDisplay);
                frameDisplay.setVisible(false);
            } else if (cmd.equals("NOVAPESSOA")) {
                novaPessoa(frameDisplay);

            } else if (cmd.equals("CUSTOPROJ")) {
                custoProjeto(frameDisplay);

            } else if (cmd.equals("TERMINAPROJ")) {
                terminarProjeto(frameDisplay);
                frameProjetos.setVisible(true);

            } else if (cmd.equals("VOLTAR")) {
                frameDisplay.dispose();
                frameProjetos.setVisible(true);
            }
        }
    }

    /**
     * Classe que mostra as informações do projeto (já terminado), nome, acrónimo, data de inicio, duração e data de fim.
     * Mostra também os botões Listar Tarefas (que já estão todas terminadas), Listar Pessoas e Voltar à frame anterior.
     */
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
            panel.add(voltar);

            frameProjetosConc.add(panel);
            frameProjetosConc.setVisible(true);
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
                listarPessoas(frameProjetosConc);
            }
        }
    }


    public void criarTarefa(JFrame frame) {
        this.frameDisplay = frame;
        new CriaTarefa();
    }

    /**
     * Classe que cria uma nova tarefa, pede ao utilizador a data de inicio a duração estimada e a percentagem de conclusão.
     * Mostra também os botões Ok e Voltar á frame anterior.
     */
    public class CriaTarefa extends JFrame {
        protected JPanel panel;
        protected JLabel labelDataIn, labelDuracao, labelTipo;
        protected JTextField fieldDataIn, fieldDuracao, fieldTipo;
        protected JButton ok, voltar;

        public CriaTarefa() {
            super();

            frameCriaTarefa = new JFrame();
            frameCriaTarefa.setTitle("Criar Tarefa");
            frameCriaTarefa.setSize(500, 300);
            frameCriaTarefa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2));

            labelDataIn = new JLabel("Data de Inicio (dd/mm/aaaa)");
            labelDuracao = new JLabel("Duração Estimada (em meses)");
            labelTipo = new JLabel("Tipo de tarefa (Documentação,Design,Desenvolvimento)");

            fieldDataIn = new JTextField(10);
            fieldDuracao = new JTextField(5);
            fieldTipo = new JTextField(15);

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
            panel.add(labelTipo);
            panel.add(fieldTipo);
            panel.add(ok);
            panel.add(voltar);

            fieldDataIn.setDocument(new JTextFieldLimit(10));
            fieldDuracao.setDocument(new JTextFieldLimit(5));
            fieldTipo.setDocument(new JTextFieldLimit(15));

            frameCriaTarefa.add(panel);
            frameCriaTarefa.setVisible(true);
        }

        private class CriaTarefaListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("OK")) {
                    try {
                        double duracaoOut;
                        int percConcOut;
                        String[] data;
                        int testdia, testmes, testano;
                        int flag = 0;
                        Tarefa t1;

                        String dataInInput = fieldDataIn.getText();
                        String duracaoInput = fieldDuracao.getText();
                        String tipo = fieldTipo.getText();

                        duracaoOut = Double.parseDouble(duracaoInput);

                        if (verificaData(dataInInput) == 1) {
                            flag += 1;
                        } else
                            JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos de forma correta.", "Inválido", JOptionPane.ERROR_MESSAGE);

                        if (tipo.equals("Documentação")) {
                            flag += 1;
                            System.out.println("doc");
                        } else if (tipo.equals("Design")) {
                            flag += 1;
                            System.out.println("design");
                        } else if (tipo.equals("Desenvolvimento")) {
                            flag += 1;
                            System.out.println("desen");
                        } else
                            JOptionPane.showMessageDialog(null, "Insira um dos tipos de tarefas disponíveis!", "Inválido", JOptionPane.ERROR_MESSAGE);

                        if (flag == 2) {
                            data = dataInInput.split("/");
                            testdia = Integer.parseInt(data[0]);
                            testmes = Integer.parseInt(data[1]);
                            testano = Integer.parseInt(data[2]);
                            if (tipo.equals("Documentação")) {
                                t1 = new Documentacao(testdia, testmes, testano, duracaoOut, 0);
                            } else if (tipo.equals("Design")) {
                                t1 = new Design(testdia, testmes, testano, duracaoOut, 0);
                            } else {
                                t1 = new Desenvolvimento(testdia, testmes, testano, duracaoOut, 0);
                            }
                            tarefas.add(t1);
                            JOptionPane.showMessageDialog(null, "Tarefa criada e adicionada com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
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
                int counter = 0;
                data = dataInInput.split("/");
                testdia = Integer.parseInt(data[0]);
                testmes = Integer.parseInt(data[1]);
                testano = Integer.parseInt(data[2]);

                for (int i = 0; i < dataInInput.length(); i++) {
                    Character c1 = dataInInput.charAt(i);
                    Character c2 = '/';
                    if (c1.equals(c2)) {
                        counter++;
                    }
                }

                if (counter != 2)
                    return 0;
                if (testmes > 12)
                    return 0;
                if ((testmes == 1 || testmes == 3 || testmes == 5 || testmes == 7 || testmes == 8 || testmes == 10 || testmes == 12) && (testdia > 31))
                    return 0;
                if ((testmes == 4 || testmes == 6 || testmes == 9 || testmes == 11) && (testdia > 30))
                    return 0;
                if ((testmes == 2) && (testdia > 28))
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

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    public void listarTarefasNaoIniciadas(JFrame frame) {
        ArrayList<Tarefa> tarefasNaoIni = new ArrayList<Tarefa>();
        this.frameDisplay = frame;
        for (Tarefa i : tarefas) {
            if (i.percentagemConc == 0)
                tarefasNaoIni.add(i);
        }
        new ListaTarefas(tarefas);
    }

    public void listarTarefasNaoConcluidas(JFrame frame) {
        ArrayList<Tarefa> tarefasNaoIni = new ArrayList<Tarefa>();
        this.frameDisplay = frame;
        for (Tarefa i : tarefas) {
            if (i.percentagemConc != 100)
                tarefasNaoIni.add(i);
        }
        new ListaTarefas(tarefas);
    }

    public void listarTarefas(JFrame frame) {
        this.frameDisplay = frame;
        new ListaTarefas(this.tarefas);
    }

    /**
     * Classe que lista as tarefas do projeto. Mostra também os botões Remover tarefa, Atribuir tarefa, Ok e Voltar á frame anterior.
     */
    public class ListaTarefas extends JFrame {
        protected JLabel label;
        protected JPanel panel;
        protected JButton ok, voltar, removerTarefa, atribuirTarefa;
        protected JScrollPane listScroller;

        public ListaTarefas(ArrayList<Tarefa> tarefas) {
            super();

            frameTarefas = new JFrame();
            frameTarefas.setTitle("Lista de Tarefas");
            frameTarefas.setSize(350, 350);
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
            for (Tarefa i : tarefas) {
                if (i.getTaxaDeEsforco() == 1) {
                    if (i.pessoaReponsavel != null) {
                        listTarefas.addElement(i.pessoaReponsavel.nome + " <Desenvolvimento> " + i.percentagemConc);
                    } else {
                        listTarefas.addElement("<Sem responsável>" + " <Desenvolvimento> " + i.percentagemConc);
                    }
                } else if (i.getTaxaDeEsforco() == 0.5) {
                    if (i.pessoaReponsavel != null) {
                        listTarefas.addElement(i.pessoaReponsavel.nome + " <Design> " + i.percentagemConc);
                    } else {
                        listTarefas.addElement("<Sem responsável>" + " <Design> " + i.percentagemConc);
                    }
                } else if (i.getTaxaDeEsforco() == 0.25) {
                    if (i.pessoaReponsavel != null) {
                        listTarefas.addElement(i.pessoaReponsavel.nome + " <Documentação> " + i.percentagemConc);
                    } else {
                        listTarefas.addElement("<Sem responsável>" + " <Documentação> " + i.percentagemConc);
                    }
                }
            }
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
                frameTarefas.dispose();
                frameDisplay.setVisible(true);
            } else if (cmd.equals("OK")) {
                //frameTarefas.setVisible(false);
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(false);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    int a = listaSelecionados.getSelectedIndex();
                    Tarefa tarefaDesejada = tarefas.get(a);
                    if (tarefaDesejada != null) {
                        tarefaDesejada.DisplayTarefa(frameDisplay);
                        frameTarefas.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);

                }
            } else if (cmd.equals("REMOVER")) {
                System.out.println("2");
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(false);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    int a = listaSelecionados.getSelectedIndex();
                    Tarefa tarefaDesejada = tarefas.get(a);
                    if (tarefaDesejada != null) {
                        tarefas.remove(tarefaDesejada);
                        frameTarefas.dispose();
                        frameDisplay.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(true);
                }
            } else if (cmd.equals("ATRIBUIR")) {
                System.out.println("3");
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(false);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    int a = listaSelecionados.getSelectedIndex();
                    Tarefa tarefaDesejada = tarefas.get(a);
                    if (tarefaDesejada != null) {
                        atribuirTarefa(frameTarefas,tarefaDesejada);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(true);
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

    public void atribuirTarefa (JFrame frame, Tarefa tarefaDesejada){
            int flag=0;
            Pessoa pessoaDesejada=null;
            String value = JOptionPane.showInputDialog(null, "Introduza a pessoa a atribuir a tarefa", "Input", JOptionPane.QUESTION_MESSAGE);
            for (Pessoa a : Projeto.docentes){
                if (a.nome.equals(value))
                    pessoaDesejada=a;
            }
            for (Pessoa b : Projeto.bolseiros){
                if (b.nome.equals(value))
                    pessoaDesejada=b;
            }
            if (investigadorPrincipal.nome.equals(value))
                pessoaDesejada=investigadorPrincipal;
            if (pessoaDesejada==null)
                JOptionPane.showMessageDialog(null, "Tem de escolher uma pessoa do projeto!", "Inválido", JOptionPane.ERROR_MESSAGE);
            else {
                verificaDisponibiladadeParaTarefa(tarefaDesejada,pessoaDesejada);
            }

    }

    public int verificaDisponibiladadeParaTarefa(Tarefa tarefaDesejada, Pessoa pessoaDesejada) {
        Data dataFinalEstimadaDeTarefa=calcularDataFinalTarefa(tarefaDesejada.dataInicio,tarefaDesejada.duracao);
        Data dataAtual=tarefaDesejada.dataInicio;
        double taxaDiaria=0,flag=0;
        while(dataAtual.equals(dataFinalEstimadaDeTarefa)!=1){
            flag=testarTaxaDeEsforcoDiaria(pessoaDesejada.tarefas,tarefaDesejada.getTaxaDeEsforco(),dataAtual);
        }
        if(flag==1)
            return 0;
        else
            return 1;
    }

    public int testarTaxaDeEsforcoDiaria(ArrayList<Tarefa> tarefas,double taxaDeEsforço,Data dataAtual){
        Data dataFinalEstimadaDeTarefa;
        double taxaDeEsforçoDia=0;
        for(Tarefa i : tarefas){
            dataFinalEstimadaDeTarefa=calcularDataFinalTarefa(i.dataInicio,i.duracao);
            if(dataFinalEstimadaDeTarefa.getDia()>=dataAtual.getDia() && dataFinalEstimadaDeTarefa.getMês()>=dataAtual.getMês() && dataFinalEstimadaDeTarefa.getAno()>=dataAtual.getAno() && i.dataInicio.getDia()<=dataAtual.getDia() && i.dataInicio.getMês()<=dataAtual.getMês() && i.dataInicio.getAno()>=dataAtual.getAno()){
                taxaDeEsforçoDia+=i.getTaxaDeEsforco();
                if(taxaDeEsforçoDia==1)
                    return 1;
            }
        }
        if(taxaDeEsforço+taxaDeEsforçoDia>1)
            return 1;
        else
            return 0;
    }

    public Data calcularDataFinalTarefa(Data dataInicio,double duracao){
        Data dataDeFim=dataInicio;
        double D=duracao;
        while (D>=12){
            dataDeFim.setAno(dataDeFim.getAno()+1);
            D-=12;
        }
        dataDeFim.setMês((int)Math.floor(D));
        D-=(int)Math.floor(D);
        dataDeFim.setDia((int)Math.floor(D*30));
        return dataDeFim;
    }

    public void listarTarefasConcluidas(JFrame frame) {
        ArrayList<Tarefa> tarefasNaoIni = new ArrayList<Tarefa>();
        this.frameDisplay = frame;
        for (Tarefa i : tarefas) {
            if (i.percentagemConc == 100)
                tarefasNaoIni.add(i);
        }
        new ListaTarefasConc(tarefas);
    }

    public class ListaTarefasConc extends JFrame {
        protected JLabel label;
        protected JPanel panel;
        protected JButton ok, voltar;
        protected JScrollPane listScroller;

        public ListaTarefasConc(ArrayList<Tarefa> tarefas) {
            super();

            frameTarefasConc = new JFrame();
            frameTarefasConc.setTitle("Lista de Tarefas concluidas");
            frameTarefasConc.setSize(350, 350);
            frameTarefasConc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ok = new JButton("Ok");
            ok.setActionCommand("OK");
            ok.addActionListener(new ListaTarefasConcListener());
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new ListaTarefasConcListener());

            DefaultListModel listTarefas = new DefaultListModel();
            for (Tarefa i : tarefas) {
                if (i.getTaxaDeEsforco() == 1) {
                    if (i.pessoaReponsavel != null) {
                        listTarefas.addElement(i.pessoaReponsavel.nome + " <Desenvolvimento> " + i.percentagemConc);
                    } else {
                        listTarefas.addElement("<Sem responsável>" + " <Desenvolvimento> " + i.percentagemConc);
                    }
                } else if (i.getTaxaDeEsforco() == 0.5) {
                    if (i.pessoaReponsavel != null) {
                        listTarefas.addElement(i.pessoaReponsavel.nome + " <Design> " + i.percentagemConc);
                    } else {
                        listTarefas.addElement("<Sem responsável>" + " <Design> " + i.percentagemConc);
                    }
                } else if (i.getTaxaDeEsforco() == 0.25) {
                    if (i.pessoaReponsavel != null) {
                        listTarefas.addElement(i.pessoaReponsavel.nome + " <Documentação> " + i.percentagemConc);
                    } else {
                        listTarefas.addElement("<Sem responsável>" + " <Documentação> " + i.percentagemConc);
                    }
                }
            }

            listaSelecionados = new JList(listTarefas);
            listScroller = new JScrollPane(listaSelecionados);
            listScroller.setBounds(50, 35, 300, 150);

            panel = new JPanel();
            panel.add(listScroller);
            panel.add(ok);
            panel.add(voltar);

            frameTarefasConc.add(panel);
            frameTarefasConc.setVisible(true);
        }
    }
    private class ListaTarefasConcListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String valoresDaLista;
            if (cmd.equals("VOLTAR")) {
                frameTarefasConc.dispose();
                frameDisplay.setVisible(true);
            } else if (cmd.equals("OK")) {
                valoresDaLista = String.join(";", listaSelecionados.getSelectedValuesList());
                if (valoresDaLista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameProjetos.setVisible(false);
                } else if (quantosSelecionados(valoresDaLista) == 1) {
                    int a = listaSelecionados.getSelectedIndex();
                    Tarefa tarefaDesejada = tarefas.get(a);
                    if (tarefaDesejada != null) {
                        tarefaDesejada.DisplayTarefa(frameDisplay);
                        frameTarefasConc.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Só pode selecionar uma tarefa!", "Inválido", JOptionPane.ERROR_MESSAGE);

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

    /**
     * Classe que lista as pessoas do projeto, investigador principal, docentes e bolseiros. Mostra também os botões Ok e Voltar à frame
     * anterior.
     */
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
                listPessoas.addElement(investigadorPrincipal.nome + " <investigador principal>");
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
            int indiceSelecionados[];
            Pessoa pessoaDesejada;
            String cmd = e.getActionCommand();
            if (cmd.equals("OK")) {
                framePessoas.setVisible(false);
                indiceSelecionados=listaSelecionados.getSelectedIndices();
                if(indiceSelecionados.length==0){
                    JOptionPane.showMessageDialog(null, "Tem de selecionar uma pessoa!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameDisplay.setVisible(true);
                }
                else if(indiceSelecionados.length>1){
                    JOptionPane.showMessageDialog(null, "Só pode selecionar um projeto!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameDisplay.setVisible(true);
                }
                else{
                    if(indiceSelecionados[0]==0){
                        pessoaDesejada=investigadorPrincipal;
                    }
                    else if(indiceSelecionados[0]>1 && indiceSelecionados[0]<=docentes.size()){
                        pessoaDesejada=docentes.get(indiceSelecionados[0]-1);
                    }
                    else{
                       pessoaDesejada=bolseiros.get(indiceSelecionados[0]-1-docentes.size());
                    }
                    pessoaDesejada.DisplayPessoa(framePessoas);
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

        Pessoa procuraPessoaNoProjeto(String nome,ArrayList<Bolseiros> bolseiros, ArrayList<Docentes> docentes, Pessoa investigadorPrincipal) {
            Pessoa alvo = null;
            if (investigadorPrincipal.nome.equals(nome)) {
                alvo = investigadorPrincipal;
            }
            if (alvo == null) {
                for (Docentes l : docentes) {
                    if (l.nome.equals(nome))
                        alvo = l;
                }
            }
            if(alvo==null) {
                for (Bolseiros j : bolseiros) {
                    if (j.nome.equals(nome))
                        alvo = j;
                }
            }
            if(alvo==null)
                System.out.println("UR MEGA GAY");
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

            if (value==null){
                //frameDisplay.dispose();
                //frameProjetos.setVisible(false);
                frameDisplay.setVisible(true);
            }
            else if (value.length()==0){
                JOptionPane.showMessageDialog(null, "Tem que inserir uma data", "Inválido", JOptionPane.ERROR_MESSAGE);
                frameDisplay.setVisible(false);
            }

            else if (value.length()>0) {
                if (verificaData(value)==0) {
                    JOptionPane.showMessageDialog(null, "Data Inválida!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameDisplay.dispose();
                    frameProjetos.setVisible(true);
                }
                else if (verificaData(value)==2) {
                    JOptionPane.showMessageDialog(null, "Insira uma data futura!", "Inválido", JOptionPane.ERROR_MESSAGE);
                    frameDisplay.dispose();
                    frameProjetos.setVisible(true);
                }else if (verificaData(value) ==1) {
                    String[] dataFim = value.split("/");
                    this.dataDeFim.setDia(Integer.parseInt(dataFim[0]));
                    this.dataDeFim.setMês(Integer.parseInt(dataFim[1]));
                    this.dataDeFim.setAno(Integer.parseInt(dataFim[2]));
                    frameDisplay.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Projeto terminado com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Não pode terminar o projeto pois há tarefas qua ainda não foram concluidas!", "Inválido", JOptionPane.ERROR_MESSAGE);
            frameDisplay.setVisible(true);
        }
    }

    public void novaPessoa(JFrame frame){
        int flag=0;
        String value = JOptionPane.showInputDialog(null, "Introduza o nome da pessoa", "Adicionar pessoa ao Projeto", JOptionPane.QUESTION_MESSAGE);
        for (Pessoa i: CentroDeInvestigacao.pessoas){
            if (i.nome.equals(value)) {
                flag=1;
                if (investigadorPrincipal.nome.equals(value))
                    JOptionPane.showMessageDialog(null, "Essa pessoa já se encontra no projeto como Investigador Principal!", "Inválido", JOptionPane.ERROR_MESSAGE);
                for (Docentes l: docentes){
                    if (l.nome.equals(value))
                        JOptionPane.showMessageDialog(null, "Essa pessoa já se encontra no projeto como Docente!", "Inválido", JOptionPane.ERROR_MESSAGE);
                }
                for (Bolseiros j: bolseiros){
                    if (j.nome.equals(value))
                        JOptionPane.showMessageDialog(null, "Essa pessoa já se encontra no projeto como Bolseiro!", "Inválido", JOptionPane.ERROR_MESSAGE);
                }
                /*if (i.custo()!=0)
                    Projeto.bolseiros.addElement(i);
                else
                    Projeto.docentes.addElement(i);*/
            }
        }
        if (flag==0){
            JOptionPane.showMessageDialog(null, "Tem que inserir uma pessoa do centro!", "Inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void custoProjeto(JFrame frame){
        double duracaoEscolhida;
        double custoProj=0;
        for (Tarefa i : tarefas){
            if (i.percentagemConc==100)
                duracaoEscolhida=calculaDifData(i.dataInicio,i.dataDeFim);
            else
                duracaoEscolhida=i.duracao;
            custoProj+=duracaoEscolhida*i.pessoaReponsavel.custo();
        }
        JOptionPane.showMessageDialog(null, custoProj+" €", "Custo do Projeto", JOptionPane.PLAIN_MESSAGE);
    }

    private double calculaDifData(Data dataInicio, Data dataDeFim) {
        double dataDif;
        int dia1=dataInicio.getDia(), mes1=dataInicio.getMês(), ano1=dataInicio.getAno(), dia2=dataDeFim.getDia(), mes2=dataDeFim.getMês(), ano2=dataDeFim.getAno();
        int difdias, difmeses, difanos;
        if(dia2 < dia1)
        {
            // meses de fevereiro
            if (mes2 == 3)
            {
                //  verifica se é ano bisseixto
                if ((ano2 % 4 == 0 && ano2 % 100 != 0) || (ano2 % 400 == 0))
                {
                    dia2 += 29;
                }
                else
                {
                    dia2 += 28;
                }
            }
            // Abril ou Junho ou Setembro ou Novembro
            else if (mes2 == 5 || mes2 == 7 || mes2 == 10 || mes2 == 12)
            {
                dia2 += 30;
            }
            // Janeiro ou Março ou Maio ou Julho ou Agosto ou Outubro ou Dezembro
            else
            {
                dia2 += 31;
            }
            mes2 = mes2 - 1;
        }
        if (mes2 < mes1)
        {
            mes2 += 12;
            ano2 -= 1;
        }
        difdias = dia2 - dia1;
        difmeses = mes2 - mes1;
        difanos = ano2 - ano1;

        //custoFinal=((difdias*31/custoPorMes)*custoPorMes)+(difmeses*custoPorMes)+(difanos*12*custoPorMes);
        dataDif=difdias/30+difmeses+(difanos*12);
        return dataDif;
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
