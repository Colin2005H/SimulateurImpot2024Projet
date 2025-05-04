package com.kerware.simulateur2024.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Barème fiscal avec l'ensemble des paramètres pour le calcul de l'impot.
 * Classe modulaire qui permet que le simulateur fonctionne pour les années suivantes.
 *
 * EXIGENCE : EXG_IMPOT_04 (tranches et taux d'imposition)
 */
public final class BaremeFiscal {

    /** Nom du barème fiscal. */
    private final String nom;
    /** Année d'application du barème. */
    private final int anneeApplication;
    /** Liste des tranches d'imposition. */
    private final List<TrancheImposition> tranches;
    /** Taux d'abattement appliqué. */
    private final double tauxAbattement;
    /** Abattement minimum en euros. */
    private final int abattementMinimum;
    /** Abattement maximum en euros. */
    private final int abattementMaximum;
    /** Seuil de décote pour un déclarant seul. */
    private final double seuilDecoteDeclarantSeul;
    /** Seuil de décote pour un couple. */
    private final double seuilDecoteCouple;
    /** Décote maximale pour un déclarant seul. */
    private final double decoteMaxDeclarantSeul;
    /** Décote maximale pour un couple. */
    private final double decoteMaxCouple;
    /** Taux de décote. */
    private final double tauxDecote;
    /** Plafonnement de la réduction d'impôt par demi-part. */
    private final double plafonnementDemiPart;
    /** Tranches de contribution exceptionnelle pour célibataire. */
    private final List<TrancheContributionExceptionnelle> tranchesContributionCelibataire;
    /** Tranches de contribution exceptionnelle pour couple. */
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
    public BaremeFiscal(
            final String nom,
            final int anneeApplication,
            final double tauxAbattement,
            final int abattementMinimum,
            final int abattementMaximum,
            final double seuilDecoteDeclarantSeul,
            final double seuilDecoteCouple,
            final double decoteMaxDeclarantSeul,
            final double decoteMaxCouple,
            final double tauxDecote,
            final double plafonnementDemiPart) {
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
    public BaremeFiscal ajouterTrancheImposition(
            final int limiteInf,
            final int limiteSup,
            final double taux) {
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
    public BaremeFiscal ajouterTrancheContributionCelibataire(
            final int limiteInf,
            final int limiteSup,
            final double taux) {
        tranchesContributionCelibataire.add(
            new TrancheContributionExceptionnelle(limiteInf, limiteSup, taux));
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
    public BaremeFiscal ajouterTrancheContributionCouple(
            final int limiteInf,
            final int limiteSup,
            final double taux) {
        tranchesContributionCouple.add(
            new TrancheContributionExceptionnelle(limiteInf, limiteSup, taux));
        return this;
    }

    /**
     * Calcule l'impôt pour un revenu imposable donné.
     *
     * @param revenuImposable Le revenu imposable
     * @return L'impôt calculé selon le barème progressif
     */
    public double calculerImpot(final double revenuImposable) {
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
    public double calculerContributionExceptionnelle(
            final double revenuFiscalReference,
            final boolean estCouple) {
        double contribution = 0;
        List<TrancheContributionExceptionnelle> tranchesApplicables =
            estCouple
                ? tranchesContributionCouple
                : tranchesContributionCelibataire;
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
    public int calculerAbattement(final double revenuNet) {
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
    public double calculerDecote(final double impotAvantDecote, final boolean estCouple) {
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

    /**
     * Retourne le nom du barème fiscal.
     * @return le nom du barème
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne l'année d'application du barème.
     * @return l'année d'application
     */
    public int getAnneeApplication() {
        return anneeApplication;
    }

    /**
     * Retourne la liste des tranches d'imposition.
     * @return la liste des tranches
     */
    public List<TrancheImposition> getTranches() {
        return new ArrayList<>(tranches);
    }

    /**
     * Retourne le taux d'abattement appliqué.
     * @return le taux d'abattement
     */
    public double getTauxAbattement() {
        return tauxAbattement;
    }

    /**
     * Retourne l'abattement minimum en euros.
     * @return l'abattement minimum
     */
    public int getAbattementMinimum() {
        return abattementMinimum;
    }

    /**
     * Retourne l'abattement maximum en euros.
     * @return l'abattement maximum
     */
    public int getAbattementMaximum() {
        return abattementMaximum;
    }

    /**
     * Retourne le seuil de décote pour un déclarant seul.
     * @return le seuil de décote pour un déclarant seul
     */
    public double getSeuilDecoteDeclarantSeul() {
        return seuilDecoteDeclarantSeul;
    }

    /**
     * Retourne le seuil de décote pour un couple.
     * @return le seuil de décote pour un couple
     */
    public double getSeuilDecoteCouple() {
        return seuilDecoteCouple;
    }

    /**
     * Retourne la décote maximale pour un déclarant seul.
     * @return la décote maximale pour un déclarant seul
     */
    public double getDecoteMaxDeclarantSeul() {
        return decoteMaxDeclarantSeul;
    }

    /**
     * Retourne la décote maximale pour un couple.
     * @return la décote maximale pour un couple
     */
    public double getDecoteMaxCouple() {
        return decoteMaxCouple;
    }

    /**
     * Retourne le taux de décote.
     * @return le taux de décote
     */
    public double getTauxDecote() {
        return tauxDecote;
    }

    /**
     * Retourne le plafonnement de la réduction d'impôt par demi-part.
     * @return le plafonnement par demi-part
     */
    public double getPlafonnementDemiPart() {
        return plafonnementDemiPart;
    }

    /**
     * Retourne la liste des tranches de contribution exceptionnelle pour célibataire.
     * @return la liste des tranches pour célibataire
     */
    public List<TrancheContributionExceptionnelle> getTranchesCelibataire() {
        return new ArrayList<>(tranchesContributionCelibataire);
    }

    /**
     * Retourne la liste des tranches de contribution exceptionnelle pour couple.
     * @return la liste des tranches pour couple
     */
    public List<TrancheContributionExceptionnelle> getTranchesCEHRCouple() {
        return new ArrayList<>(tranchesContributionCouple);
    }
}