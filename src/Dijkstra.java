import java.util.Scanner;
import java.util.LinkedList;

public class Dijkstra
{
    LinkedList<Integer> visited;
    LinkedList<Integer> queue;

    public void dijkstra(Graph g, int r)
    {
        visited = new LinkedList<>();
        queue = new LinkedList<>();

        int[] d = new int[g.n]; // array to store d of node from source
        int[] p = new int[g.n];

        // initialize all those distances at infinity
        for (int i = 0; i < g.n; i++)
        {
            d[i] = Integer.MAX_VALUE;
        }

        d[r] = 0; // set d of root from root as 0
        p[r] = -1; // parent of the source

        queue.add(r);

        while (!queue.isEmpty()) // while queue is not empty
        {
            int currentNode = queue.removeFirst(); // remove first node from queue and assign

            if (!visited.contains(currentNode)) // if this node was not visited
            {
                visited.add(currentNode); // add to visited

                for (Edge edge : g.edges(currentNode)) // for each edge this node has to
                {
                    if (!visited.contains(edge.getDestination())) // if destination node was not visited
                    {
                        if (d[currentNode] + edge.weight < d[edge.destination])
                        {
                            d[edge.destination] = d[currentNode] + edge.weight;
                            p[edge.destination] = currentNode;
                        }

                        queue.add(edge.getDestination());
                    }
                }
            }
        }

        printDijkstra(p, d, r, g.n);
    }

    public void printDijkstra(int[] parent, int[] distance, int root, int nodes){
        System.out.println("Dijkstra Algorithm: (With all paths)");
        for (int i = 0; i < nodes ; i++) {
            System.out.print(" " + root + " -> " + i + " = Distance : " + distance[i] + "  Path : ");
            printPath(parent, i);
            System.out.println();
        }
    }

    public void printPath(int[] parent, int destination){
        //if node is source then stop recursion
        if(parent[destination] == -1) {
            System.out.print("0 ");
            return;
        }
        printPath(parent, parent[destination]);
        System.out.print(destination + " ");
    }


    public static void main(String [] args)
    {
        ReadFile readFile = new ReadFile();

        Graph g = readFile.generateGraph("SparseGraph.txt");

        if (g == null) return; // if there is no graph returned from ReadFile, end program

        int nodes = g.n - 1; // total number of nodes

        // get user input for the root node
        Scanner reader = new Scanner(System.in);
        System.out.println("Number of nodes in this graph: " + nodes);
        System.out.println("Enter the root node: ");

        int r = -1; // initialize with a wrong value

        // while the input is not between 0 and the total number of nodes
        while (r < 0 || r > nodes)
        {
            r = reader.nextInt();

            if (r < 0 || r > nodes)
            {
                System.out.println("Please enter a number between 0 and " + nodes + "."); // error check
            }
        }

        // run Dijkstra algorithm
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra(g, r);
    }
}
