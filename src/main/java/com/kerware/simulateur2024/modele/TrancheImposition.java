package com.kerware.simulateur2024.modele;

/**
 * Classe représentant une tranche du barème d'imposition.
 * Définie par une limite inférieure, une limite supérieure et un taux d'imposition.
 *
 * EXIGENCE : EXG_IMPOT_04 (tranches d'imposition)
 */
public class TrancheImposition {

    private final int limiteInferieure;
    private final int limiteSuperieure;
    private final double tauxImposition;

    /**
     * Constructeur d'une tranche d'imposition.
     *
     * @param limiteInferieure Limite inférieure de la tranche en euros
     * @param limiteSuperieure Limite supérieure de la tranche en euros
     * @param tauxImposition   Taux d'imposition de la tranche (entre 0 et 1)
     */
    public TrancheImposition(int limiteInferieure, int limiteSuperieure, double tauxImposition) {
        this.limiteInferieure = limiteInferieure;
        this.limiteSuperieure = limiteSuperieure;
        this.tauxImposition = tauxImposition;
    }

    /**
     * Vérifie si un montant est dans cette tranche d'imposition.
     *
     * @param montant Le montant à vérifier
     * @return true si le montant est dans cette tranche, false sinon
     */
    public boolean contientMontant(double montant) {
        return montant >= limiteInferieure && montant < limiteSuperieure;
    }

    /**
     * Calcule l'impôt pour un montant donné dans cette tranche.
     *
     * @param montant Le montant imposable
     * @return L'impôt calculé pour la partie du montant située dans cette tranche
     */
    public double calculerImpot(double montant) {
        if (montant <= limiteInferieure) {
            return 0;
        }

        double montantImposableDansLaTranche = Math.min(montant, limiteSuperieure) - limiteInferieure;
        return montantImposableDansLaTranche * tauxImposition;
    }

    /**
     * Calcule l'impôt maximum dans cette tranche (si tout le montant est imposable).
     *
     * @return L'impôt maximal pour cette tranche
     */
    public double calculerImpotMaximum() {
        return (limiteSuperieure - limiteInferieure) * tauxImposition;
    }

    /**
     * Retourne la limite inférieure de la tranche d'imposition.
     *
     * @return La limite inférieure en euros
     */
    public int getLimiteInferieure() {
        return limiteInferieure;
    }

    /**
     * Retourne la limite supérieure de la tranche d'imposition.
     *
     * @return La limite supérieure en euros
     */
    public int getLimiteSuperieure() {
        return limiteSuperieure;
    }

    /**
     * Retourne le taux d'imposition de la tranche.
     *
     * @return Le taux d'imposition (entre 0 et 1)
     */
    public double getTauxImposition() {
        return tauxImposition;
    }
}