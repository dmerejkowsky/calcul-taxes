package fr.isea.calcul_taxes;

import org.junit.jupiter.api.Test;

import static fr.isea.calcul_taxes.Helpers.*;
import static fr.isea.calcul_taxes.LigneParser.parse;
import static org.assertj.core.api.Assertions.assertThat;

public class LigneParserTest {

    @Test
    void parse_ligne_défaut() {
        var ligne = parse("1 CD musical à 12.49");

        assertThat(ligne.quantité()).isEqualTo(1);
        assertThat(ligne.produit()).isEqualTo(produitNormal("CD musical", "12.49"));
    }

    @Test
    void parse_ligne_exempté() {
        var ligne = parse("1 livre à 5.42");

        assertThat(ligne.quantité()).isEqualTo(1);
        assertThat(ligne.produit()).isEqualTo(produitExempté("livre", "5.42"));
    }

    @Test
    void parse_ligne_importé() {
        var ligne = parse("1 CD importé à 5.42");

        assertThat(ligne.quantité()).isEqualTo(1);
        assertThat(ligne.produit()).isEqualTo(produitImporté("CD importé", "5.42"));
    }
}
