import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int numChallenges = Integer.parseInt(line[0]);
        int numDecisions = Integer.parseInt(line[1]);
        Graph graph = new Graph(numChallenges, numDecisions);
        for (int i = 0; i < numDecisions; i++) {
            String[] lineDecision = reader.readLine().split(" ");
            int c1 = Integer.parseInt(lineDecision[0]); //finished challenge
            String p = lineDecision[1];                 //Pays or Gets
            int v = Integer.parseInt(lineDecision[2]);  //amount of energy involved
            int c2 = Integer.parseInt(lineDecision[3]); //new challenge
            graph.addNode(i, new Pair(c1, c2, p.equalsIgnoreCase("Pays") ? v : -v));

        }
        String[] lastLine = reader.readLine().split(" ");
        int initialChallenge = Integer.parseInt(lastLine[0]);
        int endingChallenge = Integer.parseInt(lastLine[1]);
        int initialEnergy = Integer.parseInt(lastLine[2]);

        long returnedEnergy = new BellmanFord(graph).calculate(initialChallenge, endingChallenge);

        if (returnedEnergy <= 0) {                    //returnEnergy greater then initialEnergy
            System.out.println("Full of energy");
        } else if (returnedEnergy < initialEnergy) {  //warrior loses energy in the path to the wizard
            System.out.println(initialEnergy - returnedEnergy);
        } else {                                      //warrior does not have energy to reach the wizard
            System.out.println(0);
        }

    }
}
