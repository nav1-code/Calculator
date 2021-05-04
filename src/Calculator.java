/**
 * Created by admin on 5/4/2021.
 */
public class Calculator {
    public int calculate(int var1, int var2, String operation) {
        int result = 0;
        switch (operation){
            case "+": result = var1 + var2; break;
            case "-": result = var1 - var2; break;
            case "*": result = var1 * var2; break;
            case "/": result = var1 / var2; break;
            default:throw  new IllegalArgumentException("Invalid operation sign");
        }
        return result;
    }
}
