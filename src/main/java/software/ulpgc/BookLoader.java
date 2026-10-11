package software.ulpgc;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface BookLoader {
    List<Book> load(InputStream input) throws IOException;
}
