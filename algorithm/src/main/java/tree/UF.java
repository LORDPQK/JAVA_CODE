package tree;

public class UF {
    //记录节点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private  int count;

    //初始化并差集，以整数标识（0，N-1）个节点
    UF(int N){
        //初始化分组的数量
        this.count = N;
        //初始化eleAndGroup数组
        this.eleAndGroup = new int[N];

        //初始化eleAndGroup中的元素及其所在的组的标识符
        //让eleAndGroup数组的索引作为并查集的每个节点的元素，并且让每个索引处的值（该元素所在组的标识符）就是该索引
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i]=i;
        }

    }

    //获取并查集中的数据有多少个分组
    public int count(){
        return count;
    }

    //元素p所在分组的标识符
    public int find (int p){
        return eleAndGroup[p];
    }

    //判断并查集中元素p和元素q是否在同一个分组中
    public boolean connected(int p ,int q){
        return find(p)==find(q);
    }



    //把元素p所在的分组与元素q所在的分组合并
    public void union(int p,int q){
        if(connected(p,q)){
            return;
        }

        //找到p,q所在分组的标识符
        int pgroup = find(p);
        int qqroup = find(q);

        //合并组，让p所在组的所有元素的组标识符变为q所在组的组标识符
        for (int i = 0; i < eleAndGroup.length; i++) {
            if(eleAndGroup[i]==pgroup){
                eleAndGroup[i] =qqroup;
            }
        }

        this.count--;
    }
}
