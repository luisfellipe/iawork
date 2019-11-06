package teste;

import dados.Input;
import dados.LoadFile;
import ia.RNA;
import java.util.List;
//import ia.TreinaRNA;

/**
 *
 * @author luis
 */
public class TestaRNA {

    public static void main(String[] args) {
        int qtdNeuroIn = 3,
                qtdNeuroOut = 1,
                qtdCamadas = 3,
                qtdPesos = 3;
        double taxa = 0.1;
        RNA rna = new RNA(qtdCamadas, qtdNeuroIn, qtdNeuroOut, qtdPesos, taxa);
        rna.printNet();

        LoadFile lf = new LoadFile();
        String path = "src/main/java/dados/amostras.data";

        List<Input> amostras = lf.load(path, 288);
        for (Input input : amostras) {
            rna.setInput(input.getInputs());
        }

    }

}
