package Entities;


public class Secretaire extends Users {

   
    private final String ROLE_SECRETAIRE= "SECRETAIRE";
    
     public void setRole() {
        this.role = ROLE_SECRETAIRE;
    }

    public Secretaire(String nom, String mail, String prenom, String login, String password, int tel) {
        super(nom, mail, prenom, login, password, tel);
        super.role = ROLE_SECRETAIRE;
    }

    public Secretaire(int id, String nom, String mail, String prenom, String login, String password, String role, int tel) {
        super(id, nom, mail, prenom, login, password, role, tel);
    }

    public Secretaire() {
    }
    
    
    
}
