public class Desenvolvimento extends Tarefa {

    /*public static double taxaDeEsforco(){

    }*/
    public double getTaxaDeEsforco(){
        return 1;
    }

    public Desenvolvimento(int diaIn, int mesIn, int anoIn, double duracao, int percentagemConc){
        super(diaIn, mesIn, anoIn, duracao, percentagemConc);
    }

}
