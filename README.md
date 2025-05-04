# RÉUSINAGE DU SIMULATEUR D'IMPÔT SUR LE REVENU 2024

## Objectifs du réusinage
- Améliorer la lisibilité du code
- Documenter le code
- Remplacer les "nombres magiques" par des constantes ou paramètres nommés
- Respecter le principe de responsabilité unique (Single Responsibility)
- Éclater les fonctions trop longues en méthodes plus petites et spécifiques
- Rendre le code modulaire pour l'adapter aux années futures
- Garantir la traçabilité des exigences à travers le code

## Architecture du code réusiné
Le projet est composé de plusieurs package qui sépare les résponsabilités

### 1. `com.kerware.simulateur2024.modele`
- **FoyerFiscal** : foyer fiscal avec ses caractéristiques
- **ResultatCalculImpot** : résultats du calcul
- **SituationFamiliale** : énumération des situations familiales
- **TrancheImposition** : tranche du barème d'imposition
- **TrancheContributionExceptionnelle** : représente une tranche de la CEHR
- **BaremeFiscal** : paramètres du barème fiscal

### 2. `com.kerware.simulateur2024.service`
- **ICalculateurImpot2024** : interface du calculateur d'impôt
- **CalculateurImpot2024** : calculateur par étape
- **BaremeFiscalFabrique** : fabrique de barèmes fiscaux

### 3. `com.kerware.simulateur2024.adaptateur`
- **NouvelAdaptateurSimulateur** : adaptateur vers l'interface ICalculateurImpot

## Traçabilité vers les exigences
Les exigences métier sont renseignées en commentaire dans le code

- **EXG_IMPOT_02** : Calcul de l'abattement
- **EXG_IMPOT_03** : Calcul du nombre de parts fiscales
- **EXG_IMPOT_04** : Calcul de l'impôt par tranches
- **EXG_IMPOT_05** : Plafonnement du quotient familial
- **EXG_IMPOT_06** : Calcul de la décote
- **EXG_IMPOT_07** : Contribution exceptionnelle sur les hauts revenus

## Qualité du code
- Tests unitaires
- Couverture de code supérieure à 90 %
- Pas d'erreurs/avertissements lors de l'analyse statique par CheckStyle
- Code adaptable et modifiable facilement dans le futur

## Modification apportées
- Une fabrique à simulateur pour permettre de créer les versions futures
- TestSimulateur abstract pour pouvoir effectuer les tests sur l'ancien et le nouveau simulateur simultanément et comparer les résultats

## Mise à jour pour l'année 2025
Pour adapter le simulateur à l'année 2025, il suffira de :
1. Mettre à jour les paramètres du barème dans `BaremeFiscalFactory.creerBareme2025()`
2. Utiliser le constructeur paramétré de `CalculateurImpot2024` pour faire référence au barème 2025
