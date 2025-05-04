package com.kerware.simulateur2024.modele;

/**
 * Stocke les différentes valeurs calculées pour un foyer fiscal.
 */
public final class ResultatCalculImpot {

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
     * Constructeur du résultat de calcul d'impôt.
     *
     * @param foyerFiscal Le foyer fiscal concerné par ce calcul
     */
    public ResultatCalculImpot(final FoyerFiscal foyerFiscal) {
        this.foyerFiscal = foyerFiscal;
    }

    /**
     * Retourne le foyer fiscal concerné par ce résultat de calcul.
     *
     * @return Le foyer fiscal
     */
    public FoyerFiscal getFoyerFiscal() {
        return foyerFiscal;
    }

    /**
     * Retourne l'abattement appliqué sur le revenu brut.
     *
     * @return L'abattement en euros
     */
    public int getAbattement() {
        return abattement;
    }

    /**
     * Définit l'abattement appliqué sur le revenu brut.
     *
     * @param abattement L'abattement en euros
     */
    public void setAbattement(final int abattement) {
        this.abattement = abattement;
    }

    /**
     * Retourne le revenu fiscal de référence.
     *
     * @return Le revenu fiscal de référence en euros
     */
    public int getRevenuFiscalReference() {
        return revenuFiscalReference;
    }

    /**
     * Définit le revenu fiscal de référence.
     *
     * @param revenuFiscalReference Le revenu fiscal de référence en euros
     */
    public void setRevenuFiscalReference(final int revenuFiscalReference) {
        this.revenuFiscalReference = revenuFiscalReference;
    }

    /**
     * Retourne le nombre de parts fiscales du foyer.
     *
     * @return Le nombre de parts fiscales
     */
    public double getNbPartsFiscales() {
        return nbPartsFiscales;
    }

    /**
     * Définit le nombre de parts fiscales du foyer.
     *
     * @param nbPartsFiscales Le nombre de parts fiscales
     */
    public void setNbPartsFiscales(final double nbPartsFiscales) {
        this.nbPartsFiscales = nbPartsFiscales;
    }

    /**
     * Retourne l'impôt brut cumulé des déclarants avant application
     * des parts fiscales et autres réductions.
     *
     * @return L'impôt brut des déclarants
     */
    public double getImpotBrutDeclarants() {
        return impotBrutDeclarants;
    }

    /**
     * Définit l'impôt brut cumulé des déclarants.
     *
     * @param impotBrutDeclarants L'impôt brut des déclarants
     */
    public void setImpotBrutDeclarants(final double impotBrutDeclarants) {
        this.impotBrutDeclarants = impotBrutDeclarants;
    }

    /**
     * Retourne l'impôt brut du foyer fiscal après application des parts fiscales.
     *
     * @return L'impôt brut du foyer fiscal
     */
    public double getImpotBrutFoyer() {
        return impotBrutFoyer;
    }

    /**
     * Définit l'impôt brut du foyer fiscal.
     *
     * @param impotBrutFoyer L'impôt brut du foyer fiscal
     */
    public void setImpotBrutFoyer(final double impotBrutFoyer) {
        this.impotBrutFoyer = impotBrutFoyer;
    }

    /**
     * Retourne l'impôt avant application de la décote.
     *
     * @return L'impôt avant décote en euros
     */
    public double getImpotAvantDecote() {
        return impotAvantDecote;
    }

    /**
     * Définit l'impôt avant application de la décote.
     *
     * @param impotAvantDecote L'impôt avant décote en euros
     */
    public void setImpotAvantDecote(final double impotAvantDecote) {
        this.impotAvantDecote = impotAvantDecote;
    }

    /**
     * Retourne le montant de la décote appliquée à l'impôt.
     *
     * @return La décote en euros
     */
    public double getDecote() {
        return decote;
    }

    /**
     * Définit le montant de la décote appliquée à l'impôt.
     *
     * @param decote La décote en euros
     */
    public void setDecote(final double decote) {
        this.decote = decote;
    }

    /**
     * Retourne le montant de la contribution exceptionnelle sur les hauts revenus.
     *
     * @return La contribution exceptionnelle en euros
     */
    public double getContributionExceptionnelle() {
        return contributionExceptionnelle;
    }

    /**
     * Définit le montant de la contribution exceptionnelle sur les hauts revenus.
     *
     * @param contributionExceptionnelle La contribution exceptionnelle en euros
     */
    public void setContributionExceptionnelle(final double contributionExceptionnelle) {
        this.contributionExceptionnelle = contributionExceptionnelle;
    }

    /**
     * Retourne le montant final de l'impôt sur le revenu net.
     *
     * @return L'impôt net final en euros
     */
    public int getImpotNet() {
        return impotNet;
    }

    /**
     * Définit le montant final de l'impôt sur le revenu net.
     *
     * @param impotNet L'impôt net final en euros
     */
    public void setImpotNet(final int impotNet) {
        this.impotNet = impotNet;
    }

    @Override
    public String toString() {
        String sb = "Résultat du calcul d'impôt:\n" +
                "--------------------------------------------------\n" +
                "Revenu net declarant1 : " + foyerFiscal.getRevenuNetDeclarant1() + "\n" +
                "Revenu net declarant2 : " + foyerFiscal.getRevenuNetDeclarant2() + "\n" +
                "Situation familiale : " + foyerFiscal.getSituationFamiliale() + "\n" +
                "Abattement : " + abattement + "\n" +
                "Revenu fiscal de référence : " + revenuFiscalReference + "\n" +
                "Nombre d'enfants  : " + foyerFiscal.getNbEnfantsACharge() + "\n" +
                "Nombre d'enfants handicapés : " + foyerFiscal.getNbEnfantsSituationHandicap() + "\n" +
                "Parent isolé : " + foyerFiscal.isParentIsole() + "\n" +
                "Nombre de parts : " + nbPartsFiscales + "\n" +
                "Contribution exceptionnelle sur les hauts revenus : " + contributionExceptionnelle + "\n" +
                "Impôt brut des déclarants : " + impotBrutDeclarants + "\n" +
                "Impôt brut du foyer fiscal complet : " + impotBrutFoyer + "\n" +
                "Impôt brut après plafonnement avant decote : " + impotAvantDecote + "\n" +
                "Decote : " + decote + "\n" +
                "Impôt sur le revenu net final : " + impotNet + "\n";
        return sb;
    }
}