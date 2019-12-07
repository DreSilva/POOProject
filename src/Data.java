public class Data {
    protected int dia, mês, ano;

    protected Data(int dia, int mês, int ano) {
        this.ano = ano;
        this.dia = dia;
        this.mês = mês;
    }

    public int getDia() {
        return dia;
    }

    public int getMês() {
        return mês;
    }

    public int getAno() {
        return ano;
    }

    public String toString() {
        return dia + "/" + mês + "/" + ano;
    }
}
