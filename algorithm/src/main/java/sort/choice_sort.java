package sort;

/*选择排序：选择待排元素中最小的元素，让它与待排数组的第一个元素进行交换*/
public class choice_sort {
    //排序 i<= a.length-2 因为剩余两个元素才需要选择，剩余一个元素不需要做任何操作，
    // 所以选择操作最后在i=a.length-2即倒数第二个数与倒数第一个数进行比较
    public static void sort(Comparable[] a){

        for(int i=0;i<=a.length-2;i++){
        //定义一个变量，记录最小元素所在索引，默认为剩余待排数组的第一个元素
            int minIndex = i;

            for(int j=i+1;j<a.length;j++){
                if(greater(a[minIndex],a[j])){
                    minIndex = j;
                }
            }

            //交换MinIndex元素的值和i元素的值
                exchange(a,i,minIndex);

        }

    }
    //比较
    private static boolean greater(Comparable o1,Comparable o2){
        return o1.compareTo(o2)>0;
    }
    //交换
    private static  void exchange(Comparable[]a ,int i, int j){
        Comparable temp;
        temp = a[i];
        a[i] =a[j];
        a[j] = temp;
    }
}
