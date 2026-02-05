package fr.isea.calcul_taxes;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Facture {
    private final Panier panier;

    public Facture(Panier panier) {
        this.panier = panier;
    }

    @NotNull
    public static String asString(BigDecimal montant) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        return numberFormat.format(montant);
    }

    public static Facture parse(String input) {
        List<Ligne> lignes = new ArrayList<>();
        for (var ligne : input.split("\n")) {
            ligne = ligne.strip();
            if (ligne.isBlank()) {
                continue;
            }
            var parsed = LigneParser.parse(ligne);
            lignes.add(parsed);
        }

        var panier = new Panier(lignes);
        return new Facture(panier);
    }

    public Panier panier() {
        return this.panier;
    }

    public String display() {
        var sb = new StringBuilder();

        for (var ligne : panier.lignes()) {
            var quantité = ligne.quantité();
            var dénomination = ligne.produit().dénomination();
            var prixTaxesComprises = ligne.montantTaxesComprises();
            String details = String.format("%d %s : %s\n", quantité, dénomination, asString(prixTaxesComprises));
            sb.append(details);
        }

        var taxes = panier.taxes();
        sb.append(String.format("Montant des taxes : %s\n", asString(taxes)));

        var total = panier.total();
        sb.append(String.format("Total : %s\n", asString(total)));

        return sb.toString();
    }
}
