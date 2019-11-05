package teste;

/**
 *
 * @author luis
 */
public class TestaVetor {

    public static void main(String[] args) {

        int[] n = {1, 1, 2, 3, 5, 4, 6};
        System.out.println("N: " + n.length);

        /*  int[][] m = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m[i][j] = i * j + 1;
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.print("M: " + m[1][i] + " ");
        }

        /*int tam = 5;
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
        
        double i = 0.5;
        if(i > 0.4) System.out.println("i é maior que 0.4");
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
