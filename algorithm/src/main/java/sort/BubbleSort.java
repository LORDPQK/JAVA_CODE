package sort;
public class BubbleSort {

    /*
    *最坏情况 逆序
    * O(N^2)
    * */


    //排序
    public static void sort(Comparable[] a){

        for(int i = a.length-1;i>0;i--){
            //比较索引处j J+的值 j<i 因为J=i时j+1超出数组
            for(int j = 0;j<i;j++){
                if(greater(a[j],a[j+1])){
                    exchange(a,j,j+1);
                }
            }
        }



    }
    //交换
    public static void exchange(Comparable[] a,int i,int j){
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    //比较
    public static boolean greater(Comparable o1,Comparable o2){
        return o1.compareTo(o2)>0;
    }
}
