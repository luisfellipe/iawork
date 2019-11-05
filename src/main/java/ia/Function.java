package ia;

/**
 *
 * @author luis felipe
 */
public class Function {

    private double beta = 1; // beta: constante de inclinacao

    public double activation(double u) {
        u = 1 / (1 + Math.pow(Math.E, (-beta) * u));
        return u;
    }

    /**
     * Derivada da funcao de ativacao utilizada nos neuronios internos para
     * calculo do gradiente descendente
     */
    public double activationD(double u) {
        u = u * (1 - u);
        return u;
    }

    /**
     * seta uma constante de inclinacao
     */
    public Function setBeta(double beta) {
        this.beta = beta;
        return this;
    }

}
