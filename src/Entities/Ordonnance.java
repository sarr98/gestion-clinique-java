package Entities;


public class Ordonnance {
    private int id;
    private String code;
    private String codePatient;
    private int idConsultation;

    public Ordonnance(String codePatient, int id_medecin) {
        this.codePatient = codePatient;
        this.id_medecin = id_medecin;
    }
    

    public Ordonnance(String codePatient, int idConsultation, int id_medecin, String codeMedicament, String posologie) {
        this.codePatient = codePatient;
        this.idConsultation = idConsultation;
        this.id_medecin = id_medecin;
        this.codeMedicament = codeMedicament;
        this.posologie = posologie;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Ordonnance(String code, String codePatient, int id_medecin, String codeMedicament, String posologie) {
        this.code = code;
        this.codePatient = codePatient;
        this.id_medecin = id_medecin;
        this.codeMedicament = codeMedicament;
        this.posologie = posologie;
    }

    public Ordonnance(String codePatient, int id_medecin, String codeMedicament, String posologie) {
        this.codePatient = codePatient;
        this.id_medecin = id_medecin;
        this.codeMedicament = codeMedicament;
        this.posologie = posologie;
    }
    private int id_medecin;
    private String codeMedicament;
    private String posologie;
    private String nomMedocs;

    public Ordonnance(String codeMedicament, String posologie) {
        this.codeMedicament = nomMedocs;
        this.posologie = posologie;
    }

    public String getNomMedocs() {
        return nomMedocs;
    }

    public void setNomMedocs(String nomMedocs) {
        this.nomMedocs = nomMedocs;
    }

    public Ordonnance(int id, String code, String nomMedicament, String posologie) {
        this.id = id;
        this.code = code;
        this.codeMedicament = nomMedicament;
        this.posologie = posologie;
    }

    public Ordonnance(String code, String nomMedicament, String posologie) {
        this.code = code;
        this.codeMedicament = nomMedicament;
        this.posologie = posologie;
    }

    public Ordonnance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(String codePatient) {
        this.codePatient = codePatient;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getCodeMedicament() {
        return codeMedicament;
    }

    public void setCodeMedicament(String codeMedicament) {
        this.codeMedicament = codeMedicament;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }
    
    public String toString(){
        
        return nomMedocs +" "+  posologie;
    }
    
   
    
}
