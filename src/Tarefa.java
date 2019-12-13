import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.io.Serializable;
import java.util.ArrayList;

public class Tarefa implements Serializable {
    protected Data dataInicio, dataDeFim;
    protected int percentagemConc;
    protected double duracao;
    protected JFrame frameDisplay,frameTarefas,frameDisplayTarefa;
    protected Pessoa pessoaReponsavel;
    public String DataHoje;

    public void DisplayTarefa (JFrame frame, String DataHoje){
        this.DataHoje=DataHoje;
        this.frameDisplay=frame;
        new DarDisplayTarefa();
    }
    public class DarDisplayTarefa extends JFrame{
        protected JLabel labelDataIn, labelDuracao, labelPercConc;
        protected JPanel panel;
        protected JButton voltar,atualizarPerc;

        public DarDisplayTarefa(){
            super();

            frameDisplayTarefa = new JFrame();
            frameDisplayTarefa.setTitle("Informação da tarefa");
            frameDisplayTarefa.setSize(500, 500);

            panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            frameDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            labelDataIn = new JLabel(formatLabelDataDeIni(dataInicio));
            labelDuracao = new JLabel(formatLabelDuracao(duracao));
            labelPercConc = new JLabel(formatLabelPercConc(percentagemConc));

            atualizarPerc = new JButton("Atualizar percentagem de conclusão");
            atualizarPerc.setActionCommand("ATUALIZAR");
            atualizarPerc.addActionListener(new DarDisplayTarefaListener());
            voltar = new JButton("Voltar");
            voltar.setActionCommand("VOLTAR");
            voltar.addActionListener(new DarDisplayTarefaListener());

            panel.add(labelDataIn);
            panel.add(labelDuracao);
            panel.add(labelPercConc);
            panel.add(atualizarPerc);
            panel.add(voltar);

            frameDisplayTarefa.add(panel);
            frameDisplayTarefa.setVisible(true);
        }
    }
    private class DarDisplayTarefaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("VOLTAR")){
                frameDisplayTarefa.dispose();
                frameDisplay.setVisible(true);
            }else if (cmd.equals("ATUALIZAR")){
                setPercentagemConc();
            }
        }
    }

    public String formatLabelDataDeIni(Data data) {
        String dataDeIniS = "Data de Início: ";
        return dataDeIniS + data.getDia() + "/" + data.getMês() + "/" + data.getAno();
    }
    public String formatLabelDuracao(double duracao) {
        String duracaoS = "Duração (meses): ";
        return duracaoS + duracao;
    }
    public String formatLabelPercConc(int percentagemConc) {
        String perConcS = "Percentagem conclusão: ";
        return perConcS + percentagemConc;
    }

    public double getTaxaDeEsforco(){
        return 0;
    }

    public void setPercentagemConc() {
        if(this.percentagemConc!=100) {
            String value = JOptionPane.showInputDialog(null, "Introduza a percentagem atualizada", "Input", JOptionPane.QUESTION_MESSAGE);
            int novaPerc = 0;
            try {
                novaPerc = Integer.parseInt(value);

                if (novaPerc > 100) {
                    JOptionPane.showMessageDialog(null, "Tem de inserir uma percentagem válida", "Inválido", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Tem de inserir uma percentagem válida!", "Inválido", JOptionPane.ERROR_MESSAGE);
            }
            this.percentagemConc = novaPerc;
            if (this.percentagemConc == 100) {
                String dataS = JOptionPane.showInputDialog(null, "Insira a data de final da Tarefa", "Input", JOptionPane.QUESTION_MESSAGE);
                if (verificaData(this.DataHoje) == 1) {
                    String[] dataA = dataS.split("/");
                    this.dataDeFim.setDia(Integer.parseInt(dataA[0]));
                    this.dataDeFim.setMês(Integer.parseInt(dataA[1]));
                    this.dataDeFim.setAno(Integer.parseInt(dataA[2]));
                    JOptionPane.showMessageDialog(null, "Percentagem atualizada com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Insira uma data válida!", "Inválido", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Esta tarefa já foi finalizada!", "Inválido", JOptionPane.ERROR_MESSAGE);
        }
        frameDisplay.setVisible(true);
        frameDisplayTarefa.dispose();
    }

    /**
     * Construtor das tarefas
     * @param diaIn dia de inicio da tarefa
     * @param mesIn mes de inicio da tarefa
     * @param anoIn ano de inicio da tarefa
     * @param duracao duracao da tarefa
     * @param percentagemConc percentagem de conclusão da tarefa
     */
    public Tarefa(int diaIn, int mesIn, int anoIn, double duracao, int percentagemConc){
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.percentagemConc= percentagemConc;
        this.dataDeFim = new Data(0,0,0);
    }

    /*public String toString(){
        return "Tarefa iniciada em" + dataInicio + "com uma duracao estimada de " + duracao + "meses e uma percentagem de conclusao de " +  percentagemConc ;
    }*/
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

        if (this.dataInicio.getAno()>testano)
            return 2;
        if ((testano==this.dataInicio.getAno()) && (this.dataInicio.getMês()>testmes))
            return 2;
        if ((testano==this.dataInicio.getAno()) && (this.dataInicio.getMês()==testmes) && (this.dataInicio.getMês()>testdia))
            return 2;

        return 1;
    }
}
