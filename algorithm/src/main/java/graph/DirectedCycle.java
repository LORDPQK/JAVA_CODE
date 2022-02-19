package graph;

public class DirectedCycle {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //记录图中是否由环
    private boolean hasCycle;
    //索引代表顶点，使用栈的思想，记录当前顶点有没有已经处于正在搜索的有向路径上
    private boolean[] onStack;

    //创建一个检测环对象，检测图G中是否有环
    DirectedCycle(Digraph G){
    this.marked = new boolean[G.V()];
    this.hasCycle = false;
    this.onStack = new boolean[G.V()];

    //找到图中的每一个节点，让每一个节点作为入口，都去调用一次dfs，因为图可能是非连通图
    for(int v =0;v<G.V();v++){
        //如果当前节点还没有被搜索过就调用一次dfs
        if(!marked[v]){
            dfs(G,v);
        }
    }

    }


    //基于深度优先搜索，检测图G中是否有环
    private void dfs(Digraph G ,int v){
        //把顶点v标识为已搜索
        marked[v] = true;

        //把当前顶点入栈
        onStack[v] = true;

        //进行深度搜索
        for (Integer w : G.adj(v)) {
            //如果当前顶点w没有被搜索过，就继续递归掉用dfs方法完成深度优先搜索
            if(!marked[w]){
                dfs(G,w);
            }

            //如果w在onStack栈中，就证明之前已经处于搜索的路径上了，现在又访问到了，说明存在了环
            if(onStack[w]){
                hasCycle = true;
                return;
            }

        }

        //把当前顶点出栈,所有的顶点都会递归地出栈，让其他节点调用dfs的时候拿到的还是一个新的栈
        onStack[v] = false;
    }

    //判断图中是否有环
    public boolean hasCycle(){
        return hasCycle;
    }
}
