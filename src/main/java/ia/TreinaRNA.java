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
    private int epoca, entradaSize, maxEpoca = 1000;
    private double erro, minErro, rodadas;
    RNA rna = null;
    private List<Input> amostra;

    public void setRNA(RNA rna) {
        this.rna = rna;
    }

    public void setMinErro(double minErro) {
        this.minErro = minErro;
    }

    public void setMaxEpoca(int maxEpoca) {
        this.maxEpoca = maxEpoca;
    }

    public void setRodadas(double rod) {
        this.rodadas = rod;
    }

    /**
     * recebe os dados para treino da rna
     */
    public void setAmostras(List<Input> entrada) {
        this.amostra = entrada;
        this.entradaSize = entrada.size();
    }

    public void treinar() {
        erro = 0.0;
        double y = 0.0; //resultado da classificacao de uma amostra
        int r = 0;
        epoca = 0;
        do {
            for (int i = 0; i < entradaSize; i++) {
                for (int j = 0; j < rodadas; j++) {
                    // r = new Random().nextInt(entradaSize + 1); // escolhe indice de uma amostra aleatoria
                    y = rna.setInput(amostra.get(i).getInputs()); // rna processa amostra
                    /**
                     * se resultado diferente do esperado executa algoritmo de
                     * retropropagacao e calcula o erro
                     *
                     */
                    rna.backPropagation(amostra.get(i));//faz caminho inverso da rede ajustando os pesos
                }
                erro += Math.pow((y - amostra.get(i).getSaida()), 2);//soma dos erros quadraticos
            }//fim das amostras
            epoca++;
            erro = erro / entradaSize;
            if (erro >= minErro && epoca == maxEpoca) {
                System.err.println("RNA não atingiu parametros de treino exigidos");
            }
        } while (erro <= minErro || epoca < maxEpoca);

    }

}
