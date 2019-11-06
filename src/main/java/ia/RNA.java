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
    private int qNeuronsIn, qNeuronsOut, qNeuronsHidden, qLayers, qtdW;
    private double txOfLearn;

    public RNA(int qLayers, int qNeuronsIn, int qNeuronsOut, int qNeuronsHidden,
            int qtdW, double txOfLearn) {

        this.qNeuronsIn = qNeuronsIn;
        this.qNeuronsOut = qNeuronsOut;
        this.qNeuronsHidden = qNeuronsHidden;
        this.qLayers = qLayers;
        this.txOfLearn = txOfLearn;
        this.qtdW = qtdW;

        MLP = new ArrayList<>();//cria camada de entrada

        List<Neuronio> l = new ArrayList<>();
        for (int i = 0; i < qNeuronsIn; i++) {
            l.add(new Neuronio(qtdW));
        }
        MLP.add(l);//adiciona primeira camada

        l = null;
        //cria camadas ocultas se tiver considera-se a primeira camada como uma camada oculta
        for (int i = 0; i < qLayers - 2; i++) {
            l = new ArrayList<>();
            for (int j = 0; j < qNeuronsHidden; j++) {
                l.add(new Neuronio(qtdW));
            }
            MLP.add(l);
        }

        //cria camada de saida
        l = null;
        l = new ArrayList<>();
        for (int i = 0; i < qNeuronsOut; i++) {
            l.add(new Neuronio(qtdW));
        }
        MLP.add(l);
    }

    /**
     * Algoritmo backpropagation pega o resultado na camada de saida e az o
     * percurso inverso da classificação ajustando os pesos.
     *
     * @param Input entrada: tem os dados para classificar e tambem a resposta
     * @amostra[saida]: possui a saida desejada, não faz parte do processo da
     * rede
     * @saida: posicao da saida desejada
     * @y: saida obtida na RNA
     * @amostra[]: parametros para classificação
     * @saida: saida esperada
     */
    public void backPropagation(Input entrada) {
        /**
         * @gd: Gradiente Descendente
         * @y: saida do neuronio
         * @saidaEsperada: resultado esperado da entrada
         * @gdSoma = Soma[gd(j) * w(ij)]
         */
        double[] amostra = entrada.getInputs();
        double saidaEsperada = entrada.getSaida();
        double[] W = null; // vetor de pesos
        double gd = 0, y = 0.0, gdSoma = 0.0;

        boolean isOut = true;
        Collections.reverse(MLP);// inverte ordem da MLP para começar da camada de saida
        for (List<Neuronio> layer : MLP) {
            for (Neuronio neuronio : layer) {
                y = neuronio.getLastOutput();//ultimo sinal do neuronio
                if (!isOut) {
                    gd = y * (1 - y) * gdSoma;
                } else {
                    gd = y * (1 - y) * (saidaEsperada - y);
                    isOut = false;
                }
                neuronio.setGD(gd);//gradiente local do neuronio
                W = neuronio.getPesos();
                for (int nw = 0; nw < qtdW; nw++) {
                    W[nw] = W[nw] + (txOfLearn * y * gd);
                    gdSoma += gd * W[nw];
                }
            }
            gdSoma = 0.0;
        }
        Collections.reverse(MLP);//volta MLP para sua ordem natural
    }

    public double setInput(double entrada[]) {
        double S[] = null, u = 0.0;
        int posicao = 0;
        for (List<Neuronio> layer : MLP) {
            S = new double[layer.size()];
            for (Neuronio neuronio : layer) {
                S[posicao] = neuronio.sinal(entrada);
            }
            posicao = 0;
            entrada = S.clone();
        }
        for (double d : S) {
            u = u + d;
        }

        return u;
    }

    public List<List<Neuronio>> getLayers() {
        return MLP;
    }

    public void printNet() {
        System.out.println("::::: Rede Neural Artificial (Peso de cada Neuronio):::::\n");
        for (List<Neuronio> layer : MLP) {
            for (Neuronio neuronio : layer) {
                System.out.println(neuronio.toString());

            }
        }
    }
}
