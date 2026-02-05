package fr.isea.calcul_taxes;

import java.math.BigDecimal;
import java.util.List;

public class Panier {
    private final List<Ligne> lignes;

    public Panier(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    public BigDecimal total() {
        var total = new BigDecimal(0);

        for (var ligne : lignes) {
            var montant = ligne.montantTaxesComprises();
            total = total.add(montant);
        }

        return total;
    }

    public BigDecimal taxes() {
        var totalHorsTaxes = new BigDecimal(0);

        for (var ligne : lignes) {
            var montant = ligne.montantHorsTaxes();
            totalHorsTaxes = totalHorsTaxes.add(montant);
        }

        return total().subtract(totalHorsTaxes);
    }

    public List<Ligne> lignes() {
        return this.lignes;
    }
}
