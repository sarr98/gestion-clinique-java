package Entities;

import java.sql.Date;
import java.sql.Time;


public class Prestation extends RendezVous {
    private String typePrestation;
    private String code_patient;
    private final String TYPE_PRESTATION = "PRESTATION";
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    
    public Prestation() {
    }

    public Prestation(String typePrestation, Date date, Time heure, String code) {
        super(date, heure);
        super.setTypeRdv(TYPE_PRESTATION);
        this.typePrestation = typePrestation;
        this.code_patient=code;
        this.status="en attente de confirmation";
    }

    public String getCode_patient() {
        return code_patient;
    }

    public Prestation(String typePrestation, Date date, int id, Time heure,String typeRdv) {
        super(date, id, heure,typeRdv);
        this.typePrestation = typePrestation;
    }


    public String getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(String typePrestation) {
        this.typePrestation = typePrestation;
    }
    
    
    
}
