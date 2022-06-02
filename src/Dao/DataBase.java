package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    @SuppressWarnings("FieldMayBeFinal")
    private String url="jdbc:mysql://127.0.0.1:3306/examen java";
    @SuppressWarnings("FieldMayBeFinal")
    private String user="root";
    @SuppressWarnings("FieldMayBeFinal")
    private String password="";
    Connection cnx=null;
    //Objet d'execution des requetes preparé
    private PreparedStatement ps=null;
    public void openConnexion(){
        try {
            //1-charger le Driver
            Class.forName("com.mysql.jdbc.Driver");
            //2-Ouverture de la connection
            
            try {
                cnx=DriverManager.getConnection(url,user,password);
            } catch (SQLException ex) {
               System.out.println(ex.getMessage());
            }
            System.out.println("CONNEXION ETABLIE");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
            
            }
    
    
    public void closeConnexion(){
        if(cnx!=null){
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());            }
        }
    }
    public void initPrepareStatement(String sql){
        if(sql.toLowerCase().startsWith("insert")){
            try {
                ps=cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());            }
        }else{
            try {
                ps=cnx.prepareStatement(sql);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public int executeUpdate(String sql){
        int nbreLigne=0;
    
        try {
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    return nbreLigne;
    }
    public ResultSet executeSelect(String sql){
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    return rs;
    }
    public PreparedStatement getPs() {
        return ps;
    }
}
