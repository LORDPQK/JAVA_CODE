package sort;

public class Quick {
    private static boolean less(Comparable v , Comparable w){
        return  v.compareTo(w)<0;
    }

    private static void exchange(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a){
        int low =0;
        int high = a.length-1;
        sort(a,low,high);
    }

    public static void sort(Comparable[] a,int low, int high){
        //安全性校验
        if(high<=low){
            return;
        }

        //需要对数组中low索引到high索引处的元素进行分组（左子组和右子组）；
        int partition = partition(a,low,high);//返回的是分组的分界值所在的索引，分界值位置变换之后的索引

        //让左子组有序
        sort(a,low,partition-1);

        //让右子组有序
        sort(a,partition+1,high);

    }

    //对数组a中，从索引low到索引high之间的元素进行分组，并返回分组界限对应的索引
     public static int partition(Comparable[] a, int low, int high){
        //确定分界值
         Comparable key = a[low];
         //定义两个指针，分别指向待切分元素的最小索引处和最大索引处的下一个位置
         int left =low;
         int right = high+1;
         //切分
         while(true){
             //从右到左扫描，移动right指针，找到一个比分界值小的元素，停止
             while(less(key,a[--right])){
                 if(right==low){
                     break;
                 }
             }
             //从左往右扫描，移动left指针，找到一个比分界值大的元素，停止
             while(less(a[++left],key)){
                 if(left==high){
                     break;
                 }
             }
             //判断left>=right，如果是，则证明元素扫描完毕,结束循环，如果不是,则交换元素

             if(left>=right){
                 break;
             }else {
                 exchange(a,left,right);
             }

         }
         //交换分界值
         exchange(a,low,right);
        return right;
    }




}
