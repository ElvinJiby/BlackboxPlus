package main;

public class OperatingSystem {
    private static String OSName = System.getProperty("os.name");

    public static boolean isItMacOS() {
        return OSName.equals("Mac OS X"); // if name is macOS, return true
    }
}
