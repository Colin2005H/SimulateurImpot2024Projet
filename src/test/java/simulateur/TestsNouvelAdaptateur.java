package simulateur;

import com.kerware.simulateur.ICalculateurImpot;
import com.kerware.simulateur2024.adaptateur.NouvelAdaptateurSimulateur;
import org.junit.jupiter.api.DisplayName;

/**
 * Classe concrète de test pour le nouvel adaptateur.
 */
@DisplayName("Tests sur le nouvel adaptateur (package com.kerware.simulateur2024)")
public class TestsNouvelAdaptateur extends TestsSimulateur{

    @Override
    protected ICalculateurImpot getSimulateur() {
        return new NouvelAdaptateurSimulateur();
    }
}