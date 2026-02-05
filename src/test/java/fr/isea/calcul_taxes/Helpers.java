package fr.isea.calcul_taxes;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class Helpers {
    public static Produit produitNormal(String dénomination, String prix) {
        return new Produit(dénomination, new BigDecimal(prix), false, false);
    }

    public static Produit produitImporté(String dénomination, String prix) {
        return new Produit(dénomination, new BigDecimal(prix), false, true);

    }

    public static Produit produitExempté(String dénomination, String prix) {
        return new Produit(dénomination, new BigDecimal(prix), true, false);
    }

    public static Produit produitImportéEtExempté(String dénomination, String prix) {
        return new Produit(dénomination, new BigDecimal(prix), true, true);
    }


    public static void check(BigDecimal montant, String valeur) {
        assertThat(montant.compareTo(new BigDecimal(valeur))).isEqualTo(0);
    }

    public static Ligne un(Produit produit) {
        return new Ligne(1, produit);
    }
}
