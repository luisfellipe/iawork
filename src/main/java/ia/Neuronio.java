package ia;

/**
 *
 * @author luis
 */
public class Neuronio{

    private int qtdPesos;
    private double  bias = 1;
    private double[] pesos;

    public Neuronio(int qtdPesos) {
        this.qtdPesos = qtdPesos + 1;
        pesos = new double[qtdPesos];
        for (int x = 0; x < qtdPesos; x++) {
            pesos[x] = 0.001111111;
        }
    }

    public Neuronio(int qtdPesos, double pesoInicial) {
        this.qtdPesos = qtdPesos;
        pesos = new double[qtdPesos];
        for (int x = 0; x < qtdPesos; x++) {
            pesos[x] = pesoInicial;
        }
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
        this.setPesos(w);
    }
    /*
     * recebe como entrada a saida de outros neuronios ou a entrada da camada sensorial
     * retorna yij sinal de ativação é a saida do neuronio i na camada j 
     * para os neuronios da camada j+1 
     */
    public double sinal(double entrada[]) {
        double u = 0;
        for (int i = 0; i < getQtdPesos(); i++) {
            u += pesos[i] * entrada[i];
        }
        u = u - pesos[0]*bias;
        return new Function().activation(u);
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < getQtdPesos(); x++) {
            sb.append("w[").append(pesos[x]).append("]\t");

        }
        return sb.toString();
    }
    
}