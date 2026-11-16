
package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties CONFIG =
            new Properties();

    private static final Properties ENV_CONFIG =
            new Properties();

    private static final String ACTIVE_ENV;

    static {

        // Load common framework configuration
        loadProperties("config.properties", CONFIG);

        // System property takes priority over config.properties
        ACTIVE_ENV = System.getProperty(
                "env",
                CONFIG.getProperty("env", "qa")
        ).trim().toLowerCase();

        // Load environment-specific configuration
        loadProperties(
                ACTIVE_ENV + ".properties",
                ENV_CONFIG
        );

        System.out.println(
                "Active Environment: " + ACTIVE_ENV
        );
    }

    private ConfigReader() {
        // Prevent object creation
    }

    private static void loadProperties(
            String fileName,
            Properties properties) {

        try (InputStream input =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException(
                        "Configuration file not found: "
                                + fileName
                );
            }

            properties.load(input);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load: " + fileName, e
            );
        }
    }

    public static String getProperty(String key) {

        // Priority 1: Maven / Jenkins system properties
        String systemValue = System.getProperty(key);

        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }

        // Priority 2: Environment-specific properties
        String envValue = ENV_CONFIG.getProperty(key);

        if (envValue != null && !envValue.isBlank()) {
            return envValue.trim();
        }

        // Priority 3: Common framework properties
        String configValue = CONFIG.getProperty(key);

        if (configValue != null && !configValue.isBlank()) {
            return configValue.trim();
        }

        throw new RuntimeException(
                "Configuration property not found: " + key
        );
    }

    public static String getEnvironment() {
        return ACTIVE_ENV;
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
