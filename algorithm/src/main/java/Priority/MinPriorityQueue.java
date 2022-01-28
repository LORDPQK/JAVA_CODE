package Priority;

public class MinPriorityQueue <T extends Comparable<T>>{

    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

   MinPriorityQueue(int capacity){
        this.items = (T[]) new Comparable[capacity+1];
        this.N = 0;
    }

    private boolean less(int i ,int j){
        return items[i].compareTo(items[j])<0;
    }

    private void exch(int i,int j){
        T temp = items[i];
        items[i] = items[j];
        items[j]  = temp;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }


    //删除队列中的最大元素，并返回这个最大元素
    public T delMax(){
        T max = items[1];
        exch(1,N);
        N--;
        sink(1);
        return max;
    }

    //往队列中插入一个元素
    public void insert(T t){
        items[++N] = t;
        swim(N);
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k){
        while(k>1){
            if(less(k,k/2)){
                exch(k,k/2);
            }

            k=k/2;
        }

    }

    //下沉算法
    private void sink(int k){

        while(2*k<=N){
            int min;
            if(2*k+1<=N){
                if(less(2*k,2*k+1)){
                    min = 2*k;
                }else{
                    min = 2*k+1;
                }
            }else{
                min = 2*k;
            }

          if(less(k,min)){
              break;
          }
          exch(k,min);
            k=min;

        }
    }

}
