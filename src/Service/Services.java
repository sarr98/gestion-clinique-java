package Service;

import Dao.ConsultationDao;
import Dao.MedecinDao;
import Dao.MedicamentDao;
import Dao.OrdonnanceDao;
import Dao.PatientDao;
import Dao.PrestationDao;
import Dao.RendezVousDao;
import Dao.UsersDao;
import Entities.Consultation;
import Entities.Medecin;
import Entities.Medicament;
import Entities.Ordonnance;
import Entities.Patient;
import Entities.Prestation;
import Entities.RendezVous;
import Entities.Users;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class Services implements Iservice{
    private UsersDao userdao = new UsersDao();
    private MedecinDao meddao=new MedecinDao();
    private RendezVousDao rdvDao = new RendezVousDao();
    private PatientDao patdao=new PatientDao();
    private ConsultationDao consdao=new ConsultationDao();
    private PrestationDao prestdao=new PrestationDao();
    private MedicamentDao medDao = new MedicamentDao();
    private OrdonnanceDao ordoDao = new OrdonnanceDao();

    @Override
    public Users seConnecter(String login, String password) {
        return userdao.findByLoginPassword(login, password);
        
    }

    @Override
    public int annulerConsultation(Consultation cons) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int annulerPrestation(RendezVous rdv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<RendezVous> SelectAllRendezVous(String code) {
        return rdvDao.findAllByCode(code);
    }

    @Override
    public List<Consultation> listerConsultation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient voirDetailPatient(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation voirDetailConsultation(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int creerCompte(String nom, String prenom, String login, String password, String mail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int demanderRendezVous(RendezVous rdv) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validerUnRdv(RendezVous rdv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifierDisponibiliter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> listerPrestation(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int voirDetailPrestation(Prestation prest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> selectionnerMedecin() {
        return meddao.findAll();
    }
    
    public boolean verifRDV(Medecin med , Date date, Time time, String type_rdv,Patient pat){
        return rdvDao.findAllByAll(med, type_rdv, date, time, pat);
    }
    
    public Patient getPatientByUser(Users user){
        return patdao.findByNomPrenom(user);
    }
    
    public int demanderConsultation(Consultation c) {
        
       System.out.println(c.getId_medecin());
       return consdao.insert(c);
    }
    
    public int demanderPrestation(Prestation p) {
        
       //System.out.println(c.getId());
       return prestdao.insert(p);
    }

    @Override
    public List<RendezVous> listerRendezVous() {
        return rdvDao.findAll();
    }
    
    public List<Date> listerDateRendezVous() {
        return rdvDao.findAllByDate();
    }
    
    public List<String> listCodePatientById(int id){
        return rdvDao.findCodePatientById(id);
    }
    
    public List<Patient> listCodePatientById2(int id){
        return rdvDao.findCodePatientById2(id);
    }
    
    public List<String> listCodePatient(){
        return rdvDao.findCodePatient();
    }
    
    public List<RendezVous> listerRdvParDate(Date date){
    return rdvDao.findAllByDate(date);
}
    
    public List<Integer> listIdRdv(){
        return rdvDao.findIdRdv();
    }
    
    
    public int changeStatusConfirm(int id){
        return rdvDao.updateStatusConfirm(id);
    }
    
    public int changeStatusAnnuler(int id){
        return rdvDao.updateStatusAnnuler(id);
    }
    
    public Users findUserByCode(String code){
        return userdao.findByCodePatient(code);
    }
    
    public List<RendezVous> SearchMedecinByIdAndDate(int id, Date date){
        return rdvDao.findByIdMedecin(id,date);
    }
    
    public List<Medicament> listerMedicament(){
        return medDao.findAll();
    }
    
    public int SaveOrdonnance(Ordonnance ord){
        return ordoDao.insert(ord);
    }
    
    public int SelectIdByCode(String code){
        return rdvDao.findIDByCodePat(code);
    }
    
    public List<Ordonnance> selectOrdonnance(int id, String code){
        return ordoDao.findAll2(id, code);
    }
    
    public Date lastRdv(String code){
        List<RendezVous> rdv =rdvDao.findAllByCode(code);
        Date dateRecente =rdv.get(0).getDate();
        for(RendezVous o:rdv){
            if (o.getDate().after(dateRecente) || o.getDate().before(Date.valueOf(java.time.LocalDate.now())) ){
                dateRecente = o.getDate();
            }
            
        }
        return dateRecente;
    }
       
    
    
}

