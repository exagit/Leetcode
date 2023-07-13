package UberCards.Stacks;

import java.util.Stack;
import org.junit.Test;

public class BasicCalculator {
    @Test
    public void basicAddition() {
        Calculator c = new Calculator();
        System.out.println(c.calculate("1+ 4+5+2"));
    }

    @Test
    public void unaryAddition() {
        Calculator c = new Calculator();
        System.out.println(c.calculate("1-( -2)"));
    }

    @Test
    public void basicNestedAddition() {
        Calculator c = new Calculator();
        System.out.println(c.calculate("(1+(4+5+2))"));
    }

    @Test
    public void basicNestedAdditionSubtraction() {
        Calculator c = new Calculator();
        System.out.println(c.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}


class Calculator {
    public int calculate(String s) {
        BasicCalculatorImpl cal = new BasicCalculatorImpl(s);
        int result = cal.calculate();
        return result;
    }

    class BasicCalculatorImpl {
        String s;
        Stack<String> operandStack;
        Stack<Character> operatorStack;

        BasicCalculatorImpl(String s) {
            this.s = s;
            this.operandStack = new Stack<>();
            this.operatorStack = new Stack<>();
        }

        int calculate() {
            this.parse();
            this.startEvaluation();
            return Integer.parseInt(this.operandStack.peek());
        }

        private void parse() {
            String integerToken = null;
            for (char c : this.s.toCharArray()) {
                if (c == ' ')
                    continue;
                switch (c) {
                    case '(':
                        this.operandStack.push("(");
                        integerToken = null;
                        break;
                    case '+':
                        this.operatorStack.push('+');
                        if (!integerToken.equals(""))
                            this.operandStack.add(integerToken);
                        integerToken = "";
                        break;
                    case '-':
                        this.operatorStack.push('-');
                        if (integerToken == null) {
                            this.operandStack.add("0");
                        } else if (!integerToken.equals(""))
                            this.operandStack.add(integerToken);
                        integerToken = "";
                        break;
                    case ')':
                        if (!integerToken.equals(""))
                            this.operandStack.add(integerToken);
                        integerToken = "";
                        this.startEvaluation();
                        break;
                    default:
                        if (integerToken == null) {
                            integerToken = c + "";
                        } else {
                            integerToken += c + "";
                        }
                }
            }
            if (!integerToken.equals("")) {
                this.operandStack.add(integerToken);
            }
        }

        private void startEvaluation() {
            int positiveSum = 0, negativeSum = 0;
            while (!this.operandStack.isEmpty()) {
                int operand = Integer.parseInt(this.operandStack.pop());
                if (this.operandStack.isEmpty()) {
                    positiveSum += operand;
                    break;
                }
                if (this.operandStack.peek().equals("(")) {
                    this.operandStack.pop();
                    positiveSum += operand;
                    break;
                }
                Character operator = operatorStack.pop();
                if (operator == '+') {
                    positiveSum += operand;
                } else {
                    negativeSum += operand;
                }
            }
            this.operandStack.push((positiveSum - negativeSum) + "");
            System.out.println("After Evaluation: " + this.operandStack.toString());
        }
    }
}
