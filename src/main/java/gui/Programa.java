package gui;

import java.util.Scanner;

/**
 *
 * @author luis
 */
public class Programa {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao;
        while (true) {
            System.out.println("::::::::::: sofIA ::::::::::::::");
            System.out.println("1 - criar RNA");
            System.out.println("2 - Treinar");
            System.err.println("3 - Classificar dado");
            System.out.println("4 - Classificar Conjunto de Dados");
            opcao = leitor.nextInt();
        }
    }
}
