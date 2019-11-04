package dados;

/**
 *
 * @author luis felipe
 */
public class Input {

    private double in[] = null;
    private double saida = 0; // saida deseja para entrada in
    private int size;

    public Input() {
    }

    public Input(double in[]) {
        this.in = in.clone();
        size = in.length - 1;
        saida = in[size];
        size = size - 1;
        /*
         remove o dado de classificacao da amostra 
         considera que essa dado esta no ultimo endereco do vetor
         */
        double[] swp = new double[size];
        for (int i = 0; i < size; i++) {
            swp[i] = in[i];
        }
        in = swp;
    }

    public Input(double in[], int posSaida) {
        size = in.length - 2;
        this.in = new double[size];
        saida = in[posSaida];

        /*
         remove o dado de classificacao da amostra
         */
        for (int i = 0; i < size; i++) {
            if (i != posSaida) {
                this.in[i] = in[i];
            }
        }
    }

    public void setInput(double in, int pos) {
        if (pos <= size) {
            this.in[pos] = in;
        }
    }

    public double getInput(int pos) {
        if (pos <= size) {
            return in[pos];
        }
        return -0.01;
    }

    public void setInputs(double in[]) {
        this.in = in.clone();
    }

    public double[] getInputs() {
        return in;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public double getSaida() {
        return in[size];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < size; i++) {
            sb.append(in[i]).append("\t");
        }
        sb.append(in[i]).append("\tSaida: ").append(saida);
        return sb.toString();
    }

}
