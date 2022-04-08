import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Scanner;

public class Calculator {

    public Calculator(String expression) {
        // original input
        this.expression = expression;

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();

        // calculate reverse polish notation
        this.rpnToResult();
    }

    private void tokensToReversePolishNotation() {
        // contains final list of tokens in RPN
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack tokenStack = new Stack();
        for (String token : tokens) {
            switch (token) {
                // If left bracket push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.size() != 0 && !tokenStack.peek().equals("(")) {
                        reverse_polish.add((String) tokenStack.pop());
                    }
                    tokenStack.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                case "√":
                case "PYTHAG":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while (tokenStack.size() != 0 && isOperator((String) tokenStack.peek())) {
                        if (isPrecedent(token, (String) tokenStack.peek())) {
                            reverse_polish.add((String) tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default: // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
            }
        }
        // Empty remaining tokens
        while (tokenStack.size() != 0) {
            reverse_polish.add((String) tokenStack.pop());
        }

    }

    // Helper definition for supported operators
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("^", 2);
        OPERATORS.put("√", 2);
        OPERATORS.put("PYTHAG", 2);
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
    }

    // Helper definition for supported operators
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }

    // Test if token is an operator
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an separator
    private boolean isSeperator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0);
    }

    // Term Tokenizer takes original expression and converts it to ArrayList of
    // tokens
    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0; // term split starting index
        StringBuilder multiCharTerm = new StringBuilder(); // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if (isOperator(c.toString()) || isSeperator(c.toString())) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator or parenthesis term to list
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    private Double Calculate(Double num1, Double num2, String operator) {
        Double answer = 0.0;
        switch (operator) { // switch case based on what operation we want to run.
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            case "/":
                answer = num1 / num2;

                break;

            case "^":
                answer = Math.pow(num1, num2);
                break;

            case "%":
                answer = num1 % num2;
                break;

            case "√":
                answer = Math.sqrt(num2);
            case "PYTHAG":
                answer = Math.sqrt(Math.pow(num1, 2) + Math.pow(num2, 2));

            default:
                System.out.println("Operator did not work");
                break;
        }
        return answer;
    }

    // Key instance variables
    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;

    private Double rpnToResult() {
        Stack<Double> calculation = new Stack<>();
        Double num1 = 0.0, num2 = 0.0; // Initialize the top two numbers in the top of the stack
        for (int i = 0; i < reverse_polish.size(); i++) { // Loop to iterate through the whole array
            String current_Token = reverse_polish.get(i);
            if (isOperator(current_Token)) { // If the current token is a operator, pop the two nums in the top of the
                                             // stack.
                num2 = calculation.pop();
                if (!current_Token.equals("√")) { // only need num 2 for sqrt
                    num1 = calculation.pop();
                }
                result = Calculate(num1, num2, current_Token); // Method runs math operations.
                calculation.add(result);
            } else {
                calculation.add(Double.valueOf(reverse_polish.get(i)));
            }

        }

        return result;

    }

    // Print the expression, terms, and result
    public String toString() {
        return ("Original expression: " + this.expression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " + this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.result));
    }

    public static void main(String[] args) {

        Calculator simpleMath = new Calculator("100 + 200  * 3");
        System.out.println("Simple Math\n" + simpleMath);
        System.out.println("");

        Calculator parenthesisMath = new Calculator("(100 + 200)  * 3");
        System.out.println("Parenthesis Math\n" + parenthesisMath);
        System.out.println("");

        Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
        System.out.println("All Math\n" + allMath);
        System.out.println("");

        Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
        System.out.println("All Math2\n" + allMath2);
        System.out.println("");

        Calculator exponent = new Calculator("2 ^ 4 + 500"); // Math problem with exponent
        System.out.println("Exponent\n" + exponent);
        System.out.println("");

        Calculator triangle = new Calculator("√((3^2) + (4^2))"); // EC: Math problem with sqrt
        System.out.println("Triangle\n" + triangle);
        System.out.println("");

        // scanner class
        Scanner sc = new Scanner(System.in);
        System.out.println("Import Calculations: ");
        String equation = sc.nextLine();
        Calculator input = new Calculator(equation);
        System.out.println("Input\n" + input);
        System.out.println("");
        System.out.println("");
    }
}
