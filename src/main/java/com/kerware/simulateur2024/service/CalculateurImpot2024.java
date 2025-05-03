package com.kerware.simulateur2024.service;

import com.kerware.simulateur2024.modele.BaremeFiscal;
import com.kerware.simulateur2024.modele.FoyerFiscal;
import com.kerware.simulateur2024.modele.ResultatCalculImpot;
import com.kerware.simulateur2024.modele.SituationFamiliale;

/**
 * Implémentation du calculateur d'impôt sur le revenu pour 2024.
 * Décompose le calcul de l'impôt en plusieurs étapes distinctes pour la maintenabilité.
 */
public class CalculateurImpot2024 implements ICalculateurImpot2024 {

    private final BaremeFiscal baremeFiscal;
    private ResultatCalculImpot resultatCalcul;
    
    /**
     * Constructeur utilisant le barème fiscal par défaut.
     */
    public CalculateurImpot2024() {
        this(BaremeFiscalFactory.creerBareme2024());
    }
    
    /**
     * Constructeur permettant de chosir un barème différent (maintenabilité).
     * 
     * @param baremeFiscal Le barème fiscal à utiliser pour le calcul
     */
    public CalculateurImpot2024(BaremeFiscal baremeFiscal) {
        this.baremeFiscal = baremeFiscal;
    }
    
    @Override
    public ResultatCalculImpot calculerImpot(FoyerFiscal foyerFiscal){

        if (!foyerFiscal.estValide()) {
            throw new IllegalArgumentException("Le foyer fiscal n'est pas valide");
        }
        
        resultatCalcul = new ResultatCalculImpot(foyerFiscal);
        
        calculerAbattement();
        
        calculerRevenuFiscalReference();
        
        calculerNombrePartsFiscales();
        
        // EXIGENCE : EXG_IMPOT_07
        calculerContributionExceptionnelle();
        
        calculerImpotBrutDeclarants();
        
        calculerImpotBrutFoyer();
        
        // EXIGENCE : EXG_IMPOT_05
        appliquerPlafonnementQuotientFamilial();
        
        // EXIGENCE : EXG_IMPOT_06
        calculerDecote();
        
        calculerImpotNet();
        
        return resultatCalcul;
    }
    
    @Override
    public ResultatCalculImpot getResultatCalcul() {
        return resultatCalcul;
    }
    
    /**
     * Calcule l'abattement pour chaque déclarant du foyer fiscal.
     * EXIGENCE : EXG_IMPOT_02
     */
    private void calculerAbattement() {
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        int abattementDecl1 = baremeFiscal.calculerAbattement(foyer.getRevenuNetDeclarant1());
        int abattementDecl2 = 0;
        
        if(foyer.getSituationFamiliale() == SituationFamiliale.MARIE || foyer.getSituationFamiliale()== SituationFamiliale.PACSE){
            abattementDecl2 = baremeFiscal.calculerAbattement(foyer.getRevenuNetDeclarant2());
        }
        
        int abattementTotal = abattementDecl1 + abattementDecl2;
        resultatCalcul.setAbattement(abattementTotal);
    }
    
    /**
     * Calcule le revenu fiscal de référence.
     */
    private void calculerRevenuFiscalReference() {
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        int revenuNetGlobal = foyer.getRevenuNetGlobal();
        int abattement = resultatCalcul.getAbattement();
        
        int revenuFiscalReference = Math.max(0, revenuNetGlobal - abattement);
        resultatCalcul.setRevenuFiscalReference(revenuFiscalReference);
    }
    
    /**
     * Calcule le nombre de parts fiscales du foyer.
     * EXIGENCE : EXG_IMPOT_03
     */
    private void calculerNombrePartsFiscales(){
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        SituationFamiliale situation = foyer.getSituationFamiliale();
        int nbEnfants = foyer.getNbEnfantsACharge();
        int nbEnfantsHandicap = foyer.getNbEnfantsSituationHandicap();
        boolean parentIsole = foyer.isParentIsole();
        
        double nbParts = situation.getNbPartsFiscales();
        
        if (nbEnfants <= 2) {
            nbParts += nbEnfants * 0.5;
        } else {
            // À partir du 3ème enfant, chaque enfant -> 1 part
            nbParts += 1.0 + (nbEnfants - 2);
        }
        
        // Majoration pour parent isolé
        if(parentIsole && nbEnfants > 0){
            nbParts += 0.5;
        }
        
        // Majoration pour veuf avec enfants
        if (situation == SituationFamiliale.VEUF && nbEnfants > 0) {
            nbParts += 1.0;
        }
        
        // Majoration pour enfants en situation de handicap
        nbParts += nbEnfantsHandicap * 0.5;
        
        resultatCalcul.setNbPartsFiscales(nbParts);
    }
    
