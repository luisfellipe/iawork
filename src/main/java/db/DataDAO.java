package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class DataDAO {
    
    public void saveData(int[] data, int size){
        String sql = "INSERT INTO aviao(marca, modelo, qtdassentos) VALUES(?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            for (int i = 0; i < size; i++) {
                stmt.setInt(i, data[i]);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
