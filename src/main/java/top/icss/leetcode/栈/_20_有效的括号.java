package top.icss.leetcode.栈;

import java.util.Stack;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author cd.wang
 * @create 2020-11-27 10:08
 * @since 1.0.0
 */
public class _20_有效的括号 {

    public static boolean isValid(String s) {
        // 栈的特性 后进先出
        Stack<Character> stack = new Stack<Character>();

        char[] chars = s.toCharArray();
        for (char c : chars){
            // 如果是左侧，先入栈;右侧则匹配
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else{
                // 遇到右侧且栈空，直接结束,匹配不上也为false
                if(stack.empty()) return false;
                // 取出左边
                char left = stack.pop();
                if(left == '(' && c != ')') return false;
                if(left == '{' && c != '}') return false;
                if(left == '[' && c != ']') return false;
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        String str = "{[]}()[]{}";
        System.out.println(isValid(str));
    }
}
