package software.ulpgc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

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
                InputStream input1 = new FileInputStream("src/main/resources/libros.csv");
                InputStream input2 = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));
                InputStream input3 = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));
                ByteArrayOutputStream output = new ByteArrayOutputStream()
        ) {
            System.out.println("File input: ");
            process(input1, System.out);

            System.out.println("\nMemory input: ");
            process(input2, System.out);

            System.out.println("\nMemory output: ");
            process(input3, output);
            System.out.println(output.toString(StandardCharsets.UTF_8));

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
