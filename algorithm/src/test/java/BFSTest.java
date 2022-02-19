import graph.BFS;
import graph.Graph;

public class BFSTest {
    public static void main(String[] args) {
        Graph G = new Graph(13);
        G.addEdge(0,5);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,6);
        G.addEdge(5,3);
        G.addEdge(5,4);
        G.addEdge(3,4);
        G.addEdge(4,6);
        G.addEdge(7,8);
        G.addEdge(9,11);
        G.addEdge(9,10);
        G.addEdge(9,12);
        G.addEdge(11,12);

        BFS search = new BFS(G,5);

        int count =search.count();
        System.out.println(count);

        boolean marked1 = search.marked(11);
        System.out.println("顶点0和5是否相通："+marked1);

        boolean marked2 = search.marked(4);
        System.out.println("顶点0和7是否相通："+marked2);

    }
}
