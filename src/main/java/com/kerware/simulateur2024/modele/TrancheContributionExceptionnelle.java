package com.kerware.simulateur2024.modele;

/**
 * Classe représentant une tranche de la contribution exceptionnelle sur les hauts revenus.
 *
 * EXIGENCE : EXG_IMPOT_07 (contribution exceptionnelle sur les hauts revenus)
 */
public final class TrancheContributionExceptionnelle {

    /** Limite inférieure de la tranche. */
    private final int limiteInferieure;
    /** Limite supérieure de la tranche. */
    private final int limiteSuperieure;
    /** Taux de contribution de la tranche. */
    private final double tauxContribution;

    /**
     * Constructeur d'une tranche de contribution exceptionnelle.
     *
     * @param limiteInferieure Limite inférieure de la tranche en euros
     * @param limiteSuperieure Limite supérieure de la tranche en euros
     * @param tauxContribution Taux de contribution
     */
    public TrancheContributionExceptionnelle(
            final int limiteInferieure,
            final int limiteSuperieure,
            final double tauxContribution) {
        this.limiteInferieure = limiteInferieure;
        this.limiteSuperieure = limiteSuperieure;
        this.tauxContribution = tauxContribution;
    }

    /**
     * Vérifie si un montant est dans cette tranche de contribution.
     *
     * @param montant Le montant à vérifier
     * @return true si le montant est dans cette tranche, false sinon
     */
    public boolean contientMontant(final double montant) {
        return montant >= limiteInferieure && montant < limiteSuperieure;
    }

    /**
     * Calcule la contribution pour un montant donné dans cette tranche.
     *
     * @param montant Le montant imposable
     * @return La contribution calculée pour la partie du montant située dans cette tranche
     */
    public double calculerContribution(final double montant) {
        if (montant <= limiteInferieure) {
            return 0;
        }
        double montantImposableDansLaTranche =
            Math.min(montant, limiteSuperieure) - limiteInferieure;
        return montantImposableDansLaTranche * tauxContribution;
    }

    /**
     * Calcule la contribution maximum dans cette tranche.
     *
     * @return La contribution maximale pour cette tranche
     */
    public double calculerContributionMaximum() {
        return (limiteSuperieure - limiteInferieure) * tauxContribution;
    }

    /**
     * Retourne la limite inférieure de la tranche.
     * @return la limite inférieure en euros
     */
    public int getLimiteInferieure() {
        return limiteInferieure;
    }

    /**
     * Retourne la limite supérieure de la tranche.
     * @return la limite supérieure en euros
     */
    public int getLimiteSuperieure() {
        return limiteSuperieure;
    }

    /**
     * Retourne le taux de contribution de la tranche.
     * @return le taux de contribution
     */
    public double getTauxContribution() {
        return tauxContribution;
    }
}