package fr.isea.calcul_taxes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.isea.calcul_taxes.Helpers.*;

public class PanierTest {
    @Test
    void calcule_total_et_taxes() {
        var ligne1 = un(produitExempté("livre", "12.49"));
        var ligne2 = un(produitNormal("CD musical", "14.99"));
        var ligne3 = un(produitExempté("barre de chocolat", "0.85"));


        var panier = new Panier(
            List.of(ligne1, ligne2, ligne3)
        );

        check(panier.taxes(), "1.50");
        check(panier.total(), "29.83");
    }
}
