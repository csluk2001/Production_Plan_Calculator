package src.main.java.services;

public class UserInputFormatterImpl {
    final String FORMATTER_INTEGER = "%,d";
    final String FORMATTER_DECIMAL = "%,.2f";


    /**
     * Converts an integer input to a comma-separated string.
     *
     * @param integer the integer to convert
     * @return the comma-separated string representation of the input
     */
    public String addFormatToInteger(int integer) {
        return String.format(FORMATTER_INTEGER, integer);
    }

    /**
     * Converts a String input to a comma-separated string.
     *
     * @param str the integer to convert
     * @return the comma-separated string representation of the input
     */
    public String addFormatToIntegerString(String str) {
        return String.format(FORMATTER_INTEGER, Integer.parseInt(str));
    }

    /**
     * Remove comma-separated string format.
     *
     * @param str the String to convert
     * @return the non-comma-separated string representation of the input
     */
    public String removeFormat(String str) {
        return str.replace(",", "");
    }
}
