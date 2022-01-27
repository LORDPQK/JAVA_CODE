package heap;

public class Heap <T extends Comparable<T>>{
    //储存堆中的元素
    private T[] items;

    //记录堆中元素的个数
    private  int N ;

    public Heap(int capacity){
        this.items = (T[]) new Object[capacity];
        this.N =0;
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i,int j){
        return items[i].compareTo(items[j])<0;
    }

    //交换索引i和索引j处的值
    private void exch(int i,int j){
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //往堆中插入一个元素
    public void insert(T t){
        items[++N] = t;
        swim(N);
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    //上浮算法就是不断比较新节点a[k],和父节点a[k/2]的大小，然后根据结果完成数据元素的交换，就可以完成堆的有序调整
    //如果父节点比子节点小，则交换位置
    private void swim(int k){
        while(k>1){
            //比较当前节点和其父节点
            if(less(k/2,k)){
                exch(k/2,k);
            }

            k = k/2;
        }
    }



    //删除堆中最大的元素，并返回这个最大的元素
    public T delMax(){
        T max = items[1];

        //交换索引i处的元素和最大索引处的元素，让完全二叉树中最右侧的元素变为临时根节点
        exch(1,N);
        //删除最大元素
        items[N]= null;
        N--;

        //通过下沉调整堆，让堆重新有序
        sink(1);
        return max;

    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k){
        while(2*k<=N){
            int max;
            //获取当前节点中子节点中较大节点的值
            if(2*k+1<=N){
                if(less(2*k,2*k+1)){
                    max  = 2*k+1;
                }else{
                    max = 2*k;
                }
            }else{
                max = 2*k;
            }

            if(less(max,k)){
                exch(max,k);
            }else{
                break;
            }

            k = max;

        }
    }

}
