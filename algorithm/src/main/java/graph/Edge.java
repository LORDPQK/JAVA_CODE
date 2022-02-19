package graph;

public class Edge implements Comparable<Edge>{

    private int v;
    private int w;
    private double weight;

    public Edge(int v,int w,double weight){
        this.v = v;
        this.w =w;
        this.weight =weight;
    }

    public double getWeight(){
        return weight;
    }

    public int either(){
        return v;

    }

    public int other(int vertex){
        if(vertex==v){
            return w;
        }else {
            return v;
        }


    }


    @Override
    public int compareTo(Edge o) {
        int cmp;
        //如果当前边的权重值大，则让cmp =1；
        if(this.getWeight()>o.getWeight()){
            cmp = 1;
        }
        //如果当前边的权重值小，则让cmp =-1；
        if(this.getWeight()<o.getWeight()){
            cmp = -1;
        }else {
            cmp =0;
        }
        return cmp;
    }
}
