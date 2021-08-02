package Service1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Comsumer {
    public static void main(String[] args) {
        /*
        匿名内部类
        Consumer<Double> cousumer =new Consumer<Double>(){
        @Override
        public void accept(Double t){
        System.out.print("聚餐消费"+t);
        }
        }
         */

        //Consumer<Double> comsumer=t->System.out.println("聚餐消费"+t);

       // happy(comsumer,1000);
       // happy(t->System.out.println("聚餐消费"+t),1000);
       //String result=handlerString(s -> s.toUpperCase(),"hello") ;
       //System.out.println(result);

        List<String> list =new ArrayList<>();
        list.add("zhangsan");
        list.add("wangwu");
        list.add("pak");
        List<String>result=filterNames(s->s.startsWith("p"),list);
        System.out.println(result.toString());
    }

    //Consumer 消费型接口
    public static void happy(Consumer<Double> consumer,double money){
        consumer.accept(money);
    }

     //Function函数型接口
    //对str进行处理
    public static String handlerString(Function<String,String> function,String str){
        return function.apply(str);
    }

    //Predicate 断言型接口
    public static List<String> filterNames(Predicate<String> predicate,List <String> list){
        //过滤list中的数据
        List<String> resultList=new ArrayList<String>();
        for (String string:list) {
            if(predicate.test(string)){
                resultList.add(string);
            }
        }
        return resultList;
    }
}
