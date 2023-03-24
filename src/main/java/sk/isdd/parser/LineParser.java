package sk.isdd.parser;

public class LineParser {

    public static String findPackage(String line) {
        String[] strArr = line.split(" ", 2);
        if (strArr[0].equals("package")) {
            return strArr[1];
        }
        return null;
    }

    public static String findImport(String line) {
        String[] strArr = line.split(" ", 2);
        if (strArr[0].equals("import") && strArr[1].contains("metais")) {
            return strArr[1];
        }
        return null;
    }
}
