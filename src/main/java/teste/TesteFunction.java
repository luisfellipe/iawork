package teste;

import ia.Function;
import java.util.Scanner;

/**
 *
 * @author luis
 */
public class TesteFunction {
    public static void main(String[] args) {
        Function f = new Function();
        boolean continua = true;
        Scanner leitor = new Scanner(System.in);
        while(continua){
            System.out.println("Digite um numero: ");
            System.out.println(Math.abs(f.activation(Double.valueOf(leitor.nextLine()))));
            leitor.reset();
        }
        
    }
}
