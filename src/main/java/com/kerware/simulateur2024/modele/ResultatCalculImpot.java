package com.kerware.simulateur2024.modele;

/**
 * Stocke les différentes valeurs calculées pour un foyer fiscal.
 * Cette classe n'est pas conçue pour être étendue.
 */
public final class ResultatCalculImpot {
    /** Foyer fiscal concerné par ce résultat de calcul. */
    private final FoyerFiscal foyerFiscal;
    /** Abattement appliqué sur le revenu brut. */
    private int abattement;
    /** Revenu fiscal de référence. */
    private int revenuFiscalReference;
    /** Nombre de parts fiscales du foyer. */
    private double nbPartsFiscales;
    /** Impôt brut cumulé des déclarants. */
    private double impotBrutDeclarants;
    /** Impôt brut du foyer fiscal après parts fiscales. */
    private double impotBrutFoyer;
    /** Impôt avant application de la décote. */
    private double impotAvantDecote;
    /** Montant de la décote appliquée à l'impôt. */
    private double decote;
    /** Contribution exceptionnelle sur les hauts revenus. */
    private double contributionExceptionnelle;
    /** Montant final de l'impôt sur le revenu net. */
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
        StringBuilder sb = new StringBuilder();
        sb.append("Résultat du calcul d'impôt:\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Revenu net declarant1 : ")
            .append(foyerFiscal.getRevenuNetDeclarant1())
            .append("\n");
        sb.append("Revenu net declarant2 : ")
            .append(foyerFiscal.getRevenuNetDeclarant2())
            .append("\n");
        sb.append("Situation familiale : ")
            .append(foyerFiscal.getSituationFamiliale())
            .append("\n");
        sb.append("Abattement : ")
            .append(abattement)
            .append("\n");
        sb.append("Revenu fiscal de référence : ")
            .append(revenuFiscalReference)
            .append("\n");
        sb.append("Nombre d'enfants  : ")
            .append(foyerFiscal.getNbEnfantsACharge())
            .append("\n");
        sb.append("Nombre d'enfants handicapés : ")
            .append(foyerFiscal.getNbEnfantsSituationHandicap())
            .append("\n");
        sb.append("Parent isolé : ")
            .append(foyerFiscal.isParentIsole())
            .append("\n");
        sb.append("Nombre de parts : ")
            .append(nbPartsFiscales)
            .append("\n");
        sb.append("Contribution exceptionnelle sur les hauts revenus : ")
            .append(contributionExceptionnelle)
            .append("\n");
        sb.append("Impôt brut des déclarants : ")
            .append(impotBrutDeclarants)
            .append("\n");
        sb.append("Impôt brut du foyer fiscal complet : ")
            .append(impotBrutFoyer)
            .append("\n");
        sb.append("Impôt brut après plafonnement avant decote : ")
            .append(impotAvantDecote)
            .append("\n");
        sb.append("Decote : ")
            .append(decote)
            .append("\n");
        sb.append("Impôt sur le revenu net final : ")
            .append(impotNet)
            .append("\n");
        return sb.toString();
    }
}