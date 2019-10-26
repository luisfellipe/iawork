package dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class LoadFile {

    public int[][] load(String pathname) {
        FileReader in = null;
         int[][] table = null;
        try {
           
            File file = new File(pathname);
            //if(!file.exists()) file.createNewFile();
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            String linha = br.readLine();
            String data[] = null;
            int x = 0, n;
            while (linha != null) {
                data = linha.split(",");
                for (int i = 0; i < data.length; i++) {
                    table[x][i] = Integer.parseInt(data[i]);
                }
                linha = br.readLine();
            }
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

        return table;
    }
}
