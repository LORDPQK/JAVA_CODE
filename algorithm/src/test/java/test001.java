import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class test001 {

    public boolean isValid(String s) {



        //建立一个map来保存左右括号的键值对
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        //建立一个栈用于保存左括号
        Deque<Character> stack = new LinkedList<Character>();

        //遍历字符串s
        for(int i = 0;i<s.length();i++){
            char ch = s.charAt(i);

            //如果该字符在map集合中有相同的key，说明是右括号
            if(map.containsKey(ch)){
                //如果栈不为空，且该字符在map中对应的键和stack顶部的字符相同，则元素出栈
                if(stack.isEmpty() || stack.peek() == map.get(ch)){
                    return false;
                }
                stack.pop();
            }else{
                //如果没有相同的key，说明当前字符是左括号，将其入栈
                stack.push(ch);
            }
        }

        //如果栈为空，匹配成功
        return stack.isEmpty();
    }



}
