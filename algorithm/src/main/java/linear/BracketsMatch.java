package linear;

//括号匹配问题
public class BracketsMatch {
    public static void main(String[] args) {
        String str = "(上海(长安)())";
        boolean match = isMatch(str);
        System.out.println(str+"中的括号是否匹配:"+match);
    }

    public static boolean isMatch (String s){
        //创建栈对象，用于存储左括号
        Stack<String> stack = new Stack<String>();
        //从左往右遍历字符串
        for(int i =0; i<s.length() ;i++){

            // char c = s.charAt(i);
            String CurrChar = s.charAt(i)+"";

            //判断当前字符是否为左括号，如果是，则把字符放入栈
            if (CurrChar.equals("(")){
                stack.push(CurrChar);
            }

            //继续判断当前字符是否是右括号，如果是，则从栈中弹出一个左括号，并判断弹出的结果为null，如果为null，则证明没有匹配的左括号
            if(CurrChar.equals(")") ){
                String pop = stack.pop();
                if (pop==null){
                    return false;
                }
            }

        }

        //判断栈中是否还有剩余的左括号，如果有，则证明括号不匹配
        if(stack.size()==0){
            return true;
        }else return true;

    }
}
