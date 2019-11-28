public class Tarefa {
    protected Data dataInicio, dataDeFim;
    protected int duracao, percentagemConc;

    public Tarefa(int diaIn, int mesIn, int anoIn, int duracao, int percentagemConc){
        this.dataInicio = new Data(diaIn, mesIn, anoIn);
        this.duracao = duracao;
        this.percentagemConc= percentagemConc;
    }

}
