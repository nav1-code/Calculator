import java.util.Scanner;

/**
 * Created by admin on 4/29/2021.
 */

public class Main {
    public static void main(String[] args) throws Exception {

        String regexOperation = "[+-/*]";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("input: ");

            String expression = scanner.nextLine();

            if (EvaluateExpression.evaluateMathOperation(expression)) {

                if (EvaluateExpression.evaluateMathExpression(expression)) {
                    switch (EvaluateExpression.checkSystemNumbers(expression.split(regexOperation))) {
                        case ARABIC: {
                            CalculateArabicNumbers arabic = new CalculateArabicNumbers(expression);
                            System.out.println("output: " + arabic.calculate(arabic.getVar1(), arabic.getVar2(), arabic.getOperation()));
                        }
                        break;
                        case ROMAN: {
                            CalculateRomanNumbers roman = new CalculateRomanNumbers(expression);

                            int result = roman.calculate(roman.getVar1(), roman.getVar2(), roman.getOperation());

                            if (result == 0) {
                                System.out.println("output: ''(nullo)");
                            } else if (result < 0) {
                                System.out.println("output: -" + roman.arabicToRoman(Math.abs(result)));
                            } else System.out.println("output: " + roman.arabicToRoman(result));
                        }
                        break;
                        default:
                            throw new Exception("Invalid expression");
                    }
                }
            }

            System.out.print("Would you like to continue (Y/N)... ");
            expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("N")) System.exit(0);
            else continue;
        }
    }
}
