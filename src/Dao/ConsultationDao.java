package Dao;

import Entities.Consultation;
import Entities.Medecin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public class ConsultationDao implements IDao<Consultation>{
    DataBase database= new DataBase();
    private final String SQL_INSERT="INSERT INTO `rdv` (`id_medecin`,`time_rdv`,`date_rdv`,`type_rdv`,`code_patient`,`status`) VALUES (?,?,?,?,?,?)";

    @Override
    public int insert(Consultation consult) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setInt(1,consult.getId_medecin());
            database.getPs().setTime(2, consult.getHeure());
            database.getPs().setDate(3, consult.getDate());
            database.getPs().setString(4, consult.getTypeRdv());
            database.getPs().setString(5, consult.getCode_patient());
            database.getPs().setString(6, consult.getStatus());
            
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            database.closeConnexion();
        }
        return lastInsertId;
    }

    @Override
    public int update(Consultation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

   

