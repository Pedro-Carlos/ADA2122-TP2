
public class BellmanFord {
    Graph graph;
    int numVertices, numEdge;
    boolean[] reachesWizard; //auxiliary array to check if it is possible to reach the wizard from a vertex

    public BellmanFord(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.numVertices;
        this.numEdge = graph.numEdge;
        this.reachesWizard = new boolean[numVertices];

    }

    public int calculate(int source, int destination) {
        int energy[] = new int[numVertices];

        for (int i = 0; i < numVertices; i++)
            energy[i] = Integer.MAX_VALUE;

        energy[source] = 0;
        reachesWizard[destination] = true; //vertex where the wizard is at

        boolean checkCycle = false;
        for (int i = 1; i < numVertices; i++) {
            checkCycle = updateEnergy(energy, graph.getGraph(), false);
            if (!checkCycle) {
                break;
            }
        }


        if (checkCycle && updateEnergy(energy, graph.getGraph(), true)) {
            return -1;
        }

        return energy[destination];

    }

    private boolean updateEnergy(int[] energy, Pair[] graph, boolean check) {
        boolean changes = false;
        for (int i = 0; i < numEdge; i++) {
            int source = graph[i].source;
            int destination = graph[i].destination;
            int weight = graph[i].weight;
            if (reachesWizard[destination]) {
                reachesWizard[source] = true;
            }
            if (energy[source] < Integer.MAX_VALUE) {
                int value = energy[source] + weight;
                if (value < energy[destination]) {
                    energy[destination] = value;
                    changes = true;
                    if (check && reachesWizard[source]) {
                        return true;
                    }
                }
            }
        }
        if (check) {
            return false;
        } else {
            return changes;
        }

    }
}