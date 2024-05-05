package main;

public class OperatingSystem {
    private static final String OperatingSystem = System.getProperty("os.name");

    public static boolean isItMacOS() {
        return OperatingSystem.equals("Mac OS X"); // if name is macOS, return true
    }
}
