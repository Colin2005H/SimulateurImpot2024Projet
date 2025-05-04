package com.kerware.simulateur2024.modele;

/**
 * Différentes situations familiales possibles pour un foyer fiscal.
 * Chaque situation détermine le nombre de parts.
 */
public enum SituationFamiliale {
    CELIBATAIRE(1.0, "Célibataire"),
    MARIE(2.0, "Marié(e)"),
    PACSE(2.0, "Pacsé(e)"),
    DIVORCE(1.0, "Divorcé(e)"),
    VEUF(1.0, "Veuf/Veuve");

    private final double nbPartsFiscales;
    private final String libelle;

    /**
     * Constructeur de l'énumération.
     *
     * @param nbPartsFiscales Le nombre de parts fiscales pour cette situation
     * @param libelle         Le libellé de la situation familiale
     */
    SituationFamiliale(double nbPartsFiscales, String libelle) {
        this.nbPartsFiscales = nbPartsFiscales;
        this.libelle = libelle;
    }

    /**
     * Obtient le nombre de parts fiscales de base pour cette situation familiale.
     *
     * @return Le nombre de parts fiscales
     */
    public double getNbPartsFiscales() {
        return nbPartsFiscales;
    }

    /**
     * Obtient le libellé de la situation familiale.
     *
     * @return Le libellé
     */
    public String getLibelle() {
        return libelle;
    }
}