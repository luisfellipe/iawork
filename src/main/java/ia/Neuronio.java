package ia;

/**
 *
 * @author luis
 */
public class Neuronio {

    private int qtdPesos, potencial;
    private double[] pesos;

    public Neuronio(int qtdPesos) {
        this.qtdPesos = qtdPesos+1;
        pesos = new double[qtdPesos];
        for (int x = 0; x < qtdPesos; x++) {
            pesos[x] = 0.001111111;
        }
    }
    
    public Neuronio(int qtdPesos, int pesoInicial) {
        this.qtdPesos = qtdPesos;
        pesos = new double[qtdPesos];
        for (int x = 0; x < qtdPesos; x++) {
            pesos[x] = pesoInicial;
        }
    }

    public int getPotencial() {
        return potencial;
    }

    /**
     * @param potencial the potencial to set
     */
    public void setPotencial(int potencial) {
        this.potencial = potencial;
    }

    /**
     * @return the bias
     */
    public double getBias() {
        return pesos[0];
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
        this.pesos = w;
    }

    public double setInput(double entrada[]) {
        double u = 0;
        for (int i = 0; i < qtdPesos; i++) {
            u += pesos[i] * entrada[i] - pesos[0];
        }
        return new Function().activation(u);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < qtdPesos; x++) {
            sb.append("w[").append(pesos[x]).append("]\t");

        }
        return sb.toString();
    }
}