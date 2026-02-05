package fr.isea.calcul_taxes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static fr.isea.calcul_taxes.Helpers.*;

public class CalculTaxeTests {

    private static void checkTaxe(Produit produit, String valeur) {
        check(CalculTaxe.taxe(produit), valeur);
    }

    @Test
    void taxe_sur_produit_normal() {
        var produit = produitNormal("CD Musical", "10");

        checkTaxe(produit, "0.1");
    }

    @Test
    void taxe_sur_produit_exempté() {
        var produit = produitExempté("Livre", "5");

        checkTaxe(produit, "0.00");
    }

    @Test
    void taxe_sur_produits_importé_non_exempté() {
        var produit = produitImporté("CD Musical importé", "10");

        checkTaxe(produit, "0.15");
    }

    @Test
    void taxe_sur_produit_importé_et_exémpté() {
        var produit = produitImportéEtExempté("Livre importé", "10");

        checkTaxe(produit, "0.05");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0.95 :   0.95",
        "0.96 :   1.00",
        "0.97 :   1.00",
        "0.98 :   1.00",
        "0.99 :   1.00",
        "1    :    1.00",
        "1.01 :    1.05",
        "1.02 :    1.05",
        "1.03 :    1.05",
        "1.04 :    1.05",
        "1.05 :    1.05",
        "1.06 :    1.10",
        "100.04 : 100.05"
    }, delimiter = ':')
    void calcul_arrondi(String montant, String expected) {
        var arrondi = CalculTaxe.arrondi(new BigDecimal(montant));

        check(arrondi, expected);
    }


}
