package Entities;

import java.sql.Date;
import java.sql.Time;

public class RendezVous {
     private Date date;
     private int id;
     private Time heure;
     private String typeRdv;
     private  String status;
     private String codePat;

    public String getCodePat() {
        return codePat;
    }

    public void setCodePat(String codePat) {
        this.codePat = codePat;
    }
     

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RendezVous(Date date, int id, Time heure, String typeRdv, String status) {
        this.date = date;
        this.id = id;
        this.heure = heure;
        this.typeRdv = typeRdv;
        this.status = status;
    }

    public RendezVous(Date date, int id, Time heure, String typeRdv, String status, String codePat) {
        this.date = date;
        this.id = id;
        this.heure = heure;
        this.typeRdv = typeRdv;
        this.status = status;
        this.codePat = codePat;
    }

   


    public RendezVous(Date date, int id, Time heure,String typeRdv) {
        this.date = date;
        this.id = id;
        this.heure = heure;
        this.typeRdv=typeRdv;
    }

    public String getTypeRdv() {
        return typeRdv;
    }

    public void setTypeRdv(String typeRdv) {
        this.typeRdv = typeRdv;
    }

    public RendezVous(Date date, Time heure) {
        this.date = date;
        this.heure = heure;
    }

    public RendezVous(Date date, Time heure,String typeRdv) {
        this.date = date;
        this.heure = heure;
        this.typeRdv=typeRdv;
    }

    public RendezVous() {
    }
     
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }
    
    public String toString(){
        
        return id +" "+  date + " "+  heure + " "+ typeRdv;
    }
     
    
}
