package software.ulpgc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class SummaryWriter {
    public void write(Map<String, Integer> counts, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);

        for (Map.Entry<String, Integer> entry: counts.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
        }

        writer.flush();
    }
}
