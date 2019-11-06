package dados;

/**
 *
 * @author luis felipe
 */
public class Input {

    private double in[] = null;
    private double saida = 0; // saida deseja para entrada in
    private int sizeData;

    public Input() {
    }

    public Input(double in[]) {
        this.in = in.clone();
        sizeData = in.length - 1;
        saida = in[sizeData];
        sizeData = sizeData - 1;
        /*
         remove o dado de classificacao da amostra 
         considera que essa dado esta no ultimo endereco do vetor
         */
        double[] swp = new double[sizeData];
        for (int i = 0; i < sizeData; i++) {
            swp[i] = in[i];
        }
        in = swp;
        normalizar();
    }

    public Input(double in[], int posSaida) {
        sizeData = in.length - 2;
        this.in = new double[sizeData];
        saida = in[posSaida];

        /*
         remove o dado de classificacao da amostra
         */
        for (int i = 0; i < sizeData; i++) {
            if (i != posSaida) {
                this.in[i] = in[i];
            }
        }
        normalizar();
    }

    public void setInput(double in, int pos) {
        if (pos <= sizeData) {
            this.in[pos] = in;
        }
    }

    public double getInput(int pos) {
        if (pos <= sizeData) {
            return in[pos];
        }
        return -0.01;
    }

    public void setInputs(double in[]) {
        this.in = in.clone();
        sizeData = in.length - 2;
        saida = in[sizeData + 1];

        double[] swp = new double[sizeData];
        for (int i = 0; i < sizeData; i++) {
            swp[i] = in[i];
        }
        in = swp;
        normalizar();
    }

    public double[] getInputs() {
        return in;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public double getSaida() {
        return in[sizeData];
    }

    public int getSizeData() {
        return in.length - 1;
    }

    private void normalizar_2() {
        int[] Xmin = {0, 18, 1, 1, 1}, Xmax = {6, 98, 4, 5, 4};
        int d1 = 0, d2 = 1;
        for (int i = 0; i < in.length - 1; i++) {
            in[i] = ((((in[i] - Xmin[i]) * (d2 - d1)) / (Xmax[i] - Xmin[i])) + d1);
        }
    }

    private void normalizar() {
        int[] Xmax = {6, 98, 4, 5, 4};
        for (int i = 0; i < in.length - 1; i++) {
            in[i] = in[i] / Xmax[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < sizeData; i++) {
            sb.append(in[i]).append("\t\t");
        }
        sb.append(in[i]).append("\t\tSaida: ").append(saida);
        return sb.toString();
    }

}
