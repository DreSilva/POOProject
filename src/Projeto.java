import java.util.ArrayList;

public class Projeto {
    protected String nome, acronimo;
    protected Data dataInicio, dataDeFim;
    protected int duracao;

    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>(24);
        ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>(24);
    }

    public static void novaPessoa(String nome, String email){
        Pessoa pe1 = new Pessoa("Tomás Ventura", "ventura.tfp@gmail.com");
        Pessoa pe2 = new Pessoa("André Silva", "2000.andre.silva@gmail.com");
    }

    public Projeto(String nome, String acronimo, int diaIn, int mesIn, int anoIn, int duracao) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;

    }
}
