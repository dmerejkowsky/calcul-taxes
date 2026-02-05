package fr.isea.calcul_taxes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;


public class LigneParser {

    public static final String[] EXEMPTÉS = new String[]{"livre", "chocolat", "pilule"};

    public static Ligne parse(String text) {
        var words = text.split(" ");
        var numWords = words.length;
        var quantité = Integer.valueOf(words[0]);
        var dénomination = Arrays.stream(words, 1, numWords - 2).collect(Collectors.joining(" "));
        var prix = new BigDecimal(words[numWords - 1]);
        var exempté = isExempté(dénomination);
        var importé = dénomination.contains("importé");


        var produit = new Produit(dénomination, prix, exempté, importé);
        return new Ligne(quantité, produit);
    }

    static boolean isExempté(String dénomination) {
        for (var subString : EXEMPTÉS) {
            if (dénomination.contains(subString)) {
                return true;
            }
        }
        return false;
    }
}
