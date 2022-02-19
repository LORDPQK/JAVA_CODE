package graph;

public class DFS {

    //数组的索引为顶点的编号，值表示当前顶点是否已经被搜索
    private boolean[] marked;

    //记录有多少个节点与s顶点相通
    private int count;

    //构造深度优先搜索对象，使用深度优先搜索找出G图中s顶点的所有相邻顶点
    public DFS(Graph G,int s){
        //初始化marked数组
        this.marked = new boolean[G.V()];
        //初始化跟顶点s相通的顶点数量
        this.count = 0;
        
        dfs(G,s);
    }

    //使用DFS找出G图中s顶点相通的所有顶点
    private void dfs(Graph G,int s){
        //把s顶点标识为已搜索
        marked[s] = true;

        //通过adj方法获得和顶点s相邻的所有顶点,即他的所有子顶点
        for (Integer w : G.adj(s)) {
            //判断当前w顶点有没有被搜索过，如果没有没搜索过递归调用dfs方法,去遍历这个w顶点的子节点，直到子节点都遍历完成，才返回来遍历节点s的兄弟节点
            if(!marked[w]){
                dfs(G,w);
            }
        }

        count++;
    }



    //判断w顶点与s顶点是否相通
    public boolean marked(int w){
        //true代表相通，false代表不相通
        return marked[w];
    }

    //获取与顶点s相通的所有顶点的总数
    private int count(){
        return count;
    }

    //
}
