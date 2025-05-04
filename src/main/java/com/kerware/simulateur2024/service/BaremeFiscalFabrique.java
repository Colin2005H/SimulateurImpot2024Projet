package com.kerware.simulateur2024.service;

import com.kerware.simulateur2024.modele.BaremeFiscal;

/**
 * Fabrique de barèmes fiscaux permettant de le mettre à jour pour les années suivantes.
 */
public final class BaremeFiscalFabrique {

    // Constantes pour les paramètres du barème fiscal 2024
    private static final double TAUX_ABATTEMENT = 0.1;
    private static final int ABATTEMENT_MIN = 495;
    private static final int ABATTEMENT_MAX = 14171;

    private static final double SEUIL_DECOTE_SEUL = 1929;
    private static final double SEUIL_DECOTE_COUPLE = 3191;
    private static final double DECOTE_MAX_SEUL = 873;
    private static final double DECOTE_MAX_COUPLE = 1444;
    private static final double TAUX_DECOTE = 0.4525;

    private static final double PLAFONNEMENT_DEMI_PART = 1759;

    // Constantes pour les tranches d'imposition 2024
    private static final int LIMITE_TRANCHE_1 = 11294;
    private static final int LIMITE_TRANCHE_2 = 28797;
    private static final int LIMITE_TRANCHE_3 = 82341;
    private static final int LIMITE_TRANCHE_4 = 177106;

    private static final double TAUX_TRANCHE_1 = 0.0;
    private static final double TAUX_TRANCHE_2 = 0.11;
    private static final double TAUX_TRANCHE_3 = 0.30;
    private static final double TAUX_TRANCHE_4 = 0.41;
    private static final double TAUX_TRANCHE_5 = 0.45;

    // Constantes pour les tranches de contribution exceptionnelle
    private static final int LIMITE_CONTRIB_1 = 250000;
    private static final int LIMITE_CONTRIB_2 = 500000;
    private static final int LIMITE_CONTRIB_3 = 1000000;

    private static final double TAUX_CONTRIB_1 = 0.0;
    private static final double TAUX_CONTRIB_2 = 0.03;
    private static final double TAUX_CONTRIB_3 = 0.04;
    private static final double TAUX_CONTRIB_4 = 0.04;

    private static final int ANNEE_BAREME = 2024;
    private static final String NOM_BAREME = "Barème 2024";

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private BaremeFiscalFabrique() {
        // Classe utilitaire non instanciable
    }

    /**
     * Création du barème 2024 (revenus 2023).
     *
     * @return Le barème fiscal 2024 initialisé avec tous ses paramètres
     */
    public static BaremeFiscal creerBareme2024() {
        BaremeFiscal bareme = new BaremeFiscal(
                NOM_BAREME, ANNEE_BAREME,
                TAUX_ABATTEMENT, ABATTEMENT_MIN, ABATTEMENT_MAX,
                SEUIL_DECOTE_SEUL, SEUIL_DECOTE_COUPLE,
                DECOTE_MAX_SEUL, DECOTE_MAX_COUPLE, TAUX_DECOTE,
                PLAFONNEMENT_DEMI_PART
        );

        // Tranches d'imposition
        bareme.ajouterTrancheImposition(0, LIMITE_TRANCHE_1, TAUX_TRANCHE_1)
                .ajouterTrancheImposition(LIMITE_TRANCHE_1, LIMITE_TRANCHE_2, TAUX_TRANCHE_2)
                .ajouterTrancheImposition(LIMITE_TRANCHE_2, LIMITE_TRANCHE_3, TAUX_TRANCHE_3)
                .ajouterTrancheImposition(LIMITE_TRANCHE_3, LIMITE_TRANCHE_4, TAUX_TRANCHE_4)
                .ajouterTrancheImposition(LIMITE_TRANCHE_4, Integer.MAX_VALUE, TAUX_TRANCHE_5);

        // Tranches contribution exceptionnelle pour célibataire
        bareme.ajouterTrancheContributionCelibataire(0, LIMITE_CONTRIB_1, TAUX_CONTRIB_1)
                .ajouterTrancheContributionCelibataire(LIMITE_CONTRIB_1, LIMITE_CONTRIB_2, TAUX_CONTRIB_2)
                .ajouterTrancheContributionCelibataire(LIMITE_CONTRIB_2, LIMITE_CONTRIB_3, TAUX_CONTRIB_3)
                .ajouterTrancheContributionCelibataire(LIMITE_CONTRIB_3, Integer.MAX_VALUE, TAUX_CONTRIB_4);

        // Tranches contribution exceptionnelle pour couple
        bareme.ajouterTrancheContributionCouple(0, LIMITE_CONTRIB_2, TAUX_CONTRIB_1)
                .ajouterTrancheContributionCouple(LIMITE_CONTRIB_2, LIMITE_CONTRIB_3, TAUX_CONTRIB_2)
                .ajouterTrancheContributionCouple(LIMITE_CONTRIB_3, Integer.MAX_VALUE, TAUX_CONTRIB_3);

        return bareme;
    }
}