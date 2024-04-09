package inputs;

public class OperatingSystem {
    public static boolean isItMacOS() {
        String operatingSystem = System.getProperty("os.name");
         return operatingSystem.equals("Mac OS X");
    }
}
