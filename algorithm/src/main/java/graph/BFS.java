package graph;

import linear.Queue;

public class BFS {
    //索引代表节点，值表示当前节点是否已经被搜索
    private boolean[] marked;
    //记录有多少个顶点和s顶点相通
    private int count;
    //用来存储待搜索邻接表的节点，完成BFS的辅助队列
    private Queue<Integer> waitSearch;


    public BFS(Graph G,int s){
        this.marked = new boolean[G.V()];
        this.count = 0;
        this.waitSearch = new Queue<Integer>();

        bfs(G,s);
    }


    //使用广度优先搜索找出G图中v顶点的所有相邻节点
    private void bfs(Graph G ,int s){
        //把当前节点s表示为以搜索
        marked[s] = true;
        //让顶点s进入队列，待搜索
        waitSearch.enqueue(s);
        //通过循环，如果队列不为空，则从队列中弹出一个待搜索的顶点进行搜索
        while(!waitSearch.isEmpty()){
            //弹出一个待搜索的节点
            Integer wait = waitSearch.dequeue();

            //遍历wait的邻接表
            for (Integer w : G.adj(wait)) {
                if(!marked[w]){
                    //标记为已访问
                    marked[w] = true;
                    count++;
                    //入队列
                    waitSearch.enqueue(w);
                }
            }


        }
    }

    //判断w顶点与s顶点是否相通
    public boolean marked(int w){
        return marked[w];
    }

    //获取与s顶点相通的所有顶点的总数
    public int count(){
        return count;
    }
}
