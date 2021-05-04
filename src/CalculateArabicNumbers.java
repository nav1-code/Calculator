import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 5/3/2021.
 */
public class CalculateArabicNumbers extends Calculator {

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

    public CalculateArabicNumbers(String expression) throws Exception {
        this.operation = retriveMathOperator("[+-/*]", expression);
        String[] numbers = expression.split("[+-/*]");

        if (numbers.length == 2) {
            this.var1 = evaluate(numbers[0]);
            this.var2 = evaluate(numbers[1]);
        } else throw new Exception("Invalid expression. The expression should be in the format 2 + 3 for the Arabic numeral system and II + VII for the Roman numeral system");
    }

    private int evaluate(String number) throws Exception {
        int result = EvaluateExpression.evaluateIntegerNumber(number);

        // Evaluate range number
        if ((result > 0) && (result <= 10)) {
            return result;
        } else throw new Exception("The number entered should be in the range 1-10 inclusive");
    }

    private String retriveMathOperator(String regex, String text) throws Exception {
        Matcher matcher = Pattern.compile(regex).matcher(text);

        if (matcher.find())
            return matcher.group();
        else throw new Exception("Invalid symbol of operation");
    }
}
