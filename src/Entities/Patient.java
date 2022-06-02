package Entities;


public class Patient extends Users{
    private static int nbrPatient;
    private String domicile;
    private String code;
    private String antecedantMedicaux;
    private static final String ROLE_PATIENT = "PATIENT";
     private final static int FORMAT = 4;
    
    

    public Patient(String domicile, String code, String antecedantMedicaux, int id, String nom, String mail, String prenom, String login, String password, String role, int tel) {
        super(id, nom, mail, prenom, login, password, role, tel);
        this.domicile = domicile;
        this.code = code;
        this.antecedantMedicaux = antecedantMedicaux;
    }

    public Patient(String domicile, String code, String antecedantMedicaux, String nom, String mail, String prenom, String login, String password, String role, int tel) {
        super(nom, mail, prenom, login, password, role, tel);
        this.domicile = domicile;
        this.code = code;
        this.antecedantMedicaux = antecedantMedicaux;
    }

    public Patient(String code, int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.code = code;
    }

    public void setRole() {
        this.role = ROLE_PATIENT;
    }

    

    public Patient() {
    }

    public String getDomicile() {
        return domicile;
    }

    public Patient(String domicile, String antecedantMedicaux, String nom, String mail, String prenom, String login, String password, int tel, String role) {
        super(nom, mail, prenom, login, password, tel);
        setCode(generateNumber());
        this.domicile = domicile;
        this.antecedantMedicaux = antecedantMedicaux;
        super.role=ROLE_PATIENT;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }
    
    private static String generateNumber() {
        String nombreZero = "PATIENT";
        nbrPatient++;
        String nombreDePatientString = String.valueOf(nbrPatient);
        for (int i = 1; i <= (FORMAT - nombreDePatientString.length()); i++) {
            nombreZero += "0";
        }
        return nombreZero + nombreDePatientString;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAntecedantMedicaux() {
        return antecedantMedicaux;
    }

    public void setAntecedantMedicaux(String antecedantMedicaux) {
        this.antecedantMedicaux = antecedantMedicaux;
    }

    public String getRole() {
        return role;
    }
    public Patient(String code, int id) {
        super(id);
        this.code = code;
    }
    
}
