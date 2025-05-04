package com.kerware.simulateur2024.modele;

/**
 * Classe représentant un foyer fiscal avec sa composition et ses revenus.
 *
 * EXIGENCE : EXG_IMPOT_03 (traçabilité)
 */
public final class FoyerFiscal {

    /** Situation familiale du foyer. */
    private SituationFamiliale situationFamiliale;
    /** Revenu net du premier déclarant. */
    private int revenuNetDeclarant1;
    /** Revenu net du second déclarant. */
    private int revenuNetDeclarant2;
    /** Nombre d'enfants à charge. */
    private int nbEnfantsACharge;
    /** Nombre d'enfants en situation de handicap. */
    private int nbEnfantsSituationHandicap;
    /** Indique si le foyer est un parent isolé. */
    private boolean parentIsole;
    /** Nombre maximum d'enfants à charge autorisé. */
    private static final int NOMBRE_MAX_ENFANTS = 7;

    /**
     * Foyer fiscal avec des valeurs par défaut.
     */
    public FoyerFiscal() {
        this.situationFamiliale = SituationFamiliale.CELIBATAIRE;
        this.revenuNetDeclarant1 = 0;
        this.revenuNetDeclarant2 = 0;
        this.nbEnfantsACharge = 0;
        this.nbEnfantsSituationHandicap = 0;
        this.parentIsole = false;
    }

    /**
     * Foyer fiscal avec tous les paramètres.
     *
     * @param situationFamiliale         La situation familiale des déclarants
     * @param revenuNetDeclarant1        Le revenu net du premier déclarant
     * @param revenuNetDeclarant2        Le revenu net du second déclarant (0 si absent)
     * @param nbEnfantsACharge           Le nombre d'enfants à charge
     * @param nbEnfantsSituationHandicap Le nombre d'enfants en situation de handicap
     * @param parentIsole                Si le foyer est un parent isolé
     */
    public FoyerFiscal(final SituationFamiliale situationFamiliale,
                       final int revenuNetDeclarant1,
                       final int revenuNetDeclarant2,
                       final int nbEnfantsACharge,
                       final int nbEnfantsSituationHandicap,
                       final boolean parentIsole) {
        this.situationFamiliale = situationFamiliale;
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
        this.nbEnfantsACharge = nbEnfantsACharge;
        this.nbEnfantsSituationHandicap = nbEnfantsSituationHandicap;
        this.parentIsole = parentIsole;
    }

    /**
     * Retourne la situation familiale du foyer.
     * @return la situation familiale
     */
    public SituationFamiliale getSituationFamiliale() {
        return situationFamiliale;
    }

    /**
     * Définit la situation familiale du foyer.
     * @param situationFamiliale la situation familiale à définir
     */
    public void setSituationFamiliale(final SituationFamiliale situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    /**
     * Retourne le revenu net du premier déclarant.
     * @return le revenu net du premier déclarant
     */
    public int getRevenuNetDeclarant1() {
        return revenuNetDeclarant1;
    }

    /**
     * Définit le revenu net du premier déclarant.
     * @param revenuNetDeclarant1 le revenu à définir
     */
    public void setRevenuNetDeclarant1(final int revenuNetDeclarant1) {
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
    }

    /**
     * Retourne le revenu net du second déclarant.
     * @return le revenu net du second déclarant
     */
    public int getRevenuNetDeclarant2() {
        return revenuNetDeclarant2;
    }

    /**
     * Définit le revenu net du second déclarant.
     * @param revenuNetDeclarant2 le revenu à définir
     */
    public void setRevenuNetDeclarant2(final int revenuNetDeclarant2) {
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
    }

    /**
     * Retourne le nombre d'enfants à charge.
     * @return le nombre d'enfants à charge
     */
    public int getNbEnfantsACharge() {
        return nbEnfantsACharge;
    }

    /**
     * Définit le nombre d'enfants à charge.
     * @param nbEnfantsACharge le nombre à définir
     */
    public void setNbEnfantsACharge(final int nbEnfantsACharge) {
        this.nbEnfantsACharge = nbEnfantsACharge;
    }

    /**
     * Retourne le nombre d'enfants en situation de handicap.
     * @return le nombre d'enfants en situation de handicap
     */
    public int getNbEnfantsSituationHandicap() {
        return nbEnfantsSituationHandicap;
    }

    /**
     * Définit le nombre d'enfants en situation de handicap.
     * @param nbEnfantsSituationHandicap le nombre à définir
     */
    public void setNbEnfantsSituationHandicap(final int nbEnfantsSituationHandicap) {
        this.nbEnfantsSituationHandicap = nbEnfantsSituationHandicap;
    }

    /**
     * Indique si le foyer est un parent isolé.
     * @return true si parent isolé, false sinon
     */
    public boolean isParentIsole() {
        return parentIsole;
    }

    /**
     * Définit si le foyer est un parent isolé.
     * @param parentIsole true si parent isolé, false sinon
     */
    public void setParentIsole(final boolean parentIsole) {
        this.parentIsole = parentIsole;
    }

    /**
     * Vérifie si le foyer fiscal est valide.
     *
     * @return true si le foyer est valide, false sinon
     */
    public boolean estValide() {
        // Un parent isolé ne peut pas être marié ou pacsé
        if (parentIsole && (situationFamiliale == SituationFamiliale.MARIE
                || situationFamiliale == SituationFamiliale.PACSE)) {
            return false;
        }
        // Vérification des revenus du déclarant 2 pour situations "seul"
        boolean seul = situationFamiliale == SituationFamiliale.CELIBATAIRE
                || situationFamiliale == SituationFamiliale.DIVORCE
                || situationFamiliale == SituationFamiliale.VEUF;
        if (seul && revenuNetDeclarant2 > 0) {
            return false;
        }
        // Vérification du nombre d'enfants
        return nbEnfantsACharge >= 0 && nbEnfantsACharge <= NOMBRE_MAX_ENFANTS
                && nbEnfantsSituationHandicap >= 0
                && nbEnfantsSituationHandicap <= nbEnfantsACharge;
    }

    /**
     * Calcule le revenu net global du foyer.
     *
     * @return La somme des revenus nets des déclarants
     */
    public int getRevenuNetGlobal() {
        return revenuNetDeclarant1 + revenuNetDeclarant2;
    }
}