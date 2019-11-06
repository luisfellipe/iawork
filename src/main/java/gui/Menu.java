package gui;

import dados.Input;
import dados.LoadFile;
import ia.RNA;
import ia.TreinaRNA;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luis felipe
 */
public class Menu {

    private Scanner leitor;

    public Menu(Scanner leitor) {
        this.leitor = leitor;
    }

    public RNA criarRNA() {
        System.out.println("\n:::::::::::: Criar RNA :::::::::::::");
        int qLayers, qNeuronsIn, qNeuronsOut, qtdW;
        double txOfLearn = 0;
        System.out.println("Quantidade de camadas: ");
        qLayers = leitor.nextInt();
        System.out.println("Quantidade de neurônios da camada de entrada: ");
        qNeuronsIn = leitor.nextInt();
        System.out.println("Quantidade de neurônios da camada de saida: ");
        qNeuronsOut = leitor.nextInt();
        System.out.println("Quantidade de pesos: ");
        qtdW = leitor.nextInt();
        System.out.println("Taxa de aprendizagem [0%,100%]: ");
        txOfLearn = (leitor.nextInt()) / 100;

        RNA rna = new RNA(qLayers, qNeuronsIn, qNeuronsOut, qtdW, txOfLearn);
        new LoadFile().saveWeigths(rna, "src/main/java/dados/pesosIniciais.data");
        return rna;
    }

    public void treinar(RNA rna) {
        System.out.println("\n:::::::::::: Treinar RNA ::::::::::::::::::::");
        System.out.print("Quantidade de amostras:\n>>> ");
        int qtdAmostras = leitor.nextInt();

        TreinaRNA treinador = new TreinaRNA();
        String path = "src/main/java/dados/amostras.data";
        List<Input> amostras = new LoadFile().load(path, qtdAmostras);

        amostras.forEach(input ->System.out.println( input.toString()));//teste: apagar depois

        treinador.setRNA(rna);
        treinador.setAmostras(amostras);

        System.out.println("Escolha um numero de epocas: ");
        treinador.setMaxEpoca(leitor.nextInt());

        System.out.println("Escolha um erro minimo [0%,100%]: ");
        treinador.setMinErro(leitor.nextInt() / 100);

        System.out.println("Rodas por amostra: ");
        treinador.setRodadas(leitor.nextInt());

        System.out.println("\nTreinando RNA ... ");
        treinador.treinar();
        System.out.println("\nTerinamento concluido! :)");

    }

    public void classificar(RNA rna) {

        int n = 0;
        double[] dado = new double[5];

        int resposta = 0;
        while (true) {
            System.out.println("\n::::::::::::::::::: Classificar Dados ::::::::::::::::::");
            System.out.println("Insira amostra: ");
            System.out.println("(-1) - voltar");
            System.out.print(">>> ");
            for (int i = 0; i < 5; i++) {
                resposta = leitor.nextInt();
                if (resposta == -1) {
                    return;
                } else {
                    dado[i] = resposta;
                }
            }
            double y =  rna.setInput(dado);

            System.out.println(y);
        }
    }

    public void classificarTodos(RNA rna) {
        System.out.println(":::::::::::::: Classificar Todos ::::::::::::::::::::");
        String path = "src/main/java/dados/mamog.data";
        System.out.println("Quantidade de amostras: ");
        System.out.print(">>> ");
        int qtdAmostras = leitor.nextInt();

        List<Input> lista = new LoadFile().load(path, qtdAmostras);

        double acerto = 0, y = 0;
        System.out.println("::::: Classificando ....");
        for (Input in : lista) {
            y = rna.setInput(in.getInputs());
            if (y == in.getSaida()) {
                acerto++;
            }
            System.out.println("Saida: " + y + "\tSaida Esperada: " + in.getSaida());
            // System.out.println("Saida: " + y + " Saida Esperada: " + in.getSaida());
        }
        System.out.println("::::: pronto!!!");

        System.out.println("Porcentagem de acertos: " + (acerto / lista.size()) * 100 + "%");
    }

}
