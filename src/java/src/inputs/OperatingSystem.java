package inputs;

public class OperatingSystem {
    public static boolean isItMacOS() {
        return System.getProperty("os.name").equals("MacOS X"); // if name is macOS, return true
    }
}
