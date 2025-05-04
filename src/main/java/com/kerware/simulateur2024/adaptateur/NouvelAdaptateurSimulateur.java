package com.kerware.simulateur2024.adaptateur;

import com.kerware.simulateur.ICalculateurImpot;
import com.kerware.simulateur.SituationFamiliale;
import com.kerware.simulateur2024.modele.FoyerFiscal;
import com.kerware.simulateur2024.modele.ResultatCalculImpot;
import com.kerware.simulateur2024.service.CalculateurImpot2024;
import com.kerware.simulateur2024.service.ICalculateurImpot2024;

/**
 * Adaptateur permettant d'utiliser le nouveau calculateur d'impôt réusiné.
 */
public class NouvelAdaptateurSimulateur implements ICalculateurImpot {
    
    private final ICalculateurImpot2024 calculateur;
    private FoyerFiscal foyerFiscal;
    private ResultatCalculImpot resultat;
    
    /**
     * Crée un nouvel adaptateur avec le calculateur réusiné.
     */
    public NouvelAdaptateurSimulateur() {
        this.calculateur = new CalculateurImpot2024();
        this.foyerFiscal = new FoyerFiscal();
        this.resultat = null;
    }
    
    @Override
    public void setRevenusNetDeclarant1(int revenu) {
        foyerFiscal.setRevenuNetDeclarant1(revenu);
    }
    
    @Override
    public void setRevenusNetDeclarant2(int revenu) {
        foyerFiscal.setRevenuNetDeclarant2(revenu);
    }
    
    @Override
    public void setSituationFamiliale(SituationFamiliale situationFamiliale){
        if(situationFamiliale ==null) {
            foyerFiscal.setSituationFamiliale(null);
            return;
        }
        
        // Liaison entre l'ancienne et la nouvelle énumération
        switch (situationFamiliale) {
            case CELIBATAIRE:
                foyerFiscal.setSituationFamiliale(com.kerware.simulateur2024.modele.SituationFamiliale.CELIBATAIRE);
                break;
            case MARIE:
                foyerFiscal.setSituationFamiliale(com.kerware.simulateur2024.modele.SituationFamiliale.MARIE);
                break;
            case DIVORCE:
                foyerFiscal.setSituationFamiliale(com.kerware.simulateur2024.modele.SituationFamiliale.DIVORCE);
                break;
            case VEUF:
                foyerFiscal.setSituationFamiliale(com.kerware.simulateur2024.modele.SituationFamiliale.VEUF);
                break;
            case PACSE:
                foyerFiscal.setSituationFamiliale(com.kerware.simulateur2024.modele.SituationFamiliale.PACSE);
                break;
            default:
                throw new IllegalArgumentException("Situation familiale non défini");
        }
    }
    
    @Override
    public void setNbEnfantsACharge(int nbEnfants) {
        foyerFiscal.setNbEnfantsACharge(nbEnfants);
    }
    
    @Override
    public void setNbEnfantsSituationHandicap(int nbEnfantsHandicap) {
        foyerFiscal.setNbEnfantsSituationHandicap(nbEnfantsHandicap);
    }
    
    @Override
    public void setParentIsole(boolean parentIsole) {
        foyerFiscal.setParentIsole(parentIsole);
    }
    
    @Override
    public void calculImpotSurRevenuNet() {
        resultat = calculateur.calculerImpot(foyerFiscal);
    }
    
    @Override
    public int getRevenuNetDeclatant1() {
        return foyerFiscal.getRevenuNetDeclarant1();
    }
    
    @Override
    public int getRevenuNetDeclatant2() {
        return foyerFiscal.getRevenuNetDeclarant2();
    }
    
    @Override
    public int getRevenuFiscalReference() {
        return resultat.getRevenuFiscalReference();
    }
    
    @Override
    public int getAbattement() {
        return resultat.getAbattement();
    }
    
    @Override
    public double getNbPartsFoyerFiscal() {
        return resultat.getNbPartsFiscales();
    }
    
    @Override
    public int getImpotAvantDecote() {
        return (int) resultat.getImpotAvantDecote();
    }
    
    @Override
    public int getDecote() {
        return (int) resultat.getDecote();
    }
    
    @Override
    public double getContribExceptionnelle() {
        return resultat.getContributionExceptionnelle();
    }
    
    @Override
    public int getImpotSurRevenuNet() {
        return resultat.getImpotNet();
    }
}