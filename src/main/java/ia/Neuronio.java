package ia;

import java.util.Random;

/**
 *
 * @author luis felipe
 */
public class Neuronio {

    /**
     * qtdPesos: bias: potencial: ultima saida de ativação do neuronio pesos:
     * gd: gradiente descendente
     */
    private int qtdPesos;
    private double bias = 1, potencial = 0, beta = 1, gd = 0.0;
    private double[] pesos;

    public Neuronio(int qtdPesos) {
        this.qtdPesos = qtdPesos + 1;//mais um por causa do bias pesos[0]
        pesos = new double[this.qtdPesos];
        for (int x = 0; x < this.qtdPesos; x++) {
            pesos[x] = new Random().nextDouble() / 100;
        }
    }

    /*
     * recebe como entrada a saida de outros neuronios ou a entrada da camada sensorial
     * retorna yij sinal de ativação é a saida do neuronio i na camada j 
     * para os neuronios da camada j+1 
     */
    public double sinal(double entrada[]) {
        double u = 0;
        for (int i = 1; i < qtdPesos; i++) {
            u += pesos[i] * entrada[i - 1];
        }
        u = u - (pesos[0] * bias);
        potencial = new Function().setBeta(beta).activation(u);
        return potencial;
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
        return qtdPesos;
    }

    /**
     * @param bias the bias to set
     */
    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getLastOutput() {
        return potencial;
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
        sb.append("bias: ").append(pesos[0] * bias).append("\t\t");
        for (int x = 1; x < this.qtdPesos; x++) {
            sb.append("w[").append(pesos[x]).append("]\t\t");

        }
        return sb.toString();
    }
}
