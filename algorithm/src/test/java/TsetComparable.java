import entity.Student;

public class TsetComparable {
    public static void main(String arg[]){
        Student s1 = new Student();
        s1.setUsername("张三");
        s1.setAge(20);

        Student s2 = new Student();
        s2.setUsername("李四");
        s2.setAge(18);

        Comparable max = getMax(s1,s2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable o1,Comparable o2){
        int result = o1.compareTo(o2);
        //if result > 0 o1>o2 return o1
        //if result > 0 o1<o2 return o2

        if(result>0){
            return  o1;
        }
        else return o2;
    }
}
