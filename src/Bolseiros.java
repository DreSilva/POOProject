public class Bolseiros extends Pessoa{
    protected Data dataDeInicioBolsa, dataDeFimBolsa;

    public Bolseiros(String nome, String email, int diaInBolsa, int mesInBolsa, int anoInBolsa, int  diaFimBolsa, int mesFimBolsa, int anoFimBolsa){
        super(nome, email);
        this.dataDeInicioBolsa = new Data(diaInBolsa, mesInBolsa, anoInBolsa);
        this.dataDeFimBolsa = new Data(diaFimBolsa, mesFimBolsa, anoFimBolsa);
    }

}
