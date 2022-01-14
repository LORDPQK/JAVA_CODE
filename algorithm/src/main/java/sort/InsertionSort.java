package sort;

/*
* 1.把所有元素分为两组，已经排序的和未排序的,默认第一个元素已经排序，待排序的元素从i=1开始
* 2.找到未排序的组中的第一个元素，向已经排序的组中进行插入
* 3.倒叙遍历已经插入的元素，依次和待插入的元素进行比较，直到找到一个元素小于等于待插入元素，那么就把待插入元素放到这个元素的后一个位置，其他的元素向后移动一位
* 实质上就是在一个数组中[0 1 2 3 4 | 37] 排序 下标j=i值为3和j-1值为4比较，4>3，3和4位置交换，[0 1 2 3 3 4|7],3和3比较,= 则不做操作
*
* */
public class InsertionSort {

    //比较索引j处的值和索引j-1处的值，如果索引j-1处的值比索引j处的值大，则交换数据，如果不大则说明找到了合适的位置，跳出循环。
    //排序
    public static void sort(Comparable[] a){
        for(int i =1;i< a.length;i++){
            for(int j=i;j>0;j--){
                if(greater(a[j-1],a[j])){
                    exchange(a,j,j-1);
                }else {
                    break;
                }
            }
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
