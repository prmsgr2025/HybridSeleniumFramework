package tests;

import config.ConfigReader;

public class ConfigTest {

    public static void main(String[] args) {

        System.out.println("Environment: "
                + ConfigReader.getEnvironment());

        System.out.println("Browser: "
                + ConfigReader.getProperty("browser"));

        System.out.println("Execution Mode: "
                + ConfigReader.getProperty("executionMode"));

        System.out.println("Grid URL: "
                + ConfigReader.getProperty("gridUrl"));

        System.out.println("Base URL: "
                + ConfigReader.getProperty("baseUrl"));

        System.out.println("Explicit Wait: "
                + ConfigReader.getIntProperty("explicitWait"));

        System.out.println("Thread Count: "
                + ConfigReader.getIntProperty("threadCount"));
    }
}