package ia;

/**
 *
 * @author luis
 */
public class TreinaRNA {

    /*
     * entrada: amostras para treino
     * saida: resultados esperados para cada amostra
     * size: tamanho da amostra
     * sizeData:quantidade de dados de cada amostra
     * erro: margem de erro na classificação da amostra
     * serro: desvio padrão
     * rna: rede neural artificial
     * epoca: iterador
     */
    private int epoca, size, sizeData;
    private double erro, serro;
    RNA rna = null;
    Input[] entrada;
    int[] saida;

    public void setRNA(RNA rna) {
        this.rna = rna;
    }

    /*
     * recebe os dados para treino da rna
     */
    public void setData(Input[] entrada, int[] saida, int size, int sizeData) {
        this.entrada = entrada;
        this.saida = saida;
    }

    public void treinar() {
        erro = 0.0;
        double X[] = new double[sizeData];
        int y = 0;
        do {
            epoca = 0;
            for (int i = 0; i < size; i++) {
                y = rna.setInput(entrada[i].getInputs());
                if (y != saida[i]) {
                    rna.ajustar(entrada[i].getInputs(), saida[i], y);
                    erro = Math.pow((y - saida[i]), 2);
                }
                epoca++;
            }
        }while(erro < 0.15);
        
    }

}
