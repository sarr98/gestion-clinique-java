package Dao;

import Entities.Prestation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public class PrestationDao implements IDao<Prestation> {
    DataBase database = new DataBase();
    private final String SQL_INSERT="INSERT INTO `rdv` (`time_rdv`,`date_rdv`,`type_rdv`,`code_patient`,`status`) VALUES (?,?,?,?,?)";

    @Override
    public int insert(Prestation prest) {
        //System.out.println(consult.getId());
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            //database.getPs().setInt(1, consult.getId());
            //System.out.println(consult.getId());
            database.getPs().setTime(1, prest.getHeure());
            database.getPs().setDate(2, prest.getDate());
            database.getPs().setString(3, prest.getTypeRdv());
            database.getPs().setString(4, prest.getCode_patient());
            database.getPs().setString(5, prest.getStatus());
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
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }

   

