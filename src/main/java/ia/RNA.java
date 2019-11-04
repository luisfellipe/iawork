package ia;

import dados.Input;

/**
 *
 * @author luis felipe
 */
public class RNA {

    private Neuronio[] layerIn, layerOut;
    private Neuronio[][] layerHidden;
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

        layerIn = new Neuronio[qNeuronsIn];
        layerOut = new Neuronio[qNeuronsOut];
        //qtdCamadas - 2: subtrai duas camadas levando em conta a camada de input e de output
        layerHidden = new Neuronio[qLayers - 2][qNeuronsHidden];
        for (int i = 0; i < qNeuronsIn; i++) {
            layerIn[i] = new Neuronio(qtdW);
        }
        for (int i = 0; i < qLayers - 2; i++) {
            for (int j = 0; j < qNeuronsHidden; j++) {
                layerHidden[i][j] = new Neuronio(qNeuronsHidden);
            }
        }
        for (int i = 0; i < qNeuronsOut; i++) {
            layerOut[i] = new Neuronio(qNeuronsHidden);
        }
    }

    /**
     * Algoritmo backpropagation pega o resultado na camada de saida e az o percurso inverso
     * da classificação ajustando os pesos.
     * @param Input entrada: tem os dados para classificar e tambem a resposta
     * @amostra[saida]: possui a saida desejada, não faz parte do processo da rede
     * @saida: posicao da saida desejada
     * @y: saida obtida na RNA
     * @amostra[]: amostra para classificação
     * @saida: saida esperada
     */
    
    public void backPropagation(Input entrada) {
        double[] amostra = entrada.getInputs();
        double saida = entrada.getSaida();
        double[] W = null; // vetor de pesos
        /**
         * @gd: Gradiente Descendente
         * @y: saida do neuronio
         * @gwSoma: soma do gd(j) x peso(j) dos neuronios da camada de saida
         */
        double gd = 0.0, y = 0.0, gwSoma = 0;

        //loop de iteração dos neuronios da camada de saida
        for (int i = 0; i < getqNeuronsOut(); i++) {
            W = layerOut[i].getPesos(); //pesos do neuronio i da camada de saida
            y = layerOut[i].getLastOutput(); //ultimo sinal do neuronio i da camada de saida
            gd = y * (1 - y) * (saida - y); //calcula gradiente local de saida
            //loop de iteração nos pesos do neuronio i
            for (int j = 0; j < qtdW; j++) {
                W[j] = W[j] + txOfLearn * y * gd; //ajuste dos pesos dos neuronios
                gwSoma += gd * W[j];
            }
        }

        double W2[] = null, gwSoma2 = 0.0; //gwSoma2: gradiente da camada oculta
        for (int i = getqLayers() - 2; i > 0; i++) {//itera sobre a camada
            for (int j = 0; j < getqNeuronsHidden(); j++) { // itera sobre os neuronios da camada
                W2 = layerHidden[i][j].getPesos();
                y = layerHidden[i][j].getLastOutput();
                gd = y * (1 - y) * gwSoma;
                for (int k = 1; k < qtdW; k++) {//itera sobre os pesos dos neuronios
                    W2[k] = W2[k] + (txOfLearn * y * gd); // calcula gradiente dos neuronios internos 
                    gwSoma2 += gwSoma * W2[k];
                }
                W = W2.clone();
            }
            gwSoma = gwSoma2;
        }
        for (int i = 0; i < getqNeuronsIn(); i++) {
            W = layerIn[i].getPesos();
            y = layerIn[i].getLastOutput();
            gd = y * (1 - y) * gwSoma;
            for (int j = 1; j < qtdW; j++) {
                W[j] = W[j] + (txOfLearn * y * gd);
            }
        }
    }

    public double setInput(double entrada[]) {

        double[] W = new double[getqNeuronsIn()];
        for (int i = 0; i < getqNeuronsIn(); i++) {
            W[i] = layerIn[i].sinal(entrada);
        }
        double[] W2 = new double[getqNeuronsHidden()];
        //qtdCamadas - 2: descontado as camadas de entrada e saida
        for (int i = 0; i < getqLayers() - 2; i++) {
            for (int j = 0; j < getqNeuronsHidden(); j++) {
                W2[j] = layerHidden[i][j].sinal(W);
            }
            if (W.length < W2.length) {
                W = new double[getqNeuronsHidden()];
            }
            W = W2.clone();
        }
        double u[] = new double[getqNeuronsOut()];
        for (int i = 0; i < getqNeuronsOut(); i++) {
            u[i] = layerOut[i].sinal(W);
        }

        return 0;
    }

    public Neuronio[][] getLayerHidden() {
        return layerHidden;
    }

    public Neuronio[] getLayerInput() {
        return layerIn;
    }

    public Neuronio[] getLayerOutput() {
        return layerOut;
    }

    public void printNet() {
        System.out.println("::::: Rede Neural Artificial (Peso de cada Neuronio):::::\n");
        for (int i = 0; i < getqNeuronsIn(); i++) {
            System.out.println(layerIn[i].toString());
        }
        System.out.println("");
        for (int i = 0; i < getqLayers() - 2; i++) {
            for (int j = 0; j < getqNeuronsHidden(); j++) {
                System.out.println(layerHidden[i][j].toString());
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < getqNeuronsOut(); i++) {
            System.out.println(layerOut[i].toString());
        }
    }

    /**
     * @return the qNeuronsIn
     */
    public int getqNeuronsIn() {
        return qNeuronsIn;
    }

    /**
     * @return the qNeuronsOut
     */
    public int getqNeuronsOut() {
        return qNeuronsOut;
    }

    /**
     * @return the qNeuronsHidden
     */
    public int getqNeuronsHidden() {
        return qNeuronsHidden;
    }

    /**
     * @return the qLayers
     */
    public int getqLayers() {
        return qLayers;
    }
}
