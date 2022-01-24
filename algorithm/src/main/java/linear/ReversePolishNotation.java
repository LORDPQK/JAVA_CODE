package linear;

//逆波兰式计算
public class ReversePolishNotation {
    public static void main(String[] args) {
        //中缀表达式3*(17-15)+18/6的逆波兰式如下
        String[] notation = {"3","17","15","-","*","18","6","/","+"};
        int result = caculate(notation);
        System.out.println("逆波兰式的结果为："+result);
    }

    public static int caculate(String[] notation){
        //定义一个栈，用来存储操作数
        Stack<Integer> stack = new Stack<Integer>();
        //从左往右遍历逆波兰式，得到每一个元素
        for(int i =0; i<notation.length;i++){
            String curr = notation[i];
            Integer o1;
            Integer o2;
            Integer result;
            //判断当前元素是字符还是操作符
            switch(curr){
                case "+":
                    //+ 从栈中弹出两个操作数，完成运算，运算的结果再压入栈
                     o1 = stack.pop();
                     o2 = stack.pop();
                     result = o2+o1;
                    stack.push(result);
                    break;
                case "-":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2-o1;
                    stack.push(result);
                    break;
                case "*":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2*o1;
                    stack.push(result);
                    break;
                case "/":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2/o1;
                    stack.push(result);
                    break;
                default:
                    //是数字
                    stack.push(Integer.parseInt(curr));
                    break;
            }

        }
        int fresult = stack.pop();
        return fresult;
    }
}
