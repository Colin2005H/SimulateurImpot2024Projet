package com.kerware.simulateur2024.modele;

/**
 * Classe représentant une tranche de la contribution exceptionnelle sur les hauts revenus.
 * 
 * EXIGENCE : EXG_IMPOT_07 (contribution exceptionnelle sur les hauts revenus)
 */
public class TrancheContributionExceptionnelle {
    
    private final int limiteInferieure;
    private final int limiteSuperieure;
    private final double tauxContribution;
    
    /**
     * Constructeur d'une tranche de contribution exceptionnelle.
     * 
     * @param limiteInferieure Limite inférieure de la tranche en euros
     * @param limiteSuperieure Limite supérieure de la tranche en euros
     * @param tauxContribution Taux de contribution
     */
    public TrancheContributionExceptionnelle(int limiteInferieure, int limiteSuperieure, double tauxContribution){
        this.limiteInferieure = limiteInferieure;
        this.limiteSuperieure =limiteSuperieure;
        this.tauxContribution = tauxContribution;
    }
    
    /**
     * Vérifie si un montant est dans cette tranche de contribution.
     * 
     * @param montant Le montant à vérifier
     * @return true si le montant est dans cette tranche, false sinon
     */
    public boolean contientMontant(double montant) {
        return montant >= limiteInferieure && montant < limiteSuperieure;
    }
    
    /**
     * Calcule la contribution pour un montant donné dans cette tranche.
     * 
     * @param montant Le montant imposable
     * @return La contribution calculée pour la partie du montant située dans cette tranche
     */
    public double calculerContribution(double montant) {
        if (montant <= limiteInferieure) {
            return 0;
        }
        
        double montantImposableDansLaTranche = Math.min(montant, limiteSuperieure) - limiteInferieure;
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
    
    public int getLimiteInferieure() {
        return limiteInferieure;
    }
    
    public int getLimiteSuperieure() {
        return limiteSuperieure;
    }
    
    public double getTauxContribution() {
        return tauxContribution;
    }
}