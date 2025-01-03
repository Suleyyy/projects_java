import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static String ToONP(String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();
        Map<Character,Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if ((int)c > 47 && (int)c < 58) {
                output.append(c);
            }
            if (c == '(') {
                stack.push('(');
            }
            if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }
            if(c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty() && stack.peek() != '(' && priority.get(stack.peek()) >= priority.get(c)) {
                    output.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            output.append(stack.pop());

        return output.toString();
    }

    public static double Parser(String expression)
    {
        System.out.println("ONP: " + ToONP(expression));
        return 1.23;
    }


    public static void main(String[] args) {
        String expression = "(1+2)*3/4-5";
        System.out.println("Expression: " + expression);
        System.out.println(Parser(expression));

    }
}