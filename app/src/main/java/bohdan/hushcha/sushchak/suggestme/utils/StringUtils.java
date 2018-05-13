package bohdan.hushcha.sushchak.suggestme.utils;


/**
 * Class for normalise ingredients parameters for api
 *
 * @author Bohdan
 * @version 1.0
 * @since 1.0
 */
public class StringUtils {

    /**
     * Method to trim and normalise parameters for api
     *
     * @param text query ingredients
     * @return return normalise query for api
     */
    public static String FormatIngredients(String text) {
        if (text == null)
            throw new IllegalArgumentException("text cannot be null");

        text = text.trim();

        String formatedString = text;

        if (text.contains(",")) {
            StringBuilder builder = new StringBuilder();

            String[] arr = text.split(",");

            for (String item : arr) {
                String newItem = item.trim().toLowerCase();

                if (!newItem.isEmpty() && !isNumeric(item)) {

                    if (newItem.contains(" ") && !newItem.isEmpty()) {
                        String[] subArr = newItem.split(" ");
                        for (String subItem : subArr) {
                            if (!subItem.isEmpty() && !isNumeric(subItem)) {
                                builder.append(subItem.trim().toLowerCase());
                                builder.append(",");
                            }
                        }
                    } else {
                        builder.append(newItem);
                        builder.append(",");
                    }
                }
            }

            if (!builder.toString().isEmpty())
                formatedString = builder.substring(0, builder.length() - 1);
            else formatedString = "";
        }

        return formatedString;
    }

    /**
     * Method for check if string is number
     *
     * @param str string
     * @return true if str is number, false if not
     */
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
