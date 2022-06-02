package Dao;

import Entities.Medecin;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MedecinDao implements IDao<Medecin> {
    private final String SQL_SELECT= "select * from users where `role` like ?";
    DataBase dataBase = new DataBase();

    @Override
    public int insert(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> medecins = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        try {
            dataBase.getPs().setString(1, "medecin");
            ResultSet rs = dataBase.executeSelect(SQL_SELECT);
            
            while(rs.next())
            {
                
                int id = rs.getInt("id");
                String nom =rs.getString("nom");
                String prenom=rs.getString("prenom");
                String specialite = rs.getString("specialite");
                Medecin  med= new Medecin(specialite,id,nom,prenom);
                //
                medecins.add(med);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        
        return medecins;        
    }

    @Override
    public Medecin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
