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
        String ONP = ToONP(expression);
        System.out.println("ONP: " + ONP);
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < ONP.length(); i++) {
            char c = ONP.charAt(i);
            if ((int) c > 47 && (int) c < 58) {
                stack.push(Character.toString(c));
            } else {
                float a = Float.parseFloat(stack.pop());
                float b = Float.parseFloat(stack.pop());
                float result = 0;

                if (c == '+')
                    result = b + a;
                else if (c == '-')
                    result = b - a;
                else if (c == '*')
                    result = b * a;
                else if (c == '/')
                    result = b / a;

                stack.push(Float.toString(result));
            }
        }

        return Float.parseFloat(stack.pop());
    }


    public static void main(String[] args) {
        String expression = "(1+2)*3/4-5";
        System.out.println("Expression: " + expression);
        System.out.println("Result: " + Parser(expression));
    }
}