package fr.isea.calcul_taxes;

import java.math.BigDecimal;

public record Produit(String dénomination, BigDecimal prix, boolean exempté, boolean importé) {

    public BigDecimal getPrixTaxesComprises() {
        var taxe = CalculTaxe.taxe(this);
        return this.prix.add(CalculTaxe.arrondi(this.prix.multiply(taxe)));
    }
}
