package teste;

import dados.LoadFile;

/**
 *
 * @author luis
 */
public class TestaLoadFile {
    public static void main(String[] args) {
        LoadFile lf = new LoadFile();
        int table[][] = lf.load("db/mamog.data");
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(table[i][j]+" "); 
           }
            System.out.println("");
        }
    }
}
