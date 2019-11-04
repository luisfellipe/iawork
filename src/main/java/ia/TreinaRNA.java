package ia;

import dados.Input;
import java.util.Random;

/**
 *
 * @author luis
 */
public class TreinaRNA {

    /**
     * @entrada: amostras para treino
     * @saida: resultados esperados para cada amostra
     * @entradaSize: tamanho de cada amostra
     * @amostraSize:quantidade de dados de cada amostra
     * @erro: margem de erro na classificação da amostra
     * @serro: desvio padrão
     * @rna: rede neural artificial
     * @epoca: iterador
     */
    private int epoca, entradaSize, amostraSize, maxEpoca = 1000;
    private double erro, maxErro;
    RNA rna = null;
    private Input[] entrada;

    public void setRNA(RNA rna) {
        this.rna = rna;
    }

    public void setMaxErro(double maxErro) {
        this.maxErro = maxErro;
    }

    /**
     * recebe os dados para treino da rna
     */
    public void setData(Input[] entrada, int entradaSize, int amostraSize) {
        this.entrada = entrada;
        this.amostraSize = amostraSize;
        this.entradaSize = entradaSize;
    }

    public void treinar() {
        erro = 0.0;
        double y = 0.0; //resultado da classificacao de uma amostra
        do {
            epoca = 0;
            for (int i = 0; i < amostraSize; i++) {
                int r = new Random().nextInt() * entradaSize; // escolhe indice de uma amostra aleatoria
                y = rna.setInput(entrada[r].getInputs()); // rna processa amostra
                /**
                 * se resultado diferente do esperado executa algoritmo de
                 * retropropagacao e calcula o erro
                 * 
                 */
                if (y != entrada[i].getSaida()) {
                    rna.backPropagation(entrada[i]);
                    erro += Math.pow((y - entrada[i].getSaida()), 2);
                }
                epoca++;
            }
            erro = erro / entradaSize;
        } while (erro <= maxErro || epoca < maxEpoca);

    }

    public void setMaxEpoca(int maxEpoca) {
        this.maxEpoca = maxEpoca;
    }

}
