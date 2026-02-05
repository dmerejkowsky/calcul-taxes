package fr.isea.calcul_taxes;

import java.math.BigDecimal;

public record Ligne(int quantité, Produit produit) {
    BigDecimal montantTaxesComprises() {
        var prixTaxesComprises = produit.getPrixTaxesComprises();
        return prixTaxesComprises.multiply(new BigDecimal(this.quantité));
    }

    BigDecimal montantHorsTaxes() {
        var prixHorsTaxes = produit.prix();
        return prixHorsTaxes.multiply(new BigDecimal(this.quantité));
    }
}
