package Entities;


public class Medicament {
    private int id;
    private String codeMedoc;
    private String nomMedoc;

    public Medicament(int id, String codeMedoc, String nomMedoc) {
        this.id = id;
        this.codeMedoc = codeMedoc;
        this.nomMedoc = nomMedoc;
    }

    public Medicament(String codeMedoc, String nomMedoc) {
        this.codeMedoc = codeMedoc;
        this.nomMedoc = nomMedoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeMedoc() {
        return codeMedoc;
    }

    public void setCodeMedoc(String codeMedoc) {
        this.codeMedoc = codeMedoc;
    }

    public String getNomMedoc() {
        return nomMedoc;
    }

    public void setNomMedoc(String nomMedoc) {
        this.nomMedoc = nomMedoc;
    }
    
    public String toString(){
        
        return nomMedoc;
    }
    
}
