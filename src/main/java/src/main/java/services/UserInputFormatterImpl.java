package src.main.java.services;

public class UserInputFormatterImpl {
    final String FORMATTER_INTEGER = "%,d";
    final String FORMATTER_DECIMAL = "%,.2f";

    public String addFormatToInteger(int integer) {
        return String.format(FORMATTER_INTEGER, integer);
    }

    public String addFormatToIntegerString(String str) {
        return String.format(FORMATTER_INTEGER, Integer.parseInt(str));
    }


    public String removeFormat(String str) {
        return str.replace(",", "");
    }
}
