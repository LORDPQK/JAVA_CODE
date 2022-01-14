package sort;

public class Shell {
    public static void sort(Comparable[] a){
        //1.根据数据a的长度，确定增长量h
        int h =1;
        while (h<a.length/2){
            h=2*h+1;
        }

        //2.希尔排序
        while(h>0){
            //2.1确定待插入的元素
            for(int i=h;i<a.length;i++){
                //2.2插入数据
                for(int j=i;j>=h;j-=h){
                    if(greater(a[j-h],a[j])){
                        exchange(a,j-h,j);
                    }else{break;}
                }
            }

            //减少h的值
            h = h/2;

        }
    }

    public static boolean greater(Comparable o1,Comparable o2){return o1.compareTo(o2)>0;}

    public static void exchange(Comparable[] a,int i,int j){
        Comparable temp;
        temp = a[i];
        a[i]=a[j];
        a[j]= temp;
    }
}
