package teste;

/**
 *
 * @author luis
 */
public class TestaVetor {

    public static void main(String[] args) {
        int tam = 5;
        double[] W = new double[tam];
        W[0] = 2;
        W[1] = 2;
        W[2] = 5;
        W[3] = 2;
        W[4] = 2;
        double[] W2 = W.clone();
        for (int i = 0; i < tam; i++) {
            System.out.println(W2[i]);
        }
        System.out.println(W.length);
        /*
        for (int i = 0; i < tam; i++) {
            W[i] = i+1 * 2.71828356;
        }
        double[] W2 = new double[tam];
        
        W2 = W;
        
        for (int i = 0; i < tam; i++) {
            System.out.println(W2[i]);
        }
        
        System.out.println("");
        
        W[2] = 3;
        
          for (int i = 0; i < tam; i++) {
            System.out.println(W2[i]);
        }*/

    }
}
