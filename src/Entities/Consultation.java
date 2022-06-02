package Entities;

import java.sql.Date;
import java.sql.Time;




public class Consultation extends RendezVous {
    
    private int id_medecin;
    private String code_patient;
    private String status;
    private final String TYPE_CONSULTATION = "CONSULTATION";

    public Consultation() {
    }

    public Consultation(int id_medecin, Date date, Time heure, String type_rdv, String code_patient) {
        super(date, heure);
        super.setTypeRdv(TYPE_CONSULTATION);
        this.id_medecin = id_medecin;
        this.code_patient=code_patient;
        this.status="en attente de confirmation";
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Consultation(int id, Date date, Time heure, String typeRdv,int id_medecin, String code_patient) {
        super(date, heure, typeRdv);
        this.id_medecin = id_medecin;
        this.code_patient=code_patient;
        
    }
    
    public Consultation(int id, Date date, Time heure, String typeRdv,int id_medecin, String code_patient,String status) {
        super(date, heure, typeRdv);
        this.id_medecin = id_medecin;
        this.code_patient=code_patient;
        this.status=status;
        
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getCode_patient() {
        return code_patient;
    }

    public void setCode_patient(String code_patient) {
        this.code_patient = code_patient;
    }

    
  
    
    public int getNom_medecin() {
        return id_medecin;
    }


    public void setNom_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }
   
}
