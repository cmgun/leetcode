package com.cmgun.leetcode.strings;

import com.cmgun.algorithms.stack.Stack;

import java.util.HashMap;

/**
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 *
 */
public class ValidParentheses {

    private final static char STACK_1_START = '(';

    private final static char STACK_1_END = ')';

    private final static char STACK_2_START = '[';

    private final static char STACK_2_END = ']';

    private final static char STACK_3_START = '{';

    private final static char STACK_3_END = '}';

    private final static HashMap<Character, Character> charMaps = new HashMap<>();

    static {
        charMaps.put('(', ')');
        charMaps.put('{', '}');
        charMaps.put('[', ']');
    }

    /**
     * Runtime: 1 ms, faster than 98.83% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Valid Parentheses.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // true
        System.out.println(isValid("()"));
        // true
        System.out.println(isValid("()[]{}"));
        // false
        System.out.println(isValid("(]"));
        // false
        System.out.println(isValid("([)]"));
        // true
        System.out.println(isValid("{[]}"));
        // false
        System.out.println(isValid("(("));
    }

    /**
     * valid with stack
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        // empty string
        if (s == null || s.length() == 0) {
            return true;
        }
        // odd size
        if (s.length() % 2 != 0) {
            return false;
        }
        // store the open delimiter
        Stack<Character> stack = new Stack<>();
        // last char
        char[] chars = s.toCharArray();
        for (char current : chars) {
            // contains key
            if (charMaps.containsKey(current)) {
                // open delimiter
                stack.push(current);
                continue;
            }
            // match last char
            if (stack.isEmpty()) {
                return false;
            }
            Character endChar = charMaps.get(stack.pop());
            if (endChar == null || !endChar.equals(current)) {
                // illegal input
                return false;
            }
        }
        return stack.isEmpty();
    }


/*
    public static boolean isValid(String s) {
        // empty string
        if (s == null || s.length() == 0) {
            return true;
        }
        // odd size
        if (s.length() % 2 != 0) {
            return false;
        }

        // stack match
        // stack 1: (
        Stack<Integer> stack1 = new Stack<>();
        // stack 2: [
        Stack<Integer> stack2 = new Stack<>();
        // stack 3: {
        Stack<Integer> stack3 = new Stack<>();
        char[] chars = s.toCharArray();
        for (char i : chars) {
            switch (i) {
                case STACK_1_START: {
                    stack1.push(0);
                    break;
                }
                case STACK_1_END:{
                    if (stack1.isEmpty()) {
                        // not match
                        return false;
                    }
                    stack1.pop();
                    break;
                }
                case STACK_2_START: {
                    stack2.push(0);
                    break;
                }
                case STACK_2_END:{
                    if (stack2.isEmpty()) {
                        // not match
                        return false;
                    }
                    stack2.pop();
                    break;
                }
                case STACK_3_START: {
                    stack3.push(0);
                    break;
                }
                case STACK_3_END:{
                    if (stack3.isEmpty()) {
                        // not match
                        return false;
                    }
                    stack3.pop();
                    break;
                }
                default:
                    return false;
            }
        }
        return true;
    }
*/
}
