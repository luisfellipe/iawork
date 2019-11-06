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
        saida = in[sizeData]; //extrai o ultimo parametro como saida desejada
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
        sizeData = in.length ;
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

    public double[] getInputs() {
        return in;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public double getSaida() {
        return saida;
    }

    public int getSizeData() {
        return in.length;
    }

    private void normalizar() {
        int[] Xmax = {6, 98, 4, 5, 4};
        for (int i = 0; i < sizeData; i++) {
            in[i] = in[i] / Xmax[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < sizeData-1; i++) {
            sb.append(in[i]).append("\t");
        }
        sb.append(in[i]).append("\t\tSaida: ").append(saida);
        return sb.toString();
    }

}
