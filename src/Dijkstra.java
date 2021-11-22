import java.util.Scanner;
import java.util.LinkedList;

public class Dijkstra
{
    LinkedList<Integer> visited;
    LinkedList<Integer> queue;

    public void dijkstra(Graph g, int r)
    {
        visited = new LinkedList<>(); // store all visited nodes
        queue = new LinkedList<>(); // store nodes that will go into queue

        int[] d = new int[g.n]; // array to store distance from node to source
        int[] p = new int[g.n]; // array to store the parents of each node (to form a path)

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
                        // if distance of current node + weight of the edge to the destination node is less than
                        // the weight of the destination node (which is infinite when the program starts)
                        // then
                        if (d[currentNode] + edge.weight < d[edge.destination])
                        {
                            d[edge.destination] = d[currentNode] + edge.weight; // replace weight of following node
                            p[edge.destination] = currentNode; // set its parent to current node
                        }

                        queue.add(edge.getDestination()); // add the following node
                    }
                }
            }
        }

        printDijkstra(p, d, r, g.n); // print the solution with the parents, distance, root and all nodes, in that order
    }

    public void printDijkstra(int[] parent, int[] distance, int root, int nodes){
        System.out.println("Dijkstra Algorithm: (With all paths)");
        for (int i = 0; i < nodes ; i++)  // for each node of the graph
        {
            // print the root, the current node, distance between both and the shortest path
            System.out.print(" " + root + " -> " + i + " = Distance : " + distance[i] + "  Path : ");
            printPath(parent, i, root);
            System.out.println();
        }
    }

    public void printPath(int[] parent, int destination, int root){
        //if node is source then stop recursion
        if(parent[destination] == -1) {
            System.out.print(root + " ");
            return;
        }
        printPath(parent, parent[destination], root);
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
            r = reader.nextInt(); // read user input

            if (r < 0 || r > nodes) // check for error, if root is not between 0 and total number of nodes
            {
                System.out.println("Please enter a number between 0 and " + nodes + "."); // alert the user of the rule
            }
        }

        // run Dijkstra algorithm passing the generated graph and root node chosen by user input
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra(g, r);
    }
}
