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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis felipe
 */
public class Limpa {

    
    private StringBuilder sb;

    public void format(String inputPath) {
        FileReader in = null;
        try {
            File file = new File(inputPath);
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);

            String linha = br.readLine();
            sb = new StringBuilder();
            while (linha != null) {
                if (!linha.contains("?")) {
                    sb.append(linha).append("\n");
                }
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

    public void write(String path) {

        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());

            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Limpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedReader open(String inputPath) {
        FileReader in = null;
         BufferedReader br = null;
        try {
            File file = new File(inputPath);
            in = new FileReader(file);
           br = new BufferedReader(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return br;
    }

    public void close(BufferedReader br) {
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Limpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
