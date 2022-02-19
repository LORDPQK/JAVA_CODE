package tree;

public class UF_tree {
    //记录节点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private  int count;

    //初始化并差集，以整数标识（0，N-1）个节点
    UF_tree(int N){
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
        while(true){

            //判断当前元素的数组下标是否和数组对应的值相等
            if( p == eleAndGroup[p]){
                return p;
            }

            p = eleAndGroup[p];

        }

    }

    //判断并查集中元素p和元素q是否在同一个分组中
    public boolean connected(int p ,int q){
        return find(p)==find(q);
    }



    //把元素p所在的分组与元素q所在的分组合并
    public void union(int p,int q) {
        //找到p元素和q元素所在组的对应树的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        //如果p和q已经在同一个分组，则不许要合并
        if(pRoot == qRoot){
            return;
        }

        //让p所在的树的根节点的父节点为q所在树的根节点即可
        eleAndGroup[pRoot]  = qRoot;

        //让组的数量-1
        count--;
    }
}
