package com.kerware.simulateur2024.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Barème fiscal avec l'ensemble des paramètres pour le calcul de l'impot.
 * Classe modulaire qui permet que le simulateur fonctionne pour les années suivantes.
 * 
 * EXIGENCE : EXG_IMPOT_04 (tranches et taux d'imposition)
 */
public class BaremeFiscal {

    // Barème
    private final String nom;
    private final int anneeApplication;

    // Tranches d'imposition
    private final List<TrancheImposition> tranches;

    // Paramètres d'abattement
    private final double tauxAbattement;
    private final int abattementMinimum;
    private final int abattementMaximum;

    // Paramètres de décote
    private final double seuilDecoteDeclarantSeul;
    private final double seuilDecoteCouple;
    private final double decoteMaxDeclarantSeul;
    private final double decoteMaxCouple;
    private final double tauxDecote;

    // Paramètre de plafonnement de la réduction d'impôt par demi-part
    private final double plafonnementDemiPart;

    // Paramètres de la contribution exceptionnelle sur les hauts revenus
    private final List<TrancheContributionExceptionnelle> tranchesContributionCelibataire;
    private final List<TrancheContributionExceptionnelle> tranchesContributionCouple;

    /**
     * Constructeur complet du barème fiscal avec tous les paramètres.
     *
     * @param nom                      Nom du barème
     * @param anneeApplication         Année d'application
     * @param tauxAbattement           Taux d'abattement
     * @param abattementMinimum        Montant minimum d'abattement
     * @param abattementMaximum        Montant maximum d'abattement
     * @param seuilDecoteDeclarantSeul Seuil de décote pour un déclarant seul
     * @param seuilDecoteCouple        Seuil de décote pour un couple
     * @param decoteMaxDeclarantSeul   Décote maximale pour un déclarant seul
     * @param decoteMaxCouple          Décote maximale pour un couple
     * @param tauxDecote               Taux de décote
     * @param plafonnementDemiPart     Plafonnement par demi-part
     */
    public BaremeFiscal(String nom, int anneeApplication, double tauxAbattement, int abattementMinimum,
                        int abattementMaximum, double seuilDecoteDeclarantSeul, double seuilDecoteCouple,
                        double decoteMaxDeclarantSeul, double decoteMaxCouple, double tauxDecote,
                        double plafonnementDemiPart) {
        this.nom = nom;
        this.anneeApplication = anneeApplication;
        this.tauxAbattement = tauxAbattement;
        this.abattementMinimum = abattementMinimum;
        this.abattementMaximum = abattementMaximum;
        this.seuilDecoteDeclarantSeul = seuilDecoteDeclarantSeul;
        this.seuilDecoteCouple = seuilDecoteCouple;
        this.decoteMaxDeclarantSeul = decoteMaxDeclarantSeul;
        this.decoteMaxCouple = decoteMaxCouple;
        this.tauxDecote = tauxDecote;
        this.plafonnementDemiPart = plafonnementDemiPart;

        this.tranches = new ArrayList<>();
        this.tranchesContributionCelibataire = new ArrayList<>();
        this.tranchesContributionCouple = new ArrayList<>();
    }

    /**
     * Ajoute une tranche d'imposition au barème.
     *
     * @param limiteInf Limite inférieure de la tranche
     * @param limiteSup Limite supérieure de la tranche
     * @param taux      Taux d'imposition de la tranche
     * @return Ce barème
     */
    public BaremeFiscal ajouterTrancheImposition(int limiteInf, int limiteSup, double taux) {
        tranches.add(new TrancheImposition(limiteInf, limiteSup, taux));
        return this;
    }

    /**
     * Ajoute une tranche de contribution exceptionnelle pour les célibataires.
     *
     * @param limiteInf Limite inférieure de la tranche
     * @param limiteSup Limite supérieure de la tranche
     * @param taux      Taux de contribution
     * @return Ce barème
     */
    public BaremeFiscal ajouterTrancheContributionCelibataire(int limiteInf, int limiteSup, double taux) {
        tranchesContributionCelibataire.add(new TrancheContributionExceptionnelle(limiteInf, limiteSup, taux));
        return this;
    }

    /**
     * Ajoute une tranche de contribution exceptionnelle pour les couples.
     *
     * @param limiteInf Limite inférieure de la tranche
     * @param limiteSup Limite supérieure de la tranche
     * @param taux      Taux de contribution
     * @return Ce barème
     */
    public BaremeFiscal ajouterTrancheContributionCouple(int limiteInf, int limiteSup, double taux) {
        tranchesContributionCouple.add(new TrancheContributionExceptionnelle(limiteInf, limiteSup, taux));
        return this;
    }

    /**
     * Calcule l'impôt pour un revenu imposable donné.
     *
     * @param revenuImposable Le revenu imposable
     * @return L'impôt calculé selon le barème progressif
     */
    public double calculerImpot(double revenuImposable) {
        double impot = 0;

        for (TrancheImposition tranche : tranches) {
            impot += tranche.calculerImpot(revenuImposable);
        }

        return impot;
    }

    /**
     * Calcule la contribution exceptionnelle pour un revenu fiscal de référence donné,
     * en fonction de la situation familiale.
     *
     * @param revenuFiscalReference Le revenu fiscal de référence
     * @param estCouple             Indique si le foyer est un couple marié ou pacsé
     * @return La contribution exceptionnelle
     */
    public double calculerContributionExceptionnelle(double revenuFiscalReference, boolean estCouple) {
        double contribution = 0;
        List<TrancheContributionExceptionnelle> tranchesApplicables = estCouple ?
                tranchesContributionCouple : tranchesContributionCelibataire;

        for (TrancheContributionExceptionnelle tranche : tranchesApplicables) {
            contribution += tranche.calculerContribution(revenuFiscalReference);
        }

        return contribution;
    }

    /**
     * Calcule l'abattement pour un revenu net.
     *
     * @param revenuNet Le revenu net
     * @return L'abattement calculé
     */
    public int calculerAbattement(double revenuNet) {
        double abattement = revenuNet * tauxAbattement;

        if (abattement < abattementMinimum) {
            return abattementMinimum;
        } else if (abattement > abattementMaximum) {
            return abattementMaximum;
        } else {
            return (int) Math.round(abattement);
        }
    }

    /**
     * Calcule la décote pour un impôt donné.
     *
     * @param impotAvantDecote L'impôt avant décote
     * @param estCouple        Indique si le foyer est un couple marié ou pacsé
     * @return La décote calculée
     */
    public double calculerDecote(double impotAvantDecote, boolean estCouple) {
        double seuil = estCouple ? seuilDecoteCouple : seuilDecoteDeclarantSeul;
        double decoteMax = estCouple ? decoteMaxCouple : decoteMaxDeclarantSeul;

        if (impotAvantDecote <= 0 || impotAvantDecote >= seuil) {
            return 0;
        }

        double decote = decoteMax - (impotAvantDecote * tauxDecote);
        decote = Math.round(decote);

        // La décote ne peut pas être supérieure à l'impôt
        return Math.min(decote, impotAvantDecote);
    }

    public String getNom() {
        return nom;
    }

    public int getAnneeApplication() {
        return anneeApplication;
    }

    public List<TrancheImposition> getTranches() {
        return new ArrayList<>(tranches);
    }

    public double getTauxAbattement() {
        return tauxAbattement;
    }

    public int getAbattementMinimum() {
        return abattementMinimum;
    }

    public int getAbattementMaximum() {
        return abattementMaximum;
    }

    public double getSeuilDecoteDeclarantSeul() {
        return seuilDecoteDeclarantSeul;
    }

    public double getSeuilDecoteCouple() {
        return seuilDecoteCouple;
    }

    public double getDecoteMaxDeclarantSeul() {
        return decoteMaxDeclarantSeul;
    }

    public double getDecoteMaxCouple() {
        return decoteMaxCouple;
    }

    public double getTauxDecote() {
        return tauxDecote;
    }

    public double getPlafonnementDemiPart() {
        return plafonnementDemiPart;
    }

    public List<TrancheContributionExceptionnelle> getTranchesCelibataire() {
        return new ArrayList<>(tranchesContributionCelibataire);
    }

    public List<TrancheContributionExceptionnelle> getTranchesCEHRCouple() {
        return new ArrayList<>(tranchesContributionCouple);
    }
}