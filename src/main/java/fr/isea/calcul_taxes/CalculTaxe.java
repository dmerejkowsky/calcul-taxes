package fr.isea.calcul_taxes;

import java.math.BigDecimal;

public class CalculTaxe {

    public static final BigDecimal TAXE_NORMALE = new BigDecimal("0.1");
    public static final BigDecimal SURTAXE_IMPORTATION = new BigDecimal("0.05");

    public static BigDecimal taxe(Produit produit) {
        var total = new BigDecimal(0);

        if (produit.importé()) {
            total = total.add(SURTAXE_IMPORTATION);
        }

        if (!produit.exempté()) {
            total = total.add(TAXE_NORMALE);
        }

        return total;
    }

    public static BigDecimal arrondi(BigDecimal montant) {
        int inCents = montant.multiply(new BigDecimal(100)).intValue();
        int reminder = inCents % 5;
        int result = inCents - reminder;

        // Make sure we round up
        if (inCents > result) {
            result += 5;
        }

        return new BigDecimal(result).divide(new BigDecimal(100));
    }

}
