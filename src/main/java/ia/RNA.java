package ia;

import dados.Input;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luis felipe
 */
public class RNA {

    private List<List<Neuronio>> MLP;
    private int qLayers, qtdW;
    private double txOfLearn;

    public RNA(int qLayers, int qnIn, int qnOut, int qtdW, double txOfLearn) {

        this.qLayers = qLayers;
        this.txOfLearn = txOfLearn;
        this.qtdW = qtdW;

        MLP = new ArrayList<>();//cria camada de entrada

        List<Neuronio> l = new ArrayList<>();
        for (int i = 0; i < qnIn; i++) {
            l.add(new Neuronio(qtdW));
        }
        MLP.add(l);//adiciona primeira camada
        l = null;
        //cria camadas ocultas se tiver considera-se a primeira camada como uma camada oculta
        for (int i = 0; i < qLayers - 2; i++) {
            l = new ArrayList<>();
            for (int j = 0; j < qnOut * 2; j++) {
                l.add(new Neuronio(qnIn));
            }
            MLP.add(l);
            l = null;
        }

        //cria camada de saida
        l = new ArrayList<>();
        for (int i = 0; i < qnOut; i++) {
            l.add(new Neuronio(qnIn));
        }
        MLP.add(l);
    }//fim do metodo construtor

    /**
     * Algoritmo backpropagation pega o resultado na camada de saida e az o
     * percurso inverso da classificação ajustando os pesos.
     *
     * @param Input entrada: tem os dados para classificar e tambem a resposta
     * esperada
     */
    public void backPropagation(double saidaEsperada) {
        /**
         * @gd: Gradiente Descendente
         * @y: saida do neuronio
         * @saidaEsperada: resultado esperado da entrada
         * @gdSoma = Soma[gd(j) * w(ij)], w(ij) pesos do neuronio j
         */

        double[] W = null; // vetor de pesos
        double gd = 0, gdSoma = 0, y = 0.0;
        boolean isOut = true;

        int layer = 1, neuronio, peso;
        for (layer = MLP.size(); layer > 0; layer--) {
            for (neuronio = 0; neuronio < 10; neuronio++) {
                y = MLP.get(layer).get(neuronio).getLastOutput();
                if (isOut) {
                    gdSoma += y * (1 - y) * MLP.get(layer - 1).get(neuronio).getGD();
                } else {
                    gd = y * (1 - y) * (saidaEsperada - y);
                    MLP.get(layer).get(neuronio).setGD(gd);//guarda gradiente local do neuronio
                }
                W = MLP.get(layer).get(neuronio).getPesos();
                for (peso = 0; peso < MLP.get(layer).get(neuronio).getQtdPesos(); peso++) {
                    W[peso] = W[peso] + (txOfLearn * y * gd);
                }
            }
            isOut = false;//sai da camada de output
        }
    }

    public double setInput(double entrada[]) {
        double S[] = null, u = 0.0;
        int posicao = 0;
        for (List<Neuronio> layer : MLP) {
            S = null;
            S = new double[layer.size()];
            for (Neuronio neuronio : layer) {
                S[posicao] = neuronio.sinal(entrada);
                posicao++;
            }
            posicao = 0;
            entrada = S.clone();
        }
        for (double d : S) {
            u = u + d;
        }

        if (u < 0.2000) {
            return 0;
        } else if (u > 0.8000) {
            return 1;
        }

        return u;
    }

    public List<List<Neuronio>> getLayers() {
        return MLP;
    }

    public void printNet() {
        System.out.println("\n::::: Rede Neural Artificial (Peso de cada Neuronio):::::\n");
        for (List<Neuronio> layer : MLP) {
            for (Neuronio neuronio : layer) {
                System.out.println(neuronio.toString());

            }
            System.out.println("\n");
        }

    }
}
