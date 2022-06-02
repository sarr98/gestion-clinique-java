/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Consultation;
import Entities.Medecin;
import Entities.Patient;
import Entities.Prestation;
import Entities.RendezVous;
import Entities.Users;
import java.sql.Date;
import java.util.List;


public interface Iservice {
    //Fonctionnalité générique
    public Users seConnecter(String login, String password);
    
    //Fonctionnalité medecin/patient
    public int annulerConsultation(Consultation cons);
    
    //Fonctionnalité Responsable Prestation/Patient
    public int annulerPrestation(RendezVous rdv);
    
    //Fonctionnalité Medecin
    public List<RendezVous> listerRendezVous();
    public List<Consultation> listerConsultation();
    public Patient voirDetailPatient(int id);
    public Consultation voirDetailConsultation(int id);
    
    
    //Fonctionnalité Patient
    public int creerCompte(String nom, String prenom, String login, String password, String mail);
    public int demanderRendezVous(RendezVous rdv);
    public List<Medecin> selectionnerMedecin();
    
    
    //Fonctionnalité Secretaire
 
    
    public boolean validerUnRdv(RendezVous rdv);
    public boolean verifierDisponibiliter();
    
    
    //Fonctionnalité Responsable Prestation
    
    public List<Prestation> listerPrestation(Date date);
    public int voirDetailPrestation(Prestation prest);
 
    
}
