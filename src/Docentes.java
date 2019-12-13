import java.io.Serializable;

public class Docentes extends Pessoa implements Serializable {
    protected int numMecano;
    protected String areaInv;

    public int custo(){
        return 0;
    }
    public int isDocente(){
        return 1;
    }

    public Docentes(String nome, String email, int numMecano, String areaInv){
        super(nome,email);
        this.numMecano = numMecano;
        this.areaInv = areaInv;
    }
}
