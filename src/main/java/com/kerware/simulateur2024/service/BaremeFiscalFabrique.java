package com.kerware.simulateur2024.service;

import com.kerware.simulateur2024.modele.BaremeFiscal;

/**
 * Fabrique de barèmes fiscaux permettant de le mettre à jour pour les années suivantes.
 */
public class BaremeFiscalFabrique {
    
    /**
     * 
     * Création du barème 2024 (revenus 2023).
     * 
     * @return Le barème fiscal 2024 initialisé avec tous ses paramètres
     */
    public static BaremeFiscal creerBareme2024(){
        
        double tauxAbattement = 0.1; 
        int abattementMin = 495;
        int abattementMax = 14171;
        
        double seuilDecoteSeul = 1929;
        double seuilDecoteCouple = 3191;
        double decoteMaxSeul = 873;
        double decoteMaxCouple = 1444;
        double tauxDecote = 0.4525;
        
        double plafonnementDemiPart = 1759;
        
        BaremeFiscal bareme = new BaremeFiscal(
            "Barème 2024", 2024,
            tauxAbattement, abattementMin, abattementMax,
            seuilDecoteSeul, seuilDecoteCouple,
            decoteMaxSeul, decoteMaxCouple, tauxDecote,
            plafonnementDemiPart
        );
        
        // Tranches d'imposition
        bareme.ajouterTrancheImposition(0, 11294, 0.0)
              .ajouterTrancheImposition(11294, 28797, 0.11)
              .ajouterTrancheImposition(28797, 82341, 0.30)
              .ajouterTrancheImposition(82341, 177106, 0.41)
              .ajouterTrancheImposition(177106, Integer.MAX_VALUE, 0.45);
        
        // Tranches contribution exceptionnelle pour célibataire
        bareme.ajouterTrancheContributionCelibataire(0, 250000, 0.0)
              .ajouterTrancheContributionCelibataire(250000, 500000, 0.03)
              .ajouterTrancheContributionCelibataire(500000, 1000000, 0.04)
              .ajouterTrancheContributionCelibataire(1000000, Integer.MAX_VALUE, 0.04);
        
        // Tranches contribution exceptionnelle pour couple
        bareme.ajouterTrancheContributionCouple(0, 500000, 0.0)
              .ajouterTrancheContributionCouple(500000, 1000000, 0.03)
              .ajouterTrancheContributionCouple(1000000, Integer.MAX_VALUE, 0.04);
        
        return bareme;
    }
}