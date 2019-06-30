package com.yang.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前缀、中缀、后缀表达式 (逆波兰表达式)
 * 中缀表达式 => 后缀表达式 (逆波兰表达式)
 */
public class PolandNotation {

    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        // 1、先将字符串转为中缀表达式 拆分字符 存入list集合中
        List<String> suffix = toInfixExpressionList(expression);
        System.out.println(suffix);
        // 2、将中缀表达式 => 后缀表达式
        System.out.println(parseSuffixExpressionList(suffix));
    }

    // 将字符串转为中缀表达式
    private static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0;// 遍历字符串的指针，用于遍历中缀表达式字符串
        StringBuilder str;// 对多位数的拼接
        char c;// 单个字符的存放
        do {
            // 满足这个条件 表示是非数字
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                list.add(String.valueOf(c));
                i++; // 指针后移
            } else {
                // 是一个数，需要考虑多位数
                str = new StringBuilder();
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && c <= 57) {
                    str.append(c); // 拼接多位数
                    i++; // 指针后移
                }
                list.add(str.toString());
            }
        } while (i < expression.length());
        return list;
    }

    // 将中缀表达式 => 后缀表达式
    private static List<String> parseSuffixExpressionList(List<String> infixExpression) {
        // 符号栈
        Stack<String> s1 = new Stack<>();
        // 这里使用list 而不适用stack的原因：s2这个栈，在整个转换过程中，没有pop操作，最后还需要逆序输出转换结果，用stack比较麻烦，所以使用list
        List<String> s2 = new ArrayList<>();
        for (String item : infixExpression) {
            if (item.matches("\\d+")) {
                // 如果是一个数字，则直接加入s2
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是一个左括号，压入s1
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是一个右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到右括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// 将右括号丢弃
            } else {
                // 到这里就是运算符了
                // 当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，然后重复，直到不满足条件为止，最后将item压入s1
                while (s1.size() > 0 && Operation.getPriority(item) <= Operation.getPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        // 最后 将s1中剩余的运算符 依次弹出 压入s2
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        // 因为list有序，所以现在直接按顺序输出 就是 逆波兰表达式了 如果用栈 还需要逆序输出
        return s2;
    }

    private static class Operation {

        public static int getPriority(String op) {
            switch (op) {
                case "+":
                    return 1;
                case "-":
                    return 1;
                case "*":
                    return 2;
                case "/":
                    return 2;
                default:
                    System.out.println("不支持的运算符");
                    return -1;
            }
        }
    }
}
