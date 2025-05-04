package simulateur;

import com.kerware.simulateur.AdaptateurSimulateur;
import com.kerware.simulateur.ICalculateurImpot;
import org.junit.jupiter.api.DisplayName;

/**
 * Classe concrète de test pour l'ancien adaptateur.
 */
@DisplayName("Tests sur l'ancien adaptateur (package com.kerware.simulateur)")
public class TestsAncienAdaptateur extends TestsSimulateur{

    @Override
    protected ICalculateurImpot getSimulateur() {
        return new AdaptateurSimulateur();
    }
}