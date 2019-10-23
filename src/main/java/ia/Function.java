package ia;

/**
 *
 * @author luis
 */
public class Function {
    private double beta = 1;
    public double activation(double u){
       return this.logistica(u);
        
    }
    
    private double logistica(double u){
        u = (1-Math.pow(2.718281828459045, -(beta*u)))/(1+Math.pow(2.718281828459045, -(beta*u)));
        return u;
     
    }
    
    public void copy(double[] orig, double[] dest, int size ){
        for (int i = 0; i < size; i++) {
            dest[i] = orig[i];
        }
    }
}
