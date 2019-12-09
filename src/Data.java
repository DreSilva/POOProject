public class Data {
    private int dia, mês, ano;

    public Data(int dia, int mês, int ano) {
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

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMês(int mês) {
        this.mês = mês;
    }

    public String toString() {
        return dia + "/" + mês + "/" + ano;
    }
}
