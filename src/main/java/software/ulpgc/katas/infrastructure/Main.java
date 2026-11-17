package software.ulpgc.katas.infrastructure;

import software.ulpgc.katas.architecture.operations.GenerateGenreSummaryOperation;
import software.ulpgc.katas.infrastructure.adapters.CsvBookLoader;
import software.ulpgc.katas.infrastructure.adapters.TextGenreSummaryWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String CSV_DATA = """
            titulo,genero
            El principito,Ficción
            1984,Distopía
            Fahrenheit 451,Distopía
            Dune,Ciencia ficción
            Fundación,Ciencia ficción
            La historia interminable,Fantasía
            """;

    static void main() {
        try {
            processFileToConsole();
            processMemoryToConsole();
            processMemoryToMemory();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void processFileToConsole() throws IOException {
        System.out.println("File input:");

        try (InputStream input = new FileInputStream("src/main/resources/libros.csv")) {
            process(input, System.out);
        }
    }

    private static void processMemoryToConsole() throws IOException {
        System.out.println("\nMemory input:");

        InputStream input = new ByteArrayInputStream(
                CSV_DATA.getBytes(StandardCharsets.UTF_8)
        );

        process(input, System.out);
    }

    private static void processMemoryToMemory() throws IOException {
        InputStream input = new ByteArrayInputStream(
                CSV_DATA.getBytes(StandardCharsets.UTF_8)
        );

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        process(input, output);

        System.out.println("\nMemory output:");
        System.out.print(output.toString(StandardCharsets.UTF_8));
    }

    private static void process(InputStream input, OutputStream output) throws IOException {
        GenerateGenreSummaryOperation operation = new GenerateGenreSummaryOperation(
                new CsvBookLoader(),
                new TextGenreSummaryWriter()
        );

        operation.execute(input, output);
    }
}