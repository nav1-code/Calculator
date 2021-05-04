import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 5/3/2021.
 */
public class CalculateRomanNumbers extends Calculator {
    public int getVar1() {
        return var1;
    }

    private int var1;

    public int getVar2() {
        return var2;
    }

    private int var2;

    public String getOperation() {
        return operation;
    }

    private String operation;

    public CalculateRomanNumbers(String expression) throws Exception {
        this.operation = retriveMathOperator("[+-/*]", expression);
        String[] numbers = expression.split("[+-/*]");

        if (numbers.length == 2) {
            this.var1 = evaluate(numbers[0]);
            this.var2 = evaluate(numbers[1]);
        } else throw new Exception();
    }

    private int evaluate(String number) throws Exception {
        String resultRomanNumber = EvaluateExpression.evaluateRomanNumber(number);
        //System.out.println(resultRomanNumber);
        int result = romanToArabic(resultRomanNumber);

        // Evaluate range number
        if ((result > 0) && (result <= 10)) {
            return result;
        } else throw new Exception();
    }

    private String retriveMathOperator(String regex, String text) throws Exception {
        Matcher matcher = Pattern.compile(regex).matcher(text);

        if (matcher.find())
            return matcher.group();
        else throw new Exception();
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {

        if ((number < 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (1, 4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
