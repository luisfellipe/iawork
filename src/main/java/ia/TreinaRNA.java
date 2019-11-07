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
     * @entradaSize: tamanho de cada amostra
     * @erro: margem de erro na classificação da amostra
     * @minErro: erro minimo de aceitação da RNA
     * @rna: rede neural artificial
     * @epoca: iterador de epocas passadas
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

    /**
     *
     * @param minErro erro minimo esperado para rna
     */
    public void setMinErro(double minErro) {
        this.minErro = minErro;
    }

    /**
     *
     * @param maxEpoca quantidade maxima de epocas
     */
    public void setMaxEpoca(int maxEpoca) {
        this.maxEpoca = maxEpoca;
    }

    /**
     *
     * @param rod numero de rodadas
     */
    public void setRodadas(double rod) {
        this.rodadas = rod;
    }

    /**
     * @List<Input> entrada: amostras para treino recebe os dados para treino da
     * rna
     */
    public void setAmostras(List<Input> entrada) {
        this.amostra = entrada;
        this.entradaSize = entrada.size();
    }

    public void treinar() {
        erro = 0.0;
        double y = 0.0; //resultado da classificacao de uma amostra
        int r[] = null;
        epoca = 0;
        do {
            for (int i = 0; i < entradaSize; i++) {
                for (int j = 0; j < rodadas; j++) {
                    r = ordem(); // escolhe indice de uma amostra aleatoria
                    y = rna.setInput(amostra.get(r[i]).getInputs()); // rna processa amostra
                 
                    rna.backPropagation(amostra.get(r[i]).getSaida());//faz caminho inverso da rede ajustando os pesos
                }
                erro += Math.pow((y - amostra.get(r[i]).getSaida()), 2);//soma dos erros quadraticos
            }//fim das amostras
            epoca++;
            erro = erro / entradaSize;
            if (erro > minErro && epoca == maxEpoca) {
                System.err.println("RNA não atingiu parâmetros de treino exigidos");
            }
        } while (erro <= minErro || epoca == maxEpoca);

    }

    //gera uma ordem de entrada para as amostras no rna.backPropagation
    private int[] ordem() {
        int[] n = new int[entradaSize];
        for (int i = 0; i < entradaSize; i++) {
            n[i] = new Random().nextInt(entradaSize + 1);
            for (int j = 0; j < i; j++) {
                if (n[i] == n[j]) {
                    i--;
                }
            }
        }
        return n;
    }

}
