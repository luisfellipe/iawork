package dados;

import ia.Neuronio;
import ia.RNA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis felipe
 *
 */
public class LoadFile {

    public List<Input> load(String inputPath, int qtdData, int qtdAmostras) {

        double[] normal = {2, 30, 4, 1, 3};
        FileReader in = null;
        double[] dados = new double[qtdAmostras];
        Input input;
        ArrayList<Input> lista = new ArrayList();
        try {
            File file = new File(inputPath);
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            String linha = br.readLine();
            String data[] = null;
            int x = 0;
            while (linha != null) {
                data = linha.split(",");
                for (int i = 0; i < qtdAmostras; i++) {
                    /**
                     * quando dados estão incompletos
                     */
                    if (data[i].equals("?")) {
                        dados[i] = normal[i];
                    } else {
                        dados[i] = Double.parseDouble(data[i]);
                    }
                    input = new Input(dados);
                    lista.add(input);
                }
                linha = br.readLine();
                x++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                in.close();

            } catch (IOException ex) {
                Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    public void saveWeigths(RNA rna, String path) {

        FileWriter fw = null;
        try {
            File file = new File(path);
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            int i, j;
            while (true) {
                Neuronio[] n;
                StringBuilder sb = new StringBuilder();
                n = rna.getLayerInput();
                int qW = n[0].getQtdPesos();
                double[] W = null;
                for (i = 0; i < n.length - 1; i++) {
                    W = n[i].getPesos();
                    for (j = 0; j < qW - 1; j++) {
                        sb.append(W[j]).append(",");
                    }
                    sb.append(W[j]);
                    bw.write(sb.toString());
                    sb = null;
                }
                int k;// itera sobre os pesos de cada neuronio
                Neuronio[][] m = rna.getLayerHidden();
                for (i = 0; i < rna.getLayerHidden().length; i++) {
                    for (j = 0; j < rna.getqNeuronsHidden(); j++) {
                        W = m[i][j].getPesos();
                        for (k = 0; k < qW; k++) {
                            sb.append(W[k]).append(",");
                        }
                        sb.append(W[k]);
                        bw.write(sb.toString());
                        sb = null;
                    }

                }

                n = rna.getLayerOutput();
                qW = n[0].getQtdPesos();
                for (i = 0; i < n.length - 1; i++) {
                    W = n[i].getPesos();
                    for (j = 0; j < qW - 1; j++) {
                        sb.append(W[j]).append(",");
                    }
                    sb.append(W[j]);
                    bw.write(sb.toString());
                    sb = null;
                }
                bw.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void showWeights(String path) {
        FileReader in = null;
        try {
            File file = new File(path);
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            String linha = br.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                in.close();

            } catch (IOException ex) {
                Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
