import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 5/3/2021.
 */
public class EvaluateExpression {

    private static String regexOperation = "[+-/*]";
    private static String regexRomanNumbers = "[IVXLCDM]"; //"(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})"; //"(X{0,2})(IX|IV|V?I{0,3})";//"(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})"; //"[IVXLCDM]";
    private static String regexRoman = "(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
    private static String regexArabicNumbers = "\\d{1,2}"; //"[0-9]";
    private static String regexDecimal = "^[\\+\\-]{0,1}[0-9]+[\\.\\,][0-9]+$";

    public EvaluateExpression(){}

    public static int evaluateIntegerNumber(String number) throws Exception {
        if (!Pattern.matches(regexDecimal, (CharSequence) number)) {
            return Integer.parseInt(number);
        } else throw new Exception("Invalid format number. The number should be integer");
    }

    public static String evaluateRomanNumber(String number) throws Exception {
        if (Pattern.matches(regexRoman, (CharSequence) number))
            return number;
        else throw new Exception("Invalid roman number, try to introduce combination of next symbols: I, V, X");
    }

    public static boolean evaluateRangeNumber(int number) throws Exception {
        if ((number > 0) && (number <= 10)) {
            return true;
        } else throw new Exception("The number entered should be in the range 1-10 inclusive");
    }

    public static boolean evaluateMathOperation(String expression) throws Exception {
        Matcher matcher = Pattern.compile(regexOperation).matcher(expression);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        if(matches == 1)
            return true;
        else throw new Exception("Invalid symbol of operation");
    }

    public static boolean evaluateMathExpression(String expression) throws Exception {
        String[] operands = expression.split(regexOperation);
        if (operands.length == 2)
            return true;
        else throw new Exception("Invalid expression. The expression should be in the format 2 + 3 for the Arabic numeral system and II + VII for the Roman numeral system");
    }

    public static SystemNumbers checkSystemNumbers(String[] numbers) throws Exception {
        int countArabic = 0;
        int countRoman = 0;
        for (String item : numbers) {
            if (Pattern.compile(regexRomanNumbers).matcher(item).find())
                countRoman++;
            else countArabic++;
        }

        if(countArabic == 2)
            return SystemNumbers.ARABIC;
        else if (countRoman == 2)
            return SystemNumbers.ROMAN;
        else throw new Exception("Invalid expression. Both operators must relate to the same number system");
    }
}
