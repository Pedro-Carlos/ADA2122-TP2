
public class Graph {
    int numEdge;
    int numVertices;
    Pair[] graph;

    Graph(int numVertices, int numEdge) {
        this.numVertices = numVertices;
        this.numEdge = numEdge;
        this.graph = new Pair[numEdge];

    }
    public void addNode(int connectionNum, Pair pair){
        graph[connectionNum] = pair;
    }

    public Pair[] getGraph() {
        return graph;
    }
}
