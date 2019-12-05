public class Tarefa {
    protected Data dataInicio, dataDeFim;
    protected int duracao, percentagemConc;

    public static void atualizarTarefas(String nome, String email, int diaFim, int mesFim, int anoFim, int percentagemConc){

    }

    public Tarefa(int diaIn, int mesIn, int anoIn, int duracao, int percentagemConc){
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.percentagemConc= percentagemConc;
    }

    public String toString(){
        return "Tarefa iniciada em" + dataInicio + "com uma duracao estimada de " + duracao + "meses e uma percentagem de conclusao de " +  percentagemConc ;
    }

}
