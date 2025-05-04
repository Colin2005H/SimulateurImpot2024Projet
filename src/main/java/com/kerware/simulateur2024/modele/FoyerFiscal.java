package com.kerware.simulateur2024.modele;

/**
 * Classe représentant un foyer fiscal avec sa composition et ses revenus.
 *
 * EXIGENCE : EXG_IMPOT_03 (traçabilité)
 */
public class FoyerFiscal {

    private SituationFamiliale situationFamiliale;
    private int revenuNetDeclarant1;
    private int revenuNetDeclarant2;
    private int nbEnfantsACharge;
    private int nbEnfantsSituationHandicap;
    private boolean parentIsole;

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
    public FoyerFiscal(SituationFamiliale situationFamiliale, int revenuNetDeclarant1,
                       int revenuNetDeclarant2, int nbEnfantsACharge, int nbEnfantsSituationHandicap,
                       boolean parentIsole) {
        this.situationFamiliale = situationFamiliale;
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
        this.nbEnfantsACharge = nbEnfantsACharge;
        this.nbEnfantsSituationHandicap = nbEnfantsSituationHandicap;
        this.parentIsole = parentIsole;
    }


    public SituationFamiliale getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(SituationFamiliale situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public int getRevenuNetDeclarant1() {
        return revenuNetDeclarant1;
    }

    public void setRevenuNetDeclarant1(int revenuNetDeclarant1) {
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
    }

    public int getRevenuNetDeclarant2() {
        return revenuNetDeclarant2;
    }

    public void setRevenuNetDeclarant2(int revenuNetDeclarant2) {
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
    }

    public int getNbEnfantsACharge() {
        return nbEnfantsACharge;
    }

    public void setNbEnfantsACharge(int nbEnfantsACharge) {
        this.nbEnfantsACharge = nbEnfantsACharge;
    }

    public int getNbEnfantsSituationHandicap() {
        return nbEnfantsSituationHandicap;
    }

    public void setNbEnfantsSituationHandicap(int nbEnfantsSituationHandicap) {
        this.nbEnfantsSituationHandicap = nbEnfantsSituationHandicap;
    }

    public boolean isParentIsole() {
        return parentIsole;
    }

    public void setParentIsole(boolean parentIsole) {
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
        return nbEnfantsACharge >= 0 && nbEnfantsACharge <= 7
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