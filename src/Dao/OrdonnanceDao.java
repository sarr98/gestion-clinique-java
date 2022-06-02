
package Dao;

import Entities.Ordonnance;
import Entities.RendezVous;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdonnanceDao implements IDao<Ordonnance> {
    private DataBase database = new DataBase();
    private final String SQL_INSERT="INSERT INTO `ordonnace` (`code_pat`,`id_medecin`,`code_medicament`,`posologie`,`id_consultation`) VALUES (?,?,?,?,?)";
    private final String SQL_SELECT="SELECT nom_medicament, posologie from ordonnace o, medicament m  WHERE o.code_medicament like m.code_medicament and o.code_pat like ? and o.id_medecin = ?";
    private final String SQL_SELECT2="SELECT nom_medicament, posologie from ordonnace o, medicament m WHERE o.code_medicament like m.code_medicament and o.code_pat like 'PATIENT0001' and o.id_medecin = '18' ";
    @Override
    public int insert(Ordonnance ordo) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, ordo.getCodePatient());
            database.getPs().setInt(2, ordo.getId_medecin());
            database.getPs().setString(3, ordo.getCodeMedicament());
            database.getPs().setString(4, ordo.getPosologie());
            database.getPs().setInt(5, ordo.getIdConsultation());
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
    public int update(Ordonnance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Ordonnance> findAll2(int id, String code) {
        List<Ordonnance> ordo=new ArrayList<>();
        Ordonnance ord = new Ordonnance();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT);
        try {
             database.getPs().setString(1, code);
             database.getPs().setInt(2, id);
            ResultSet rs = database.executeSelect(SQL_SELECT);
            //System.out.print(rs);
            while(rs.next())
            {
                
                //Mapping relation vers objet,rs.getDate("date_rdv")
                    //System.out.print(rs.getString("nom_medicament"));
                    ord =new Ordonnance(rs.getString("m.nom_medicament"),rs.getString("posologie"));
                    ordo.add(ord);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        finally {
            database.closeConnexion();
        }
        
    return ordo;        
    
    }
    
}
