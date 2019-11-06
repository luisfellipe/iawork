package teste;

import java.util.Random;

/**
 *
 * @author luis
 */
public class TestaNAleatorio {

    public static void main(String[] args) {
        int n = 0;
        while (true) {
            n = new Random().nextInt(100);
            System.out.println("N aleatorio: " + n);
        }
    }

}
