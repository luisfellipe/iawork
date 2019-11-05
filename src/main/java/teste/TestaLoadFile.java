package teste;

import dados.Input;
import dados.LoadFile;
import java.util.List;

/**
 *
 * @author luis
 */
public class TestaLoadFile {
    
    public static void main(String[] args) {
        
        String path2 = "src/main/java/dados/limpo.data";
        
        LoadFile ld = new LoadFile();
        List<Input> in = ld.load(path2, 830, 6);
        for (int i = 0; i < 830; i++) {
            System.out.println(in.get(i).toString());
        }
        
    }
}
