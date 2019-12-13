import java.io.Serializable;

public class Design extends Tarefa implements Serializable {

    /*public static double taxaDeEsforco(){

    }*/
    public double getTaxaDeEsforco(){
        return 0.5;
    }

    public Design(int diaIn, int mesIn, int anoIn, double duracao, int percentagemConc){
        super(diaIn, mesIn, anoIn, duracao, percentagemConc);
    }

}
