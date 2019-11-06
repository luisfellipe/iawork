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
            System.out.println("::::::::::: sofIA ::::::::::::::");
            System.out.println("1 - criar RNA");
            System.out.println("2 - Treinar");
            System.err.println("3 - Classificar dado");
            System.out.println("4 - Classificar Conjunto de Dados");
            System.out.println("5 - Visualizar RNA");
            System.out.println("6 - Visualizar Pesos Finais e Iniciais da RNA");
            System.out.println("0 - Encerrar");
            opcao = leitor.nextInt();

            switch (opcao) {
                case 1:
                    rna = menu.criarRNA();
                    new LoadFile().saveWeigths(rna, "src/main/java/dados/pesosIniciais.data");
                    rnaCriada = true;
                    break;
                case 2:
                    if (rnaCriada == false) {
                        System.out.println("RNA ainda não foi criada!");
                    } else {
                        menu.treinar(rna);
                    }

                    break;
                case 3:
                    if (rnaCriada == false) {
                        System.out.println("RNA ainda não foi criada!");
                    } else {
                        menu.classificar(rna);
                    }
                    break;
                case 4:
                    if (rnaCriada == false) {
                        System.out.println("RNA ainda não foi criada!");
                    } else {
                        menu.classificarTodos(rna);
                    }
                    break;
                case 5:
                    if (rnaCriada == false) {
                        System.out.println("RNA ainda não foi criada!");
                    } else {
                        rna.printNet();
                    }
                    break;
                case 6:
                    System.out.println("::::::::::::::::::: Pesos Antigos ::::::::::::::::::");
                    new LoadFile().showWeights("src/main/java/dados/pesosIniciais.data");
                    System.out.println("::::::::::::::::::: Pesos Atuais :::::::::::::::::::");
                    rna.printNet();

                    ;

                default:
                    System.out.println(":::::::::::::Encerrando sofIA:::::::::");
                    System.exit(opcao);
            }

        }
    }
}
