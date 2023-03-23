package sk.isdd.parser;

public class LineParser {

    public static void parseLine(String line) {
        String aPackage = findPackage(line);
    }

    public static String findPackage(String line) {
        String[] strArr = line.split(" ", 2);
        if (strArr[0].equals("package")) {
            return strArr[1];
        }
        return null;
    }

    public static String findImport(String line) {
        String[] strArr = line.split(" ", 2);
        if (strArr[0].equals("import")) {
            return strArr[1];
        }
        return null;
    }
}
