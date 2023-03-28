package sk.isdd.print;

import java.util.List;
import java.util.Map;

public class PlantUmlPrinter {

    private static final String START_UML = "@startuml";
    private static final String END_UML = "@enduml";


    public static void print(Map<String, List<String>> map) {
        System.out.println(START_UML);

        map.forEach((key, valueList) ->
                valueList.forEach(val -> printLine(key, val)));

        System.out.println(END_UML);
    }

    private static void printLine(String key, String val) {
        System.out.println("[" + key + "]" + " --> " + "[" + val + ']');
    }

    public static void printOld(Map<String, List<String>> res) {
        res.forEach((s, strings) -> System.out.println(s + " -> " + strings));
    }

}
