package com.kerware.simulateur;

/**
 * Adaptateur permettant d'utiliser le Simulateur d'impôt via l'interface 
 * ICalculateurImpot.
 */
public final class AdaptateurSimulateur implements ICalculateurImpot {

    /**
     * Instance du simulateur utilisé par cet adaptateur.
     */
    private Simulateur simulateur = new Simulateur();

    /**
     * Revenus nets du déclarant 1.
     */
    private int revenusNetDecl1 = 0;

    /**
     * Revenus nets du déclarant 2.
     */
    private int revenusNetDecl2 = 0;

    /**
     * Situation familiale du contribuable.
     */
    private SituationFamiliale situationFamiliale;

    /**
     * Nombre d'enfants à charge.
     */
    private int nbEnfantsACharge;

    /**
     * Nombre d'enfants en situation de handicap.
     */
    private int nbEnfantsSituationHandicap;

    /**
     * Indique si le contribuable est parent isolé.
     */
    private boolean parentIsole;

    @Override
    public void setRevenusNetDeclarant1(final int rn) {
        this.revenusNetDecl1 = rn;
    }

    @Override
    public void setRevenusNetDeclarant2(final int rn) {
        this.revenusNetDecl2 = rn;
    }

    @Override
    public void setSituationFamiliale(final SituationFamiliale sf) {
        this.situationFamiliale = sf;
    }

    @Override
    public void setNbEnfantsACharge(final int nbe) {
        this.nbEnfantsACharge = nbe;
    }

    @Override
    public void setNbEnfantsSituationHandicap(final int nbesh) {
        this.nbEnfantsSituationHandicap = nbesh;
    }

    @Override
    public void setParentIsole(final boolean pi) {
        this.parentIsole = pi;
    }

    @Override
    public void calculImpotSurRevenuNet() {
        simulateur.calculImpot(
                revenusNetDecl1, 
                revenusNetDecl2, 
                situationFamiliale, 
                nbEnfantsACharge, 
                nbEnfantsSituationHandicap, 
                parentIsole);
    }

    @Override
    public int getRevenuNetDeclatant1() {
        return revenusNetDecl1;
    }

    @Override
    public int getRevenuNetDeclatant2() {
        return revenusNetDecl2;
    }

    @Override
    public double getContribExceptionnelle() {
        return simulateur.getContribExceptionnelle();
    }

    @Override
    public int getRevenuFiscalReference() {
        return (int) simulateur.getRevenuReference();
    }

    @Override
    public int getAbattement() {
        return (int) simulateur.getAbattement();
    }

    @Override
    public double getNbPartsFoyerFiscal() {
        return simulateur.getNbParts();
    }

    @Override
    public int getImpotAvantDecote() {
        return (int) simulateur.getImpotAvantDecote();
    }

    @Override
    public int getDecote() {
        return (int) simulateur.getDecote();
    }

    @Override
    public int getImpotSurRevenuNet() {
        return (int) simulateur.getImpotNet();
    }
    
    @Override
    public void printResultatSimulation() {
    	simulateur.calculImpot(revenusNetDecl1, revenusNetDecl2, situationFamiliale, nbEnfantsSituationHandicap, nbEnfantsACharge, parentIsole);
    }
}