    /**
     * Calcule la contribution exceptionnelle sur les hauts revenus.
     * EXIGENCE : EXG_IMPOT_07
     */
    private void calculerContributionExceptionnelle(){
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        boolean estCouple = foyer.getSituationFamiliale() == SituationFamiliale.MARIE|| foyer.getSituationFamiliale() == SituationFamiliale.PACSE;
        
        double revenuFiscalReference = resultatCalcul.getRevenuFiscalReference();
        double contributionExceptionnelle = baremeFiscal.calculerContributionExceptionnelle(revenuFiscalReference, estCouple);
        
        resultatCalcul.setContributionExceptionnelle(Math.round(contributionExceptionnelle));
    }
    
    /**
     * Calcule l'impôt brut des déclarants (sans prise en compte des enfants).
     * EXIGENCE : EXG_IMPOT_04
     */
    private void calculerImpotBrutDeclarants() {
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        double nbPartsDeclarants = foyer.getSituationFamiliale().getNbPartsFiscales();
        int revenuFiscalReference = resultatCalcul.getRevenuFiscalReference();
        
        double revenuImposableParPart = revenuFiscalReference / nbPartsDeclarants;
        double impotParPart = baremeFiscal.calculerImpot(revenuImposableParPart);
        double impotBrutDeclarants = Math.round(impotParPart * nbPartsDeclarants);
        
        resultatCalcul.setImpotBrutDeclarants(impotBrutDeclarants);
    }
    
    /**
     * Calcule l'impôt brut du foyer fiscal.
     * EXIGENCE : EXG_IMPOT_04
     */
    private void calculerImpotBrutFoyer(){
        double nbPartsFiscales = resultatCalcul.getNbPartsFiscales();
        int revenuFiscalReference = resultatCalcul.getRevenuFiscalReference();
        
        double revenuImposableParPart = revenuFiscalReference / nbPartsFiscales;
        double impotParPart = baremeFiscal.calculerImpot(revenuImposableParPart);
        double impotBrutFoyer = Math.round(impotParPart * nbPartsFiscales);
        
        resultatCalcul.setImpotBrutFoyer(impotBrutFoyer);
    }
    
    /**
     * Applique le plafonnement du quotient familial.
     * EXIGENCE : EXG_IMPOT_05
     */
    private void appliquerPlafonnementQuotientFamilial() {
        double impotBrutDeclarants = resultatCalcul.getImpotBrutDeclarants();
        double impotBrutFoyer = resultatCalcul.getImpotBrutFoyer();
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        
        double nbPartsDeclarants = foyer.getSituationFamiliale().getNbPartsFiscales();
        double nbPartsFiscales = resultatCalcul.getNbPartsFiscales();
        
        double reductionImpot = impotBrutDeclarants - impotBrutFoyer;
        
        // Pas de réduction => pas de plafonnement
        if (reductionImpot <= 0) {
            resultatCalcul.setImpotAvantDecote(impotBrutFoyer);
            return;
        }
        
        // Plafond de réduction autorisé
        double ecartParts = nbPartsFiscales - nbPartsDeclarants;
        double plafondReduction = (ecartParts / 0.5) * baremeFiscal.getPlafonnementDemiPart();
        
        // Plafonnement si nécessaire
        if (reductionImpot > plafondReduction) {
            double impotAvecPlafond = impotBrutDeclarants - plafondReduction;
            resultatCalcul.setImpotAvantDecote(impotAvecPlafond);
        } else {
            resultatCalcul.setImpotAvantDecote(impotBrutFoyer);
        }
    }
    
    /**
     * Calcule la décote sur l'impôt.
     * EXIGENCE : EXG_IMPOT_06
     */
    private void calculerDecote(){
        double impotAvantDecote = resultatCalcul.getImpotAvantDecote();
        FoyerFiscal foyer = resultatCalcul.getFoyerFiscal();
        
        boolean estCouple = foyer.getSituationFamiliale() == SituationFamiliale.MARIE || foyer.getSituationFamiliale() == SituationFamiliale.PACSE;
        
        double decote = baremeFiscal.calculerDecote(impotAvantDecote, estCouple);
        resultatCalcul.setDecote(decote);
    }
    
    /**
     * Calcule l'impôt net final.
     */
    private void calculerImpotNet() {
        double impotAvantDecote = resultatCalcul.getImpotAvantDecote();
        double decote = resultatCalcul.getDecote();
        double contributionExceptionnelle = resultatCalcul.getContributionExceptionnelle();
        
        double impotApresDecote = impotAvantDecote - decote;
        double impotNet = Math.round(impotApresDecote + contributionExceptionnelle);
        
        // Impossible d'avoir un impôt négatif
        resultatCalcul.setImpotNet((int) Math.max(0, impotNet));
    }
}