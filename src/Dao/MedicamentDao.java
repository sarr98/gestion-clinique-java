
package Dao;

import Entities.Medicament;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MedicamentDao implements IDao<Medicament> {
    
    DataBase dataBase = new DataBase();
    private final String SQL_SELECTALL="select * from `medicament`";

    @Override
    public int insert(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> medocs=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECTALL);
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECTALL);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    Medicament med =new Medicament(rs.getInt("id"),rs.getString("code_medicament"),rs.getString("nom_medicament"));
                    medocs.add(med);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        return medocs;
    }

    @Override
    public Medicament findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
}
