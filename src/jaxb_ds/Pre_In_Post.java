package JAXB_DS;

public class Pre_In_Post {

    private static Stack<Character> stack;
    private static Stack<Integer> valueStack;
    private static String postfix;

    // Convert prefix notation expression to Infix notation 
    static String convertPrefixToInfix(String Prefix) {
        Stack<String> stack = new Stack<>();

        // Length of expression 
        int l = Prefix.length();

        // Reading from right to left 
        for (int i = l - 1; i >= 0; i--) {
            char c = Prefix.charAt(i);
            if (isOperator(c)) {
                String op1 = stack.Pop();
                String op2 = stack.Pop();

                // Concat the operands and operator 
                stack.Push("(" + op1 + c + op2 + ")");
            } else {

                // To make character to string
                stack.Push(c + "");
            }
        }
        return stack.Peek();
    }

    // function to convert infix notation expression to postfix notation
    static String convertInfixToPostfix(String infix) {
        stack = new Stack<>();
        postfix = "";

        //loop to iterates fo every character in the string
        for (int index = 0; index < infix.length(); index++) {

            //saves the character that have the turn in variable to use it later
            char nextCharacter = infix.charAt(index);

            //while it's not space
            if (nextCharacter != ' ') {

                //see if the character is plus + or time * or what (like if else if)
                switch (nextCharacter) {

                    //if the charc is power which it have the highest pirioty
                    case '^':
                        //add it to the stack
                        stack.Push(nextCharacter);
                        break;
                    case '*':
                    case '+':
                    case '-':
                    case '/':
                        //if the the piriorty of the operator is greater which in the stack pop the stack out (remove the operator in the stack)
                        while (!stack.isEmpty() && percedence(nextCharacter) >= stack.Peek()) {
                            postfix += "" + stack.Pop();
                        }
                        //add the operator to the stack
                        stack.Push(nextCharacter);
                        break;
                    case '(':
                        stack.Push(nextCharacter);
                    case ')':

                        //if he founds the closing brackets pop the stack until the opening brackets of the closing bracket
                        while (!stack.isEmpty() && stack.Peek() != '(') {
                            postfix += "" + stack.Pop();
                        }
                        stack.Pop();

                        break;
                    default:
                        if (isAlphaOrNum(nextCharacter)) {
                            postfix += "" + nextCharacter;
                        }
                        break;
                }
            }
        }

        //free the stack out
        while (!stack.isEmpty()) {
            if (stack.Peek() != '(') {
                postfix += "" + stack.Pop();
            } else {
                stack.Pop();
            }
        }
        return postfix;
    }

    //function to claculate the postfix expression using stack
    static int evaluatePostfix(String postfix) {

        valueStack = new Stack<>();

        //loop to iterates fo every character in the string
        for (int index = 0; index < postfix.length(); index++) {
            char nextCharacter = postfix.charAt(index);

            // if the char in number we will add it to tha stack
            if (isNum(nextCharacter)) {

                valueStack.Push(Integer.valueOf("" + nextCharacter));

            } else {// if it's operator we pop (remove) 2 numbers from the stack and calculate it then push it(add) to the stack

                int operandTwo;
                int operandOne;
                int result;

                operandTwo = valueStack.Pop();
                operandOne = valueStack.Pop();
                switch (nextCharacter) {
                    case '^':
                        result = (int) Math.pow(operandOne, operandTwo);
                        break;

                    case '*':
                        result = operandOne * operandTwo;
                        break;

                    case '/':
                        result = operandOne / operandTwo;
                        break;

                    case '+':
                        result = operandOne + operandTwo;
                        break;

                    case '-':
                        result = operandOne - operandTwo;
                        break;

                    default:
                        if (isAlpha(nextCharacter)) {
                            postfix += "" + nextCharacter;
                        }
                        continue;
                }
                valueStack.Push(result);
            }
        }

        return valueStack.Peek();
    }

    //function to convert from prefix notation to postix notation directily
    static String convertPrefixToPostfix(String Prefix) {
        return convertInfixToPostfix(convertPrefixToInfix(Prefix));
    }

    // Function to check if character
    // is operator or not     
    private static boolean isOperator(char x) {
        switch (x) {
            case '+': 
            case '-': 
            case '*': 
            case '/': 
            case '^': 
            case '%': 
            return true;
            
        }
        return false;
    }

    //function to check if it alphabet
    private static boolean isAlpha(char x) {
        return x >= 'A' && x <= 'Z' || x >= 'a' && x <= 'z';
    }

    //function to check if it number
    private static boolean isNum(char x) {
        return x <= '9' && x >= '0';
    }

    //function to check if it alphabet or number
    private static boolean isAlphaOrNum(char x) {
        return x >= 'A' && x <= 'Z' || x >= 'a' && x <= 'z' || x <= '9' && x >= '0';
    }

    //function to define the piriority of the operators
    private static int percedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
                return 2;
            case '/':
                return 2;
            case '+':
                return 1;
            case '-':
                return 1;
            case '(':
                return 0;
            case ')':
                return 0;
            default:
                return -1;
        }
    }

}