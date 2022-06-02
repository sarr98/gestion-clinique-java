package Entities;

public class Users {
    
    protected int id;
    protected String nom;
    protected String mail;
    protected String prenom;
    protected String login;
    protected String password;
    protected String role;
    protected int tel;
    private String code;
    private String AntecedantMedicaux;

    public String getAntecedantMedicaux() {
        return AntecedantMedicaux;
    }

    public void setAntecedantMedicaux(String AntecedantMedicaux) {
        this.AntecedantMedicaux = AntecedantMedicaux;
    }

    public String getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public Users(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    

    public int getId() {
        return id;
    }

    public Users(String nom, String mail, String prenom, String login, String password, String role, int tel) {
        this.nom = nom;
        this.mail = mail;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.tel = tel;
    }

    public Users(int id, String nom, String mail, String prenom, String login, String password, String role, int tel) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.tel = tel;
    }
    public Users(int id, String nom, String mail, String prenom, String login, String password, String role, int tel,String code) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.tel = tel;
        this.code = code;
    }
    
    public Users(int id, String nom, String mail, String prenom, String login, String password, String role, int tel,String code,String antecedant) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.tel = tel;
        this.code = code;
        this.AntecedantMedicaux=antecedant;
    }

    public Users(int id) {
        this.id = id;
    }

    public Users(String nom, String mail, String prenom, String login, String password, int tel) {
        this.nom = nom;
        this.mail = mail;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.tel = tel;
    }

    

    public Users() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    
    
    
}
