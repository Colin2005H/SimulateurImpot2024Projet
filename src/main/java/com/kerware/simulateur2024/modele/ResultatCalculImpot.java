package com.kerware.simulateur2024.modele;

/**
 * Stocke les différentes valeurs calculées pour un foyer fiscal.
 */
public class ResultatCalculImpot{
    
    private final FoyerFiscal foyerFiscal;
    
    private int abattement;
    private int revenuFiscalReference;
    private double nbPartsFiscales;
    private double impotBrutDeclarants;
    private double impotBrutFoyer;
    private double impotAvantDecote;
    private double decote;
    private double contributionExceptionnelle;
    
    private int impotNet;
    
    /**
     * 
     * @param foyerFiscal Le foyer fiscal concerné par ce calcul
     */
    public ResultatCalculImpot(FoyerFiscal foyerFiscal){
        this.foyerFiscal = foyerFiscal;
    }

    
    public FoyerFiscal getFoyerFiscal() {
        return foyerFiscal;
    }
    
    public int getAbattement() {
        return abattement;
    }
    
    public void setAbattement(int abattement) {
        this.abattement = abattement;
    }
    
    public int getRevenuFiscalReference() {
        return revenuFiscalReference;
    }
    
    public void setRevenuFiscalReference(int revenuFiscalReference) {
        this.revenuFiscalReference = revenuFiscalReference;
    }
    
    public double getNbPartsFiscales() {
        return nbPartsFiscales;
    }
    
    public void setNbPartsFiscales(double nbPartsFiscales) {
        this.nbPartsFiscales = nbPartsFiscales;
    }
    
    public double getImpotBrutDeclarants() {
        return impotBrutDeclarants;
    }
    
    public void setImpotBrutDeclarants(double impotBrutDeclarants) {
        this.impotBrutDeclarants = impotBrutDeclarants;
    }
    
    public double getImpotBrutFoyer() {
        return impotBrutFoyer;
    }
    
    public void setImpotBrutFoyer(double impotBrutFoyer) {
        this.impotBrutFoyer = impotBrutFoyer;
    }
    
    public double getImpotAvantDecote() {
        return impotAvantDecote;
    }
    
    public void setImpotAvantDecote(double impotAvantDecote) {
        this.impotAvantDecote = impotAvantDecote;
    }
    
    public double getDecote() {
        return decote;
    }
    
    public void setDecote(double decote) {
        this.decote = decote;
    }
    
    public double getContributionExceptionnelle() {
        return contributionExceptionnelle;
    }
    
    public void setContributionExceptionnelle(double contributionExceptionnelle) {
        this.contributionExceptionnelle = contributionExceptionnelle;
    }
    
    public int getImpotNet() {
        return impotNet;
    }
    
    public void setImpotNet(int impotNet) {
        this.impotNet = impotNet;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Résultat du calcul d'impôt:\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Revenu net declarant1 : ").append(foyerFiscal.getRevenuNetDeclarant1()).append("\n");
        sb.append("Revenu net declarant2 : ").append(foyerFiscal.getRevenuNetDeclarant2()).append("\n");
        sb.append("Situation familiale : ").append(foyerFiscal.getSituationFamiliale()).append("\n");
        sb.append("Abattement : ").append(abattement).append("\n");
        sb.append("Revenu fiscal de référence : ").append(revenuFiscalReference).append("\n");
        sb.append("Nombre d'enfants  : ").append(foyerFiscal.getNbEnfantsACharge()).append("\n");
        sb.append("Nombre d'enfants handicapés : ").append(foyerFiscal.getNbEnfantsSituationHandicap()).append("\n");
        sb.append("Parent isolé : ").append(foyerFiscal.isParentIsole()).append("\n");
        sb.append("Nombre de parts : ").append(nbPartsFiscales).append("\n");
        sb.append("Contribution exceptionnelle sur les hauts revenus : ").append(contributionExceptionnelle).append("\n");
        sb.append("Impôt brut des déclarants : ").append(impotBrutDeclarants).append("\n");
        sb.append("Impôt brut du foyer fiscal complet : ").append(impotBrutFoyer).append("\n");
        sb.append("Impôt brut après plafonnement avant decote : ").append(impotAvantDecote).append("\n");
        sb.append("Decote : ").append(decote).append("\n");
        sb.append("Impôt sur le revenu net final : ").append(impotNet).append("\n");
        return sb.toString();
    }
}