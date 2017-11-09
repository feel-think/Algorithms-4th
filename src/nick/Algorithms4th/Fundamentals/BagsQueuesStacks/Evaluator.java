package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Evaluator {


    public static double evaluate(String expression) {
        // 去除表达式中多余的空格
        return valueOf(expression.replace(" ", ""));
    }

    private static double valueOf(String expression) {
        /**
         * 假定表达式为一个数字，或遵守如下形式
         * expression1 = ( expression2 operator expression3 )
         * 其中 expression2 和 expression3 的形式也符合上面的定义
         */
        try {
            // 递归的最简单的情况
            return Double.parseDouble(expression);
        } catch (NumberFormatException e) {}
        // 将表达式拆分为两个子表达式，然后使用递归求解
        // 扫描表达式字符串，寻找运算符的位置，以运算符为分界，提取出两个子表达式
        int tier = 0; // 保存当前所处的表达式层级的变量
        char operator = 0; // 保存操作符的变量
        int operatorIndex = 0; // 保存操作符位置的变量
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                tier++;
                continue;
            }
            if (c == ')') {
                tier--;
                continue;
            }
            /**
             * 当所处的表达式层级为 1 时，说明当前扫描到的字符位于最外层表达式中，
             * 若字符 c 是运算符号，就找到了划分两个子表达式的运算符
             */
            if (tier == 1 && "+-*/".indexOf(c) != -1) {
                operator = c;
                operatorIndex = i;
            }
        }
        // 提取表达式字符串，注意去掉两边的括号
        String leftExpression = expression.substring(1, operatorIndex);
        String rightExpression = expression.substring(operatorIndex + 1, expression.length() - 1);
        // 递归求值
        switch (operator) {
            case '+':
                return valueOf(leftExpression) + valueOf(rightExpression);
            case '-':
                return valueOf(leftExpression) - valueOf(rightExpression);
            case '*':
                return valueOf(leftExpression) * valueOf(rightExpression);
            case '/':
                return valueOf(leftExpression) / valueOf(rightExpression);
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String expression = "( 1 + ( ( 3 / 2 ) * ( 4 * 5 ) ) )";
        StdOut.println(evaluate(expression));
    }

}