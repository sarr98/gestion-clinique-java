
package Dao;

import Entities.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UsersDao implements IDao<Users> {
    
    private DataBase database = new DataBase();
    private final String SQL_LOGIN_PASSWORD="select * from `users` where `login` like ? and `password`  like ?";
    private final String SQL_SELECT_BY_CODE_PATIENT="select * from `users` where `code_pat` like ? ";
    @Override
    public int insert(Users ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Users ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Users> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Users findByLoginPassword(String login,String password){
        Users user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_LOGIN_PASSWORD);
        //System.out.println("avant if");
        try {
            database.getPs().setString(1,login);
            database.getPs().setString(2,password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        ResultSet rs = database.executeSelect(SQL_LOGIN_PASSWORD);
        try {
            if(rs.next()){
                user = new Users(rs.getInt("id"),rs.getString("nom"),rs.getString("mail"),rs.getString("prenom"),rs.getString("login"),rs.getString("password"),rs.getString("role"),rs.getInt("tel"),rs.getString("code_pat"));    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            database.closeConnexion();
        }
        return user;
    }
    
    public Users findByCodePatient(String code){
        Users user=new Users();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_BY_CODE_PATIENT);
        try {
            database.getPs().setString(1, code );
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_CODE_PATIENT);
            
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                user = new Users(rs.getInt("id"),rs.getString("nom"),rs.getString("mail"),rs.getString("prenom"),rs.getString("login"),rs.getString("password"),rs.getString("role"),rs.getInt("tel"),rs.getString("code_pat"),rs.getString("antecedant_medicaux"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            database.closeConnexion();
        }
    return user;     
    }    
    
    
}
