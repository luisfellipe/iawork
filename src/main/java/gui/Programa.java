package gui;

import dados.LoadFile;
import ia.RNA;
import java.util.Scanner;

/**
 *
 * @author luis felipe
 */
public class Programa {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao;
        Menu menu = new Menu(leitor);
        RNA rna = null;
        boolean rnaCriada = false, rnaTreinada = false;
        while (true) {
            StringBuilder sb = new StringBuilder("::::::::::: sofIA ::::::::::::::")
                    .append("\n")
                    .append("1 - criar RNA").append("\n")
                    .append("2 - Treinar").append("\n")
                    .append("3 - Classificar dados").append("\n")
                    .append("4 - Classificar Conjunto de Dados").append("\n")
                    .append("5 - Visualizar RNA").append("\n")
                    .append("6 - Visualizar Pesos Finais e Iniciais da RNA").append("\n")
                    .append("0 - Encerrar").append("\n")
                    .append(">>> ");

            System.out.print(sb.toString());
            opcao = leitor.nextInt();

            switch (opcao) {
                case 1:
                    rna = menu.criarRNA();
                    new LoadFile().saveWeigths(rna, "src/main/java/dados/pesosIniciais.data");
                    rnaCriada = true;
                    System.out.println("\nRNA criada com sucesso!\n");
                    break;
                case 2:
                    if (rnaCriada == false) {
                        System.err.println("\nRNA ainda não foi criada!");

                    } else {
                        menu.treinar(rna);
                    }

                    break;
                case 3:
                    if (rnaCriada == false) {
                        System.err.println("\nRNA ainda não foi criada!");
                    } else {
                        menu.classificar(rna);
                    }
                    break;
                case 4:
                    if (rnaCriada == false) {
                        System.err.println("\nRNA ainda não foi criada!");
                    } else {
                        menu.classificarTodos(rna);
                    }
                    break;
                case 5:
                    if (rnaCriada == false) {
                        System.err.println("\nRNA ainda não foi criada!");
                    } else {
                        rna.printNet();
                    }
                    break;
                case 6:
                    if (rnaTreinada) {
                        System.out.println("::::::::::::::::::: Pesos Antigos ::::::::::::::::::");
                        new LoadFile().showWeights("src/main/java/dados/pesosIniciais.data");
                        System.out.println("::::::::::::::::::: Pesos Atuais :::::::::::::::::::");
                        rna.printNet();
                    } else {
                        System.err.println("RNA não foi treinada ainda! ");
                    }
                    ;

                default:
                    System.out.println(":::::::::::::Encerrando sofIA:::::::::");
                    System.exit(opcao);
            }

        }
    }
}
