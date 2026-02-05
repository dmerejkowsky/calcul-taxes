package fr.isea.calcul_taxes;

import org.junit.jupiter.api.Test;

import static fr.isea.calcul_taxes.Helpers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProduitTests {

    @Test
    void calcul_prix_taxes_comprises_exempté() {
        var produit = produitExempté("livre", "12.49");

        assertThat(produit.getPrixTaxesComprises()).isEqualTo("12.49");
    }

    @Test
    void calcul_prix_taxes_comprises_pour_produit_normal() {
        var produit = produitNormal("CD musical", "14.99");

        assertThat(produit.getPrixTaxesComprises()).isEqualTo("16.49");
    }

    @Test
    void calcul_prix_taxes_comprises_pour_produit_importé() {
        var produit = produitImporté("flacon de parfum", "47.50");

        assertThat(produit.getPrixTaxesComprises()).isEqualTo("54.65");
    }
}
