public class Documentacao extends Tarefa {

    /*public static double taxaDeEsforco(){

    }*/
    public double getTaxaDeEsforco(){
        return 0.25;
    }

    public Documentacao(int diaIn, int mesIn, int anoIn, double duracao, int percentagemConc){
        super(diaIn, mesIn, anoIn, duracao, percentagemConc);
    }
}
