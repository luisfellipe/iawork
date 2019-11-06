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
        ArrayList<Input> lista = null;
        try {
            
            FileReader in = null;
            lista = new ArrayList();
            File file = new File(inputPath);
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            
            String linha;
            linha = br.readLine();
            double[] dados = null;
            String data[] = null;
            double var = 0;
            Input input;
            
            while (linha != null) {
                dados = new double[qtdAmostras];
                data = linha.split(",");
                for (int i = 0; i < qtdAmostras; i++) {
                    /**
                     * quando dados estão incompletos
                     */
                    if (data[i].equals("?")) {
                        var = 0;
                        switch (i) {
                            case 0:
                                if (Double.parseDouble(data[5]) == 1) {
                                    var = 6;
                                } else {
                                    var = 2;
                                }
                                break;
                            
                            case 1:
                                var = 18;
                                if (Double.parseDouble(data[5]) == 1) {
                                    var = var * 1.8;
                                } else if (!data[0].equals("?")) {
                                    if (Double.parseDouble(data[0]) < 3) {
                                        var = var * 1.2;
                                    } else {
                                        var = var * 1.3;
                                    }
                                } else if (!data[0].equals("?")) {
                                    if (Double.parseDouble(data[0]) > 3) {
                                        var = var * 1.4;
                                    }
                                } else if (!data[2].equals("?")) {
                                    if (Double.parseDouble(data[2]) > 2) {
                                        var = var * 1.2;
                                    }
                                } else if (!data[3].equals("?")) {
                                    if (Double.parseDouble(data[3]) > 2) {
                                        var = var * 1.3;
                                    }
                                } else if (!data[4].equals("?")) {
                                    if (Double.parseDouble(data[4]) < 3) {
                                        var = var * 1.3;
                                    }
                                }
                                break;
                            case 2:
                                var = 0;
                                if (Double.parseDouble(data[5]) == 1) {
                                    var = 1;
                                } else if (!data[0].equals("?")) {
                                    if (Double.parseDouble(data[0]) > 3) {
                                        var = var + 1;
                                    }
                                } else if (!data[3].equals("?")) {
                                    if (Double.parseDouble(data[2]) > 3) {
                                        var = var + 1;
                                    }
                                } else if (!data[4].equals("?")) {
                                    if (Double.parseDouble(data[2]) < 3) {
                                        var = var + 1;
                                    }
                                } else if (Double.parseDouble(data[1]) > 35) {
                                    if (var < 4) {
                                        var = var + 1;
                                    }
                                }
                                break;
                            case 3:
                                var = 0;
                                if (Double.parseDouble(data[5]) == 1) {
                                    var = 1;
                                } else if (!data[0].equals("?")) {
                                    if (Double.parseDouble(data[0]) > 3) {
                                        var = var + 1;
                                    }
                                } else if (!data[1].equals("?")) {
                                    if (Double.parseDouble(data[1]) > 3) {
                                        var = var + 1;
                                    }
                                } else if (!data[2].equals("?")) {
                                    if (Double.parseDouble(data[2]) > 3) {
                                        var = var + 1;
                                    }
                                } else if (!data[4].equals("?")) {
                                    if (Double.parseDouble(data[4]) > 3) {
                                        var = var + 1;
                                    }
                                }
                                break;
                            case 4:
                                var = 5;
                                if (Double.parseDouble(data[5]) == 1) {
                                    var = var - 1;
                                } else if (!data[0].equals("?")) {
                                    if (Double.parseDouble(data[0]) > 4) {
                                        var = var - 1;
                                    }
                                    
                                } else if (!data[1].equals("?")) {
                                    if (Double.parseDouble(data[1]) > 45) {
                                        var = var - 1;
                                    }
                                } else if (!data[2].equals("?")) {
                                    if (Double.parseDouble(data[2]) >= 3) {
                                        var = var - 1;
                                    }
                                } else if (!data[3].equals("?")) {
                                    if (Double.parseDouble(data[3]) >= 3) {
                                        var = var - 1;
                                    }
                                }
                                
                                if (var < 1) {
                                    var = 1;
                                }
                                break;
                        }
                        dados[i] = var;
                        var = 0;
                    }// fim do tratamento
                    else {//se dados completos passa para double
                        dados[i] = Double.parseDouble(data[i]);
                    }
                }// fim do for
                input = new Input(dados.clone());
                lista.add(input);
                linha = br.readLine();
            }// fim do while
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void saveWeigths(RNA rna, String path) {
        
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder sb = new StringBuilder();
            /////////////////////////////////////////////////////////////////
            for (List<Neuronio> layer : rna.getLayers()) {
                for (Neuronio neuronio : layer) {
                    sb.append("W[");
                    for (double w : neuronio.getPesos()) {
                        sb.append(w).append("]\t");
                    }
                    sb.append("\n");
                }
            }
            ///////////////////////////////////////////////////////////////
            bw.write(sb.toString());
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showWeights(String path) {
        FileReader in = null;
        try {
            File file = new File(path);
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            String linha = br.readLine();
            StringBuilder sb = new StringBuilder();
            ///////////////////////////////////////////////////
            while (linha != null) {
                sb.append(linha).append("\n");
                System.out.println(linha);
                linha = br.readLine();
            }
            ////////////////////////////////////
            br.close();
            ///printa pesos na tela
            System.out.println(sb.toString());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadFile.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class
                    .getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                in.close();
                
            } catch (IOException ex) {
                Logger.getLogger(LoadFile.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
