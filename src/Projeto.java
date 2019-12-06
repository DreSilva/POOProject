import java.util.ArrayList;

public class Projeto {
    protected String nome, acronimo;
    protected Data dataInicio, dataDeFim;
    protected int duracao;
    protected ArrayList<Pessoa> pessoas;
    protected ArrayList<Tarefa> tarefas;

    public static void novaPessoa(String nome, String email){
        Pessoa pe1 = new Pessoa("Tomás Ventura", "ventura.tfp@gmail.com");
        Pessoa pe2 = new Pessoa("André Silva", "2000.andre.silva@gmail.com");
    }

    public static void novaTarefa(int diaIn, int mesIn, int anoIn, int duracao, int percentagemConc ){
        Tarefa t1 = new Tarefa(2,12,2019, 3,0);
    }

    public Projeto(String nome, String acronimo, int diaIn, int mesIn, int anoIn, int duracao) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.dataDeFim = new Data(0,0,0);

    }
}
