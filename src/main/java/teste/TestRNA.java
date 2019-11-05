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
public class TestRNA {

    public static void main(String[] args) {
        int qtdNeuroIn = 2,
                qtdNeuroOut = 1,
                qtdNeuroHidden = 2,
                qtdCamadas = 3,
                qtdPesos = 3;
        double taxa = 0.1;
        RNA rna = new RNA(qtdCamadas, qtdNeuroIn, qtdNeuroOut, qtdNeuroHidden, qtdPesos, taxa);
        rna.printNet();

        LoadFile lf = new LoadFile();
        String path = "src/main/java/dados/amostras.data";
       
        List<Input> amostra = lf.load(path, 399, 6);
      
     
    }

}
