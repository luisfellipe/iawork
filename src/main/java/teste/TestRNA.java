package teste;

import ia.RNA;
//import ia.TreinaRNA;

/**
 *
 * @author luis
 */
public class TestRNA {

    public static void main(String[] args) {
        int qtdNeuroIn = 2,
                qtdNeuroOut = 2,
                qtdNeuroHidden = 3,
                qtdCamadas = 3,
                qtdPesos = 3;
        double taxa = 0.1;
        RNA rna = new RNA(qtdCamadas, qtdNeuroIn, qtdNeuroOut, qtdNeuroHidden, qtdPesos, taxa);
        rna.printNet();
        /*
        double[] amostra = {1, 3, 6, 8, 7, 10, 12, 15, 17,53,124,527,1,90,867,34,2,18,453,56};
        double[] saida =   {1, 1, 0, 0, 1, 0, 0, 1, 1,1,0,1,1,0,1,0,0,0,1,0};
        new TreinaRNA();
        double[] entrada = {1, 5, 6, 0, 9, 23, 56, 67, 45, 67};
        int resposta = rna.setInput(saida);
        if (resposta == 1) {
            System.out.println("PAR");
        } else {
            System.out.println("IMPAR");
        }
         */
    }

}
