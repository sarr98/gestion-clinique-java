
package Dao;

import Entities.Consultation;
import Entities.Medecin;
import Entities.Patient;
import Entities.RendezVous;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
//import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RendezVousDao implements IDao<RendezVous> {
    
    DataBase dataBase = new DataBase();
    private final String SQL_SELECT_ID_RDV_BY_CODE="select `id` from `rdv` where `code_patient` like ?";
    private final String SQL_SELECT= "select * from `rdv` where `type_rdv` like ? and `date_rdv` = ? and `time_rdv` = ? and `id_medecin` = ? and `code_patient` like ?";
    private final String SQL_SELECTALL="select * from `rdv` where code_patient like ?";
    private final String SQL_SELECTALLBYALL="select * from `rdv`";
    private final String SQL_SELECT_CODE_PATIENT_BY_ID="select `code_patient` from `rdv` where `status` like 'CONFIRME' and `id_medecin` = ? ";
    private final String SQL_SELECT_ID_RDV="select `id` from `rdv`";
    private final String SQL_SELECT_CODE_PATIENT="select `code_patient` from `rdv` ";
    private final String SQL_SELECT_DATE="select `date_rdv` from `rdv`";
    private final String SQL_UPDATE_STATUS_CONFIRM = "update `rdv` set `status` = 'CONFIRME' where `id` = ? ";
    private final String SQL_UPDATE_STATUS_ANNULER = "update `rdv` set `status` = 'ANNULEE' where `id` = ? ";
    private final String SQL_SELECT_BY_DATE="select * from `rdv` where `date_rdv` = ? and `type_rdv` like 'PRESTATION' ";
    private final String SQL_SELECTALLBYID_AND_DATE="select * from `rdv` where `id_medecin` = ? and `date_rdv` = ? and `status` like 'CONFIRME' ";
    @Override
    public int insert(RendezVous ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(RendezVous ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int updateStatusConfirm(int id) {
        int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE_STATUS_CONFIRM);
        try {
            dataBase.getPs().setInt(1, id);
            nbrLigne=dataBase.executeUpdate(SQL_UPDATE_STATUS_CONFIRM);         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        return nbrLigne;
    }
    
    public int updateStatusAnnuler(int id) {
        int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE_STATUS_ANNULER);
        try {
            dataBase.getPs().setInt(1, id);
            nbrLigne=dataBase.executeUpdate(SQL_UPDATE_STATUS_ANNULER);         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        finally {
            dataBase.closeConnexion();
        }
        return nbrLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean findAllByAll(Medecin med, String type_rdv, Date date, Time time, Patient pat) {
        RendezVous rendezV=new RendezVous();
        dataBase.openConnexion();
        //System.out.println(SQL_SELECT);
        dataBase.initPrepareStatement(SQL_SELECT);
        try {
            dataBase.getPs().setString(1,type_rdv);
            dataBase.getPs().setDate(2,date);
            dataBase.getPs().setTime(3,time);
            dataBase.getPs().setInt(4,med.getId());
            dataBase.getPs().setString(5,pat.getCode());
            ResultSet rs = dataBase.executeSelect(SQL_SELECT);
            while(rs.next())
            {
                int id = rs.getInt("id");
                String typeRDV =rs.getString("type_rdv");
                Date dateRDV=rs.getDate("date_rdv");
                Time timeRDV = rs.getTime("time_rdv");
                int id_med=rs.getInt("id_medecin");
                String code_pat=rs.getString("code_patient");
                rendezV = new Consultation(id,dateRDV,timeRDV,typeRDV,id_med,code_pat);
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        
    return false;        
    }

    public List<RendezVous> findByIdMedecin(int id,Date date) {
        List<RendezVous> rdvs=new ArrayList<>();
        RendezVous rendezVous = new RendezVous();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECTALLBYID_AND_DATE);
        try {
             dataBase.getPs().setInt(1, id);
             dataBase.getPs().setDate(2, date);
            ResultSet rs = dataBase.executeSelect(SQL_SELECTALLBYID_AND_DATE);
            
            while(rs.next())
            {
                
                //Mapping relation vers objet,rs.getDate("date_rdv")
                    rendezVous =new RendezVous(rs.getDate("date_rdv"),rs.getInt("id"),rs.getTime("time_rdv"),rs.getString("type_rdv"),rs.getString("status"),rs.getString("code_patient"));
                    rdvs.add(rendezVous);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        
    return rdvs;        
    }


    public List<RendezVous> findAllByCode(String code) {
        //RendezVous rendezV=new RendezVous();
        List<RendezVous> rdvs=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECTALL);
        try {
             dataBase.getPs().setString(1, code);
            ResultSet rs = dataBase.executeSelect(SQL_SELECTALL);
            
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                    RendezVous rendezVous =new RendezVous(rs.getDate("date_rdv"),rs.getInt("id"),rs.getTime("time_rdv"),rs.getString("type_rdv"),rs.getString("status"));
                    rdvs.add(rendezVous);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
    return rdvs;        
    }

    @Override
    public List<RendezVous> findAll() {
        List<RendezVous> rdvs=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECTALLBYALL);
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECTALLBYALL);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    RendezVous rdv =new RendezVous(rs.getDate("date_rdv"),rs.getInt("id"),rs.getTime("time_rdv"),rs.getString("type_rdv"),rs.getString("status"),rs.getString("code_patient"));
                    rdvs.add(rdv);
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
        return rdvs;
    }

    public List<String> findCodePatientById(int id) {
        List<String> list=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_CODE_PATIENT_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_CODE_PATIENT_BY_ID);
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                      String code = rs.getString("code_patient");
                      if (!list.contains(code)){
                          list.add(code);
                      }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
    return list;        
    }
    public List<Patient> findCodePatientById2(int id) {
        List<Patient> list=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_CODE_PATIENT_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_CODE_PATIENT_BY_ID);
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                      Patient pat = new Patient(rs.getString("code_patient"),rs.getInt("id"));
                      if (!list.contains(pat)){
                          list.add(pat);
                      }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
    return list;        
    }
    public List<String> findCodePatient() {
        List<String> list=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_CODE_PATIENT);
        try {
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_CODE_PATIENT);
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                      String code = rs.getString("code_patient");
                      if (!list.contains(code)){
                          list.add(code);
                      }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
    return list;        
    }
    public List<Integer> findIdRdv() {
        List<Integer> list=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_ID_RDV);
        try {
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_ID_RDV);
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                      int id = rs.getInt("id");
                      list.add(id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
    return list;        
    }
    
    
    public List<Date> findAllByDate() {
        List<Date> date=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_DATE);
        ResultSet rs =dataBase.executeSelect(SQL_SELECT_DATE);
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    if (!date.contains(rs.getDate("date_rdv"))){
                          date.add(rs.getDate("date_rdv"));
                      }
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
        return date;
    }
    
    
    public List<RendezVous> findAllByDate(Date date) {
        //RendezVous rendezV=new RendezVous();
        List<RendezVous> rdvs=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_BY_DATE);
        try {
             dataBase.getPs().setDate(1, date);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_BY_DATE);
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                    RendezVous rendezVous =new RendezVous(rs.getDate("date_rdv"),rs.getInt("id"),rs.getTime("time_rdv"),rs.getString("type_rdv"),rs.getString("status"),rs.getString("code_patient"));
                    rdvs.add(rendezVous);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        
    return rdvs;        
    }

    @Override
    public RendezVous findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int findIDByCodePat(String code) {
        int id = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_ID_RDV_BY_CODE);
        try {
            dataBase.getPs().setString(1, code);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_ID_RDV_BY_CODE);
            while(rs.next())
            {
                //Mapping relation vers objet,rs.getDate("date_rdv")
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            dataBase.closeConnexion();
        }
        
    return id;        
    }
    
}
    
    

