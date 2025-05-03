package com.kerware.simulateur2024.service;

import com.kerware.simulateur2024.modele.FoyerFiscal;
import com.kerware.simulateur2024.modele.ResultatCalculImpot;

/**
 * Interface définissant les opérations du calculateur d'impôt sur le revenu.
 */
public interface ICalculateurImpot2024{
    
    /**
     * Calcule l'impôt sur le revenu pour un foyer fiscal donné.
     * 
     * @param foyerFiscal Le foyer fiscal pour lequel calculer l'impôt
     * @return Le résultat détaillé du calcul de l'impôt
     * @throws IllegalArgumentException si le foyer fiscal n'est pas valide
     */
    ResultatCalculImpot calculerImpot(FoyerFiscal foyerFiscal);
    
    /**
     * Résultat du calcul effectué.
     * 
     * @return Le résultat détaillé du calcul d'impôt
     */
    ResultatCalculImpot getResultatCalcul();
}