import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;

public class Tarefa {
    protected Data dataInicio, dataDeFim;
    protected int percentagemConc;
    protected double duracao;
    protected JFrame frameDisplay,frameTarefas,frameDisplayTarefa;
    protected Pessoa pessoaReponsavel;
    public String DataHoje;

    /*public static void atualizarTarefas(String nome, String email, int diaFim, int mesFim, int anoFim, int percentagemConc){

    }*/

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
                String value = JOptionPane.showInputDialog(null, "Introduza a percentagem atualizada", "Input", JOptionPane.QUESTION_MESSAGE);
                int novaPerc =0;
                try{
                    novaPerc = Integer.parseInt(value);

                    if (novaPerc>100){
                        JOptionPane.showMessageDialog(null, "Tem de inserir uma percentagem válida", "Inválido", JOptionPane.ERROR_MESSAGE);
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Tem de inserir uma percentagem válida!", "Inválido", JOptionPane.ERROR_MESSAGE);
                }
                setPercentagemConc(novaPerc);
                JOptionPane.showMessageDialog(null, "Percentagem atualizada com sucesso!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                frameDisplayTarefa.dispose();
                frameDisplay.setVisible(true);
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

    public void setPercentagemConc(int percentagemConc) {
        this.percentagemConc = percentagemConc;
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
    }

    /*public String toString(){
        return "Tarefa iniciada em" + dataInicio + "com uma duracao estimada de " + duracao + "meses e uma percentagem de conclusao de " +  percentagemConc ;
    }*/

}
