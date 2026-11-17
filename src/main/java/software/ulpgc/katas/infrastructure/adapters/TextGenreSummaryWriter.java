package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.architecture.ports.GenreSummaryWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TextGenreSummaryWriter implements GenreSummaryWriter {

    @Override
    public void write(Map<String, Integer> genreCounts, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);

        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
        }

        writer.flush();
    }
}