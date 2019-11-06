package teste;

import ia.Neuronio;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author luis felipe
 */
public class TestaLista {

    public static void main(String[] args) {
        List<List<Neuronio>> lista = new ArrayList<>(3);
        List<Neuronio> l = null;
        l = new ArrayList<>();
        int qtdW = 3, qtdNIn = 3, qtdNOut = 1, qtdC = 6;
        for (int i = 0; i < qtdNIn; i++) {
            l.add(new Neuronio(qtdW));
        }
        lista.add(l);
        l = null;

        for (int i = 0; i < qtdC - 2; i++) {
            l = new ArrayList<>();
            for (int j = 0; j < qtdNIn; j++) {
                l.add(new Neuronio(qtdNIn));
            }
            lista.add(l);
        }
        l = null;

        l = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            l.add(new Neuronio(qtdW));
        }
        lista.add(l);
        int n = 1, ly = 1;
        for (List<Neuronio> layer : lista) {
            for (Neuronio neuronio : layer) {
                System.out.println("L: " + ly + " N: " + n + " " + neuronio.toString());
                n++;
            }
            ly++;
            n = 0;
            System.out.println("");
        }
    }
}
