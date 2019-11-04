package ia;

import java.util.Random;

/**
 *
 * @author luis
 */
public class Neuronio {

    /**
     * qtdPesos: bias: lastOutput: ultima saida de ativação do neuronio pesos:
     * gd: gradiente descendente
     */
    private int qtdPesos;
    private double bias = 1, lastOutput = 0, beta = 1, gd;
    private double[] pesos;

    public Neuronio(int qtdPesos) {
        this.qtdPesos = qtdPesos + 1;//mais um por causa do bias pesos[0]
        pesos = new double[this.qtdPesos];
        for (int x = 0; x < this.qtdPesos; x++) {
            pesos[x] = new Random().nextDouble();
        }
    }

    public Neuronio(int qtdPesos, double pesoInicial) {
        this.qtdPesos = qtdPesos + 1;;//mais um por causa do bias pesos[0]
        pesos = new double[qtdPesos];
        for (int x = 0; x < this.qtdPesos; x++) {
            pesos[x] = pesoInicial;
        }
    }

    /*
     * recebe como entrada a saida de outros neuronios ou a entrada da camada sensorial
     * retorna yij sinal de ativação é a saida do neuronio i na camada j 
     * para os neuronios da camada j+1 
     */
    public double sinal(double entrada[]) {
        double u = 0;
        for (int i = 1; i < this.qtdPesos; i++) {
            u += pesos[i] * entrada[i];
        }
        u = u - (pesos[0] * bias);
        lastOutput = new Function().setBeta(beta).activation(u);
        return lastOutput;
    }

    /**
     * @return the bias
     */
    public double getBias() {
        return bias;
    }

    /**
     * @return the pesos
     */
    public double[] getPesos() {
        return pesos;
    }

    /**
     * @param w the pesos to set
     */
    public void setPesos(double[] w) {
        pesos = w.clone();
    }

    /**
     * @return the qtdPesos
     */
    public int getQtdPesos() {
        return qtdPesos - 1;
    }

    /**
     * @param bias the bias to set
     */
    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getLastOutput() {
        return lastOutput;
    }

    public void setGD(double gd) {
        this.gd = gd;
    }

    public double getGD() {
        return gd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("biases: ").append(pesos[0] * bias).append("\t");
        for (int x = 1; x < this.qtdPesos; x++) {
            sb.append("w[").append(pesos[x]).append("]\t");

        }
        return sb.toString();
    }
}
