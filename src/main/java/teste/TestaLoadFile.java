package teste;

import dados.Input;
import dados.LoadFile;

/**
 *
 * @author luis
 */
public class TestaLoadFile {

    public static void main(String[] args) {
        LoadFile lf = new LoadFile();
        String path = "src/main/java/dados/desvio.data";
        double[] normal = {5, 30, 3, 4, 8, 0}; //paramtros ? que faltam
        Input[] in = lf.load(path, 961, 6, normal);

        for (int i = 0; i < in.length; i++) {
            System.out.println(in[i].toString() + "\t" + i);
        }

    }
}
