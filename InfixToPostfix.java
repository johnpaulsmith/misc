/**
 *
 * @author John Paul Smith
 * 
 * Infix-to-postfix translation. The Shunting-yard algorithm.
 *
 * Currently this code works only on single-digit terms:
 * "12+3" and "1+23" will both give the result: "123+".
 * 
 * An example test input:
 * 
 * The postfix translation of 
 * '(1 + 8) / (6 - 5) * ((3 - 2) * (2 + 2))' is '18+65-/32-22+**'
 */
 
package misc;

import java.util.Stack;

public class InfixToPostfix {

    public static void main(String[] args) {

        //String exp = "a + b * c - (d / e)^f";
        String exp = "(1 + 8) * (6 - 5) / ((3 - 2) * (2 + 2))";

        try {
            System.out.println("The postfix translation of '" + exp + "' is "
                    + "'" + InfixToPostfix.infixToPostfix(exp) + "'");
        } catch (InvalidExpressionException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private InfixToPostfix() {
    }

    /**
     *
     * Translate an infix expression String into a postfix expression String.
     *
     * @param infix the String representation of the infix expression to
     * translate into postfix.
     * 
     * @return a String representation of the postfix translation of infix.
     */
    public static String infixToPostfix(String infix) 
            throws InvalidExpressionException, ParenthesesMismatchException {

        /**
         * Remove any spaces.
         */
        infix = infix.replace(" ", "");
        
        /**
         * The end-line termination character ';' is included here.
         */
        if (!infix.matches("[a-zA-Z0-9+-/*%^();]+?")) {
            
            throw new InvalidExpressionException("invalid infix expression: "
                    + infix);
        }        
        
        Stack<Character> s = new Stack<>();

        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); ++i) {

            Character c = infix.charAt(i);            

            /**
             * Process alphanumeric characters.
             */
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {

                if (i < (infix.length() - 1)
                        && Character.isAlphabetic(infix.charAt(i + 1))) {

                    throw new InvalidExpressionException("invalid infix "
                            + "expression: operands '" + infix.charAt(i) 
                            + "' and '" + infix.charAt(i + 1) + 
                            "' without operator");
                }

                if (i < (infix.length() - 1) && infix.charAt(i + 1) == '(') {
                    
                    throw new InvalidExpressionException("invalid infix "
                            + "expression: operand '" + infix.charAt(i) 
                            + " and '" + infix.charAt(i + 1) 
                            + "' adjacent without operator");
                }

                postfix.append(c);

            } else {

                /**
                 * Process operator, parenthesis, and end-line characters.
                 */
                
                /**
                 * Pop and append the remainder of the stack and return
                 */
                if (c == ';') {

                    while (!s.isEmpty()) {

                        postfix.append(s.pop());
                    }

                    return postfix.toString();
                }

                /**
                 * If the stack is empty, push the current non-alphabetic token
                 * and continue.
                 */
                if (s.isEmpty()) {

                    s.push(c);

                    continue;
                }

                /**
                 * For a non-empty stack
                 */
                /**
                 * Pop and append stack until first '(' found and continue.
                 */
                if (c == ')') {

                    while (s.peek() != '(') {

                        postfix.append(s.pop());

                        if (s.isEmpty()) {

                            throw new ParenthesesMismatchException("mismatched "
                                    + "parentheses in the infix expression: "
                                    + infix);
                        }
                    }

                    /**
                     * Discard the matching left parenthesis.
                     */
                    s.pop();

                    continue;
                }

                /**
                 * Evaluate the contents of the stack and perform the necessary
                 * operations depending on the current token. The '%', '^', and
                 * '(' have the highest precedence and are pushed regardless so
                 * there are no if statements for them.
                 */
                if (c == '+' || c == '-') {
                    
                    /**
                     * Pop operators until a '(' has been found.
                     */
                    while (!s.isEmpty() && s.peek() != '(') {
                        
                        postfix.append(s.pop());
                    }
                }
                
                if (c == '*' || c == '/') {

                    /**
                     * Pop only the first operator of equal precedence.
                     */
                    if (s.peek() == '*' || s.peek() == '/') {

                        postfix.append(s.pop());
                    }
                }

                /**
                 * Push the current token after the necessary operations have
                 * been performed on the stack.
                 */
                s.push(c);
            }
        }

        /**
         * For infix expressions without a explicit termination character.
         */
        while (!s.isEmpty()) {

            postfix.append(s.pop());
        }

        return postfix.toString();
    }
}

class InvalidExpressionException extends RuntimeException {

    public InvalidExpressionException(String message) {

        super(message);
    }
}

class ParenthesesMismatchException extends RuntimeException {

    public ParenthesesMismatchException(String message) {
        super(message);
    }
}
