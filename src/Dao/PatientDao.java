
package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Patient;
import Entities.Users;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatientDao implements IDao<Patient>{

    private DataBase database = new DataBase();
    private final String SQL_INSERT="INSERT INTO `users` (`domicile`,`antecedant_medicaux`,`nom`,`mail`,`prenom`,`login`,`password`,`tel`,`role`,`code_pat`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT="SELECT * FROM `users` WHERE nom like ? and prenom like ?";
    private final String SQL_SELECT2="SELECT * FROM `users` WHERE code_pat like ?";
    @Override
    public int insert(Patient patient) {
         int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, patient.getDomicile());
            database.getPs().setString(2, patient.getAntecedantMedicaux());
            database.getPs().setString(3, patient.getNom());
            database.getPs().setString(4, patient.getMail());
            database.getPs().setString(5, patient.getPrenom());
            database.getPs().setString(6, patient.getLogin());
            database.getPs().setString(7, patient.getPassword());
            database.getPs().setInt(8, patient.getTel());
            database.getPs().setString(9, patient.getRole());
            database.getPs().setString(10, patient.getCode());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        finally {
            database.closeConnexion();
        }
        return lastInsertId;
    }
    

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Patient findByNomPrenom(Users user) {
        
        Patient pat = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT);
        //System.out.println("avant if");
        try {
            database.getPs().setString(1,user.getNom());
            database.getPs().setString(2,user.getPrenom());
            
        } catch (SQLException ex) {
            
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rs = database.executeSelect(SQL_SELECT);
        try {
            if(rs.next()){
                 pat = new Patient(rs.getString("code_pat"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"));    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        database.closeConnexion();
        return pat;
    }

}
    

