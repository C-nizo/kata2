package software.ulpgc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() {
        try {
            InputStream input = new FileInputStream("src/main/resources/libros.csv");
            OutputStream output = System.out;

            process(input, output);
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
