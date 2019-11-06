package gui;

import dados.Input;
import dados.LoadFile;
import ia.RNA;
import ia.TreinaRNA;
import java.util.List;
import java.util.Locale;
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
        System.out.println(":::::::::::: Criar RNA :::::::::::::");
        int qLayers, qNeuronsIn, qNeuronsOut, qNeuronsHidden, qtdW;
        double txOfLearn = 0;
        System.out.println("Quantidade de camadas: ");
        qLayers = leitor.nextInt();
        System.out.println("Quantidade de neurônios da camada de entrada: ");
        qNeuronsIn = leitor.nextInt();
        System.out.println("Quantidade de neurônios da camada de saida: ");
        qNeuronsOut = leitor.nextInt();
        System.out.println("Quantidade de pesos: ");
        qtdW = leitor.nextInt();
        System.out.println("Taxa de aprendizagem(0-100): ");
        txOfLearn = (leitor.nextInt()) / 100;

        qNeuronsHidden = qLayers - 2;

        RNA rna = new RNA(qLayers, qNeuronsIn, qNeuronsOut, qNeuronsHidden, qtdW, txOfLearn);
        new LoadFile().saveWeigths(rna, "src/main/java/dados/pesosIniciais.data");
        return rna;
    }

    public void treinar(RNA rna) {
        System.out.println(":::::::::::: Treinar RNA ::::::::::::::::::::");
        TreinaRNA treinador = new TreinaRNA();
        String path = "src/main/java/dados/amostras.data";
        List<Input> lista = new LoadFile().load(path, 6, 289);
        lista.forEach(action -> action.toString());

        treinador.setData(lista);
        System.out.println("Escolha uma um numero de epocas: ");
        treinador.setMaxEpoca(leitor.nextInt());
        System.out.println("Escolha um erro minimo: ");
        treinador.setMinErro(leitor.nextDouble());
        treinador.setRNA(rna);
        System.out.println("Rodas por amostra: ");
        treinador.setRodadas(leitor.nextInt());

        System.out.println("Iniciando treino ... ");
        treinador.treinar();
        System.out.println("Terinamento concluido! :)");

    }

    public void classificar(RNA rna) {
        System.out.println("::::::::::::::::::: Classificar Dado ::::::::::::::::::");
        System.out.println("Insira dos dados [x..y] ");
        String dados = leitor.nextLine();
        String dado[] = dados.split(" ");
        int n = dado.length - 1;
        double[] d = new double[n];
        for (int i = 0; i < n; i++) {
            d[i] = Double.parseDouble(dado[i]);
        }
        double y = rna.setInput(d);

        if (y < 0.5) {
            System.out.println("Maligno!");
        } else {
            System.out.println("Benigno");
        }

    }

    public void classificarTodos(RNA rna) {
        System.out.println(":::::::::::::: Classificar Todos ::::::::::::::::::::");
        String path = "src/main/java/dados/mamog.data";
        List<Input> lista = new LoadFile().load(path, 6, 289);
        double acerto = 0, y = 0;
        for (Input in : lista) {
            y = rna.setInput(in.getInputs());
            if (y == in.getSaida()) {
                acerto++;
            }
        }

        System.out.println("Porcentagem de acertos: " + (acerto / lista.size()) * 100 + "%");
    }

}
