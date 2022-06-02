package Entities;


public class Medecin extends Users {
    private String specialite;
    private final String ROLE_MEDECIN = "MEDECIN";

    public Medecin(String specialite, int id, String nom, String mail, String prenom, String login, String password, String role, int tel) {
        super(id, nom, mail, prenom, login, password, role, tel);
        this.specialite = specialite;
    }

    public Medecin(String specialite, String nom, String mail, String prenom, String login, String password, int tel) {
        super(nom, mail, prenom, login, password, tel);
        this.specialite = specialite;
        super.role=this.ROLE_MEDECIN;
    }

    public Medecin(String specialite, int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.specialite = specialite;
    }

    

    public void setRole() {
        this.role = ROLE_MEDECIN;
    }

    

    public Medecin() {
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
    public String toString(){
        
        return super.nom +" "+  super.prenom + " "+  specialite;
    }
    
    
}
