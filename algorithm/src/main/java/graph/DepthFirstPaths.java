package graph;

import java.util.Stack;

public class DepthFirstPaths {

    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;

    //起点
    private int s;

    //索引代表顶点，值代表从起点s到当前节点的路径的 上一个顶点
    private  int[] edgeTo;


    public DepthFirstPaths(Graph G, int s){
        //初始化marked数组
        this.marked = new boolean[G.V()];
        //初始化起点
        this.s =s;
        //初始化edgeTo数组
        this.edgeTo = new int[G.V()];

        dfs(G,s);
    }
    //使用深度优先搜索对象，使用深度优先搜素找出G图中起点为s的所有路径
    private void dfs(Graph G,int s){
        marked[s] = true;
        for (Integer w : G.adj(s)) {
            if(!marked[w]){
                edgeTo[w] = s;//到达顶点w的上一个节点是s
                dfs(G,w);
            }
        }
    }

    //判断v顶点与s顶点是否存在路径
    public boolean hasPathTo(int v){
        return marked[v];
    }

    //找出从起点s到顶点v的路径
    public Stack<Integer> pathTo(int V){
        if(!hasPathTo(V)){
            return null;
        }

        //创建栈对象，保存路径中的所有顶点
        Stack<Integer> path = new Stack<>();


        //通过循环，从顶点v开始，一直往前找，直到找到顶点为止
        for(int i = V;i!=s;i=edgeTo[i]){
            path.push(i);
        }

        return path;
    }
}
