public class Data {
    private int dia, mês, ano;

    public Data(int dia, int mês, int ano) {
        this.ano = ano;
        this.dia = dia;
        this.mês = mês;
    }

    public String toString() {
        return dia + "/" + mês + "/" + ano;
    }
}
