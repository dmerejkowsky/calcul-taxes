package fr.isea.calcul_taxes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.isea.calcul_taxes.Helpers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FactureTest {


    @Test
    void parse() {
        var input = """
            1 livre à 12.49
            1 CD musical à 14.98
            """;

        var facture = Facture.parse(input);
        var panier = facture.panier();
        var lignes = panier.lignes();

        assertThat(lignes).isEqualTo(List.of(
            un(produitExempté("livre", "12.49")),
            un(produitNormal("CD musical", "14.98")))
        );
    }

    @Test
    void test_1() {
        var input = """
            1 livre à 12.49
            1 CD musical à 14.99
            1 barre de chocolat à 0.85
            """;
        var facture = Facture.parse(input);

        assertThat(facture.display()).isEqualTo("""
            1 livre : 12.49
            1 CD musical : 16.49
            1 barre de chocolat : 0.85
            Montant des taxes : 1.50
            Total : 29.83
            """);
    }

    @Test
    void test_2() {
        var input = """
            1 boîte de chocolats importée à 10.00
            1 flacon de parfum importé à 47.50
            """;
        var facture = Facture.parse(input);

        assertThat(facture.display()).isEqualTo("""
            1 boîte de chocolats importée : 10.50
            1 flacon de parfum importé : 54.65
            Montant des taxes : 7.65
            Total : 65.15
            """);
    }

    @Test
    void test_3() {
        var input = """
            1 flacon de parfum importé à 27.99
            1 flacon de parfum à 18.99
            1 boîte de pilules contre la migraine à 9.75
            1 boîte de chocolats importés à 11.25
            """;
        var facture = Facture.parse(input);

        assertThat(facture.display()).isEqualTo("""
            1 flacon de parfum importé : 32.19
            1 flacon de parfum : 20.89
            1 boîte de pilules contre la migraine : 9.75
            1 boîte de chocolats importés : 11.85
            Montant des taxes : 6.70
            Total : 74.68
            """);
    }


}
