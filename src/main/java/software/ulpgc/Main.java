package software.ulpgc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;

public class Main {
    static void main() {
        String csvData = """
                titulo,genero
                El principito,Ficción
                1984,Distopía
                Fahrenheit 451,Distopía
                Dune,Ciencia ficción
                Fundación,Ciencia ficción
                La historia interminable,Fantasía
                """;

        try (
                InputStream fileInput = new FileInputStream("src/main/resources/libros.csv");
                InputStream memoryInput = new ByteArrayInputStream(
                        csvData.getBytes(StandardCharsets.UTF_8))
        ) {
            System.out.println("File input: ");
            process(fileInput, System.out);

            System.out.println("\nMemory input: ");
            process(memoryInput, System.out);

            try (
                    InputStream memoryInputForFile = new ByteArrayInputStream(
                            csvData.getBytes(StandardCharsets.UTF_8)
                    );
                    OutputStream fileOutput = new FileOutputStream("genre-summary.txt")
            ) {
                process(memoryInputForFile, fileOutput);
            }

            System.out.println("\nSummary saved to genre-summary.txt");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void process(InputStream input, OutputStream output) throws IOException {
        BookLoader loader = new CsvBookLoader();
        List<Book> books = loader.load(input);

        GenreCounter counter = new GenreCounter();
        Map<String, Integer> counts = counter.count(books);

        SummaryWriter writer = new SummaryWriter();
        writer.write(counts, output);
    }
}
