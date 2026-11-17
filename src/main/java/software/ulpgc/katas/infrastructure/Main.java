package software.ulpgc.katas.infrastructure;

import software.ulpgc.katas.architecture.operations.GenerateGenreSummaryOperation;
import software.ulpgc.katas.infrastructure.adapters.CsvBookLoader;
import software.ulpgc.katas.infrastructure.adapters.TextGenreSummaryWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    static void main() {
        try {
            processFileToConsole();
            processResourceToMemory();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void processFileToConsole() throws IOException {
        System.out.println("Entrada: fichero | Salida: consola");

        try (InputStream input = new FileInputStream("src/main/resources/libros.csv")) {
            process(input, System.out);
        }
    }

    private static void processResourceToMemory() throws IOException {
        System.out.println("\nEntrada: recurso del classpath | Salida: memoria");

        try (InputStream input = Main.class.getResourceAsStream("/libros.csv");
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {

            if (input == null) {
                throw new IOException("Resource libros.csv not found");
            }

            process(input, output);
            System.out.print(output.toString(StandardCharsets.UTF_8));
        }
    }

    private static void process(InputStream input, OutputStream output) throws IOException {
        GenerateGenreSummaryOperation operation = new GenerateGenreSummaryOperation(
                new CsvBookLoader(),
                new TextGenreSummaryWriter()
        );

        operation.execute(input, output);
    }
}