package graph;

import linear.Queue;

//无向图的数据结构
public class Graph {

    //顶点的数量
    private int V;

    //边的数量
    private int E;
    //邻接表
    private Queue<Integer>[] adj;

    public Graph(int V){
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        this.adj =  new Queue[V];
        for (int i = 0; i <adj.length ; i++) {
            adj[i] = new Queue<Integer>();
        }
    }




    //获取图中顶点的数量
    public int V(){
        return V;
    }

    //获取图中边的数量
    public int E() {
        return E;
    }

    //往图中添加一条边v-w
    public void addEdge(int v,int w){
        adj[v].enqueue(w);
        adj[w].enqueue(v);
        //边的数量+1
        E++;
    }

    //获取和顶点v相邻的所有顶点
    public Queue<Integer> adj(int v){
        return adj[v];
    }
}
