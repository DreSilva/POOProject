public class Doutorado extends Bolseiros {

    public int custo(){
        return 1000;
    }
    public int isDoutorado(){
        return 1;
    }

    /*public static int custo(int diaInBolsa, int mesInBolsa, int anoInBolsa, int  diaFimBolsa, int mesFimBolsa, int anoFimBolsa){
        int custoPorMes=1000;
        int custoFinal;
        int dia1=diaInBolsa, mes1=mesInBolsa, ano1=anoInBolsa, dia2=diaFimBolsa, mes2=mesFimBolsa, ano2=anoFimBolsa;
        int difdias, difmeses, difanos;
        if(dia2 < dia1)
        {
            // meses de fevereiro
            if (mes2 == 3)
            {
                //  verifica se é ano bisseixto
                if ((ano2 % 4 == 0 && ano2 % 100 != 0) || (ano2 % 400 == 0))
                {
                    dia2 += 29;
                }
                else
                {
                    dia2 += 28;
                }
            }
            // Abril ou Junho ou Setembro ou Novembro
            else if (mes2 == 5 || mes2 == 7 || mes2 == 10 || mes2 == 12)
            {
                dia2 += 30;
            }
            // Janeiro ou Março ou Maio ou Julho ou Agosto ou Outubro ou Dezembro
            else
            {
                dia2 += 31;
            }
            mes2 = mes2 - 1;
        }
        if (mes2 < mes1)
        {
            mes2 += 12;
            ano2 -= 1;
        }
        difdias = dia2 - dia1+1;
        difmeses = mes2 - mes1;
        difanos = ano2 - ano1;

        custoFinal=((difdias*31/custoPorMes)*custoPorMes)+(difmeses*custoPorMes)+(difanos*12*custoPorMes);
        return custoFinal;
    }*/

    public Doutorado(String nome, String email, int diaInBolsa, int mesInBolsa, int anoInBolsa, int  diaFimBolsa, int mesFimBolsa, int anoFimBolsa){
        super(nome, email, diaInBolsa, mesInBolsa, anoInBolsa, diaFimBolsa, mesFimBolsa, anoFimBolsa);
    }

}
