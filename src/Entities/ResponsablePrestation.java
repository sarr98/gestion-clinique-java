
package Entities;


public class ResponsablePrestation extends Users{
    private final String ROLE_RPRESTATION = "RESPONSBALE PRESTATION";

    public void setRole() {
        this.role = ROLE_RPRESTATION;
    }

    public ResponsablePrestation() {
    }

    public ResponsablePrestation(String nom, String mail, String prenom, String login, String password, int tel) {
        super(nom, mail, prenom, login, password, tel);
        super.role=this.ROLE_RPRESTATION;
    }

    public ResponsablePrestation(int id, String nom, String mail, String prenom, String login, String password, String role, int tel) {
        super(id, nom, mail, prenom, login, password, role, tel);
    }

    
    
    
    
    
    
    
}
