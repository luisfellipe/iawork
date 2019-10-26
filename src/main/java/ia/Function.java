package ia;

/**
 *
 * @author luis
 */
public class Function {
    private double beta = 1;
    public double activation(double u){
         u = 1/(1+Math.pow(Math.E, (-beta)*u));
       return u;
    }
    public Function setBeta(double beta){
        this.beta = beta;
        return this;
    }
}
