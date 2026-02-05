package fr.isea.calcul_taxes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Please specify input path as first argument");
            System.exit(2);
        }

        var inputPath = args[0];
        var contents = Files.readString(Path.of(inputPath));
        System.out.println("INPUT");
        System.out.println(contents);

        var facture = Facture.parse(contents);

        System.out.println("OUTPUT");
        System.out.println(facture.display());
    }
}
