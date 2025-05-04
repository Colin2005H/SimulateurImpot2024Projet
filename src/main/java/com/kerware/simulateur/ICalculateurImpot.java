package com.kerware.simulateur;

/**
 * Interface définissant les opérations pour un calculateur d'impôt sur le
 * revenu.
 */
public interface ICalculateurImpot {

    /**
     * Définit les revenus nets du premier déclarant.
     * @param rn Revenu net du premier déclarant
     */
    void setRevenusNetDeclarant1(int rn);

    /**
     * Définit les revenus nets du second déclarant.
     * @param rn Revenu net du second déclarant
     */
    void setRevenusNetDeclarant2(int rn);

    /**
     * Définit la situation familiale du foyer fiscal.
     * @param sf Situation familiale
     */
    void setSituationFamiliale(SituationFamiliale sf);

    /**
     * Définit le nombre d'enfants à charge.
     * @param nbe Nombre d'enfants à charge
     */
    void setNbEnfantsACharge(int nbe);

    /**
     * Définit le nombre d'enfants en situation de handicap.
     * @param nbesh Nombre d'enfants en situation de handicap
     */
    void setNbEnfantsSituationHandicap(int nbesh);

    /**
     * Définit si le déclarant est un parent isolé.
     * @param pi Vrai si le déclarant est un parent isolé, faux sinon
     */
    void setParentIsole(boolean pi);

    /**
     * Calcule l'impôt sur le revenu net.
     */
    void calculImpotSurRevenuNet();

    /**
     * Retourne le revenu net du premier déclarant.
     * @return Revenu net du premier déclarant
     */
    int getRevenuNetDeclatant1();

    /**
     * Retourne le revenu net du second déclarant.
     * @return Revenu net du second déclarant
     */
    int getRevenuNetDeclatant2();

    /**
     * Retourne la contribution exceptionnelle.
     * @return La contribution exceptionnelle
     */
    double getContribExceptionnelle();

    /**
     * Retourne le revenu fiscal de référence.
     * @return Le revenu fiscal de référence
     */
    int getRevenuFiscalReference();

    /**
     * Retourne l'abattement.
     * @return L'abattement
     */
    int getAbattement();

    /**
     * Retourne le nombre de parts fiscales du foyer.
     * @return Le nombre de parts fiscales
     */
    double getNbPartsFoyerFiscal();

    /**
     * Retourne le montant de l'impôt avant décote.
     * @return L'impôt avant décote
     */
    int getImpotAvantDecote();

    /**
     * Retourne la décote.
     * @return La décote
     */
    int getDecote();

    /**
     * Retourne l'impôt sur le revenu net.
     * @return L'impôt sur le revenu net
     */
    int getImpotSurRevenuNet();

}
