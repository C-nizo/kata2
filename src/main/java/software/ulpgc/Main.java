package software.ulpgc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() {
        try (InputStream input1 = new FileInputStream("src/main/resources/libros.csv");
             InputStream input2 = Main.class.getResourceAsStream("/libros.csv");
             ByteArrayOutputStream output2 = new ByteArrayOutputStream();
        ) {
            System.out.println("Entrada: fichero | Salida: consola");
            process(input1, System.out);

            System.out.println("\nEntrada: recurso del classpath | Salida: memoria");

            if (input2 == null) {
                throw new IOException("Resource libros.csv not found");
            }

            process(input2, output2);
            System.out.print(output2.toString(StandardCharsets.UTF_8));

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
