
package readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class JsonDataReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonDataReader() {
    }

    public static List<Map<String, Object>> readJson(String fileName) {

        try (InputStream input = JsonDataReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException(
                        "JSON file not found: " + fileName);
            }

            return mapper.readValue(
                    input,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read JSON file: " + fileName, e);
        }
    }
}
