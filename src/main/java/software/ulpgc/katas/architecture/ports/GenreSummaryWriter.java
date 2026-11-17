package software.ulpgc.katas.architecture.ports;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface GenreSummaryWriter {
    void write(Map<String, Integer> genreCounts, OutputStream output) throws IOException;
}