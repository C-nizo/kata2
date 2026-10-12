package software.ulpgc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvBookLoader implements BookLoader {
    @Override
    public List<Book> load(InputStream input) throws IOException {
        List<Book> books = new ArrayList<>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8)
        );

        String header = reader.readLine();
        if (header == null) {
            throw new IOException("The CSV file is empty");
        }

        if (!header.equals("titulo,genero")) {
            throw new IOException("Invalid CSV header: " + header);
        }

        String line;
        int lineNumber = 1;

        while ((line = reader.readLine()) != null) {
            lineNumber++;

            String[] values = line.split(",", -1);
            if (values.length != 2 ||
                    values[0].isBlank() ||
                    values[1].isBlank()
            ) {
                throw new IOException("Invalid CSV row at line: " + lineNumber);
            }

            books.add(new Book(values[0].trim(), values[1].trim()));
        }

        return books;
    }
}
