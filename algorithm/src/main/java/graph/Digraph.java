package graph;


import linear.Queue;

public class Digraph {

    //记录顶点数量
    private int V;
    //记录边的数量
    private int E;
    //邻接表
    private Queue<Integer>[] adj;

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = new Queue[V];
        for (int i = 0; i <adj.length ; i++) {
                adj[i] = new Queue<Integer>();
        }
    }

    //获取图中顶点的数量
    public int V(){
        return V;
    }

    //获取图中边的数量
    public int E(){
        return E;
    }

    //向有向图中添加一条边v->w
    public void addEdge(int v, int w){
        adj[v].enqueue(w);
        E++;
    }

    //获取由v指出的边所连接的所有顶点
    public Queue<Integer> adj(int v){
        return adj[v];
    }

    //获取该图的反向图
    private Digraph reverse(){
        //创建有向图对象
        Digraph r = new Digraph(V);

        for (int v = 0; v <V; v++) {
            //获取由该顶点v指出的所有边
            for (Integer w : adj[v]) {
                r.addEdge(w,v);
            }
        }


        return r;
    }
}
