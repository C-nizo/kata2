package software.ulpgc;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GenreCounter {
    public Map<String, Integer> count(List<Book> books) {
        Map<String, Integer> counts = new TreeMap<>();

        for (Book book: books) {
            String genre = book.genre();
            counts.put(genre, counts.getOrDefault(genre, 0) + 1);
        }

        return counts;
    }
}
