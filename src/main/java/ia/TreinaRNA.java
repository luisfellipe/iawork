package ia;

import dados.Input;
import java.util.List;
import java.util.Random;

/**
 *
 * @author luis felipe
 */
public class TreinaRNA {

    /**
     * @entrada: amostras para treino
     * @saida: resultados esperados para cada amostra
     * @entradaSize: tamanho de cada amostra
     * @amostraSize:quantidade de dados de cada amostra
     * @erro: margem de erro na classificação da amostra
     * @minErro: erro minimo de aceitação da RNA
     * @rna: rede neural artificial
     * @epoca: iterador
     * @rodadas: quantidade de uma unica amostra servira de input para uma RNA
     * ajustar seus pesos com backpropagation
     */
    private int epoca, entradaSize, amostraSize, maxEpoca = 1000;
    private double erro, minErro, rodadas;
    RNA rna = null;
    private List<Input> amostra;

    public void setRNA(RNA rna) {
        this.rna = rna;
    }

    public void setMinErro(double minErro) {
        this.minErro = minErro;
    }

    /**
     * recebe os dados para treino da rna
     */
    public void setData(List<Input> entrada) {
        this.amostra = entrada;
        this.amostraSize = entrada.get(0).getSizeData();
        this.entradaSize = entrada.size();
    }

    public void treinar() {
        erro = 0.0;
        double y = 0.0; //resultado da classificacao de uma amostra
        do {
            epoca = 0;
            for (int i = 0; i < amostraSize; i++) {
                int r = new Random().nextInt() * entradaSize; // escolhe indice de uma amostra aleatoria
                y = rna.setInput(amostra.get(r).getInputs()); // rna processa amostra
                /**
                 * se resultado diferente do esperado executa algoritmo de
                 * retropropagacao e calcula o erro
                 *
                 */
                if (y != amostra.get(r).getSaida()) {
                    rna.backPropagation(amostra.get(r));
                    erro += Math.pow((y - amostra.get(r).getSaida()), 2);
                }
                epoca++;
            }
            erro = erro / entradaSize;
            if (erro > minErro && epoca == maxEpoca) {
                System.err.println("RNA não atingiu parametros de treino exigidos");
            }
            erro = erro/ amostra.size();
            
        } while (erro <= minErro || epoca < maxEpoca);

    }

    public void setMaxEpoca(int maxEpoca) {
        this.maxEpoca = maxEpoca;
    }

    public void setRodadas(double rod) {
        this.rodadas = rod;
    }
}
