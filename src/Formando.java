import java.io.Serializable;

public class Formando extends Bolseiros implements Serializable {

    public Formando(String nome, String email, int diaInBolsa, int mesInBolsa, int anoInBolsa, int  diaFimBolsa, int mesFimBolsa, int anoFimBolsa){
        super(nome, email, diaInBolsa, mesInBolsa, anoInBolsa, diaFimBolsa, mesFimBolsa, anoFimBolsa);
    }

}
