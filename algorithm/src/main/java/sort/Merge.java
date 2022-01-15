package sort;

public class Merge {
    //归并需要的辅助数组
    private static Comparable[] assist;

    private static boolean less(Comparable v , Comparable w){
        return  v.compareTo(w)<0;
    }

    private static void exchange(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //对数组a中的元素进行排序
    public static void  sort(Comparable[] a){
        //1.初始化数组assist;
        assist = new Comparable[a.length];
        //2.定义一个变量low,和变量high,分别记录数组中最小的索引和最大的索引
        int low =0;
        int high = a.length-1;
        sort(a,low,high);
    }

    //对数组中从low到high的元素进行排序
    public static void sort(Comparable[] a ,int low,int high){
        //安全性校验，如果只有一个数据则不能分组，最小的一组包含两个数据
        if(low>=high){
            return;
        }

        //对low到high之间的数据分为两组
        int mid = low+(high-low)/2;

        //分别对每一组数据进行排序
        sort(a,low,mid);
        sort(a,mid+1,high);

        //再把两个组中的数据进行归并
        merge(a,low,mid,high);
    }

    //对数组中从low到mid为一组，mid+1到high为一组，对这两组数据进行归并
    private static void merge(Comparable[] a,int low,int mid ,int high){
        //定义三个指针，分别用于指向两个数组的首元素，和assist数组的首元素
        int i =low;
        int p1=low;
        int p2 = mid+1;

        //遍历，移动p1，p2，比较对应索引处的值，找出最小的那个，放到辅助数组的对应索引处
        while(p1<=mid && p2<=high){
            if(less(a[p1],a[p2])){
                assist[i++]= a[p1++];
            }else{
                assist[i++]= a[p2++];
            }
        }
        //遍历，如果p1的指针没有走完，那么就顺序移动p1指针，把对应元素放到辅助数组的对应索引处
        while (p1<=mid){
            assist[i++]= a[p1++];
        }
        //遍历，如果p2的指针没有走完，那么就顺序移动p1指针，把对应元素放到辅助数组的对应索引处
        while (p2<=high){
            assist[i++]= a[p2++];
        }
        //把辅助数组中的元素覆盖原数组

        for(int j =low;j<high;j++){
            a[j]=assist[j];
        }
    }

}
