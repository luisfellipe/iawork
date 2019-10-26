package ia;

import java.io.File;

/**
 *
 * @author luis
 */
public class RNA {

    private Neuronio[] camadaIn, camadaOut;
    private Neuronio[][] camadaHidden;
    private int qtdNeuroIn, qtdNeuroOut, qtdNeuroHidden, qtdCamadas, qtdPesos;
    private double taxaAprend;

    public RNA(int qtdCamadas, int qtdNeuroIn, int qtdNeuroOut, int qtdNeuroHidden, int qtdPesos, double taxaAprend) {
        this.qtdNeuroIn = qtdNeuroIn;
        this.qtdNeuroOut = qtdNeuroOut;
        this.qtdNeuroHidden = qtdNeuroHidden;
        this.qtdCamadas = qtdCamadas;
        this.taxaAprend = taxaAprend;
        this.qtdPesos = qtdPesos;

        camadaIn = new Neuronio[qtdNeuroIn];
        camadaOut = new Neuronio[qtdNeuroOut];
        camadaHidden = new Neuronio[qtdCamadas - 2][qtdNeuroHidden];
        for (int i = 0; i < qtdNeuroIn; i++) {
            camadaIn[i] = new Neuronio(qtdPesos);
        }
        for (int i = 0; i < qtdCamadas - 2; i++) {
            for (int j = 0; j < qtdNeuroHidden; j++) {
                camadaHidden[i][j] = new Neuronio(qtdNeuroHidden);
            }
        }
        for (int i = 0; i < qtdNeuroOut; i++) {
            camadaOut[i] = new Neuronio(qtdNeuroHidden);
        }
    }

    /*
        
     */
    public void backPropagation(double[] amostra, int saida, int y) {
        double[] W = null;
        for (int i = 0; i < qtdNeuroOut; i++) {
            W = camadaOut[i].getPesos();
            W[0] = W[0] + (saida * taxaAprend);// bias
            for (int j = 0; j < qtdPesos; j++) {
                W[j] = W[j] + (taxaAprend * (saida - y) * amostra[j]);
            }
        }
        double[] W2 = null;
        for (int i = qtdCamadas - 2; i > 0; i++) {
            for (int j = 0; j < qtdNeuroHidden; j++) {
                W2 = camadaHidden[i][j].getPesos();
                W2[0] = W2[0] + (saida * taxaAprend);// bias
                for (int k = 0; k < qtdPesos; k++) {
                    W2[j] = W2[j] + (taxaAprend * (saida - y) * W[j]);
                }
                W = W2.clone();
            }
        }
        for (int i = 0; i < qtdNeuroIn; i++) {
            W = camadaIn[i].getPesos();
            W[0] = W[0] + (saida * taxaAprend);// bias
            for (int j = 1; j < qtdPesos; j++) {
                W[j] = W[j] + (taxaAprend * (saida - y) * camadaHidden[0][i].getPesos()[j]);
            }
        }
    }

    public int setInput(double entrada[]) {

        double[] W = new double[qtdNeuroIn];
        for (int i = 0; i < qtdNeuroIn; i++) {
            W[i] = camadaIn[i].sinal(entrada);
        }
        double[] W2 = new double[qtdNeuroHidden];
        for (int i = 0; i < qtdCamadas - 2; i++) {
            for (int j = 0; j < qtdNeuroHidden; j++) {
                W2[j] = camadaHidden[i][j].sinal(W);
            }
            if (W.length < W2.length) {
                W = new double[qtdNeuroHidden];
            }
            W = W2.clone();
        }
        double u[] = new double[qtdNeuroOut];
        for (int i = 0; i < qtdNeuroOut; i++) {
            u[i] = camadaOut[i].sinal(W);
        }

        return 0;
    }

    public void printNet() {
        System.out.println("::::: Rede Neural Artificial (Peso de cada Neuronio):::::\n");
        for (int i = 0; i < qtdNeuroIn; i++) {
            System.out.println(camadaIn[i].toString());
        }
        System.out.println("");
        for (int i = 0; i < qtdCamadas - 2; i++) {
            for (int j = 0; j < qtdNeuroHidden; j++) {
                System.out.println(camadaHidden[i][j].toString());
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < qtdNeuroOut; i++) {
            System.out.println(camadaOut[i].toString());
        }
    }

    /*
     * Carrega Layes com pesos e bias já treinados
     */
    public void loadRNA(String path) {
        File file = new File(path);
    }

    public void saveRNA() {

    }

}
