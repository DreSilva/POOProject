public class Docentes extends Pessoa {
    protected int numMecano;
    protected String areaInv;

    public Docentes(String nome, String email, int numMecano, String areaInv){
        super(nome,email);
        this.numMecano = numMecano;
        this.areaInv = areaInv;
    }
}
