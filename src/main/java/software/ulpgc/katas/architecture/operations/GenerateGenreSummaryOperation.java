package software.ulpgc.katas.architecture.operations;

import software.ulpgc.katas.architecture.domain.GenreCounter;
import software.ulpgc.katas.architecture.model.Book;
import software.ulpgc.katas.architecture.ports.BookLoader;
import software.ulpgc.katas.architecture.ports.GenreSummaryWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class GenerateGenreSummaryOperation {
    private final BookLoader loader;
    private final GenreSummaryWriter writer;
    private final GenreCounter counter = new GenreCounter();

    public GenerateGenreSummaryOperation(BookLoader loader, GenreSummaryWriter writer) {
        this.loader = loader;
        this.writer = writer;
    }

    public void execute(InputStream input, OutputStream output) throws IOException {
        List<Book> books = loader.load(input);
        Map<String, Integer> counts = counter.count(books);
        writer.write(counts, output);
    }
}