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
    protected int duracao, percentagemConc;
    protected JFrame frameDisplay,frameTarefas;

    public void ListarTarefas(JFrame frame){
        this.frameDisplay=frame;
        new ListaTarefas();
    }

    public class ListaTarefas extends JFrame{

    }

    /*public static void atualizarTarefas(String nome, String email, int diaFim, int mesFim, int anoFim, int percentagemConc){

    }*/

    public Tarefa(int diaIn, int mesIn, int anoIn, int duracao, int percentagemConc){
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.percentagemConc= percentagemConc;
    }

    /*public String toString(){
        return "Tarefa iniciada em" + dataInicio + "com uma duracao estimada de " + duracao + "meses e uma percentagem de conclusao de " +  percentagemConc ;
    }*/

}
