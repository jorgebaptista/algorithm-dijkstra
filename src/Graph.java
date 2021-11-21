import java.util.LinkedList;

public class Graph {

    int n; // num of nodes

    LinkedList<Edge> adjLists[]; // array of linked lists of edges.

    public Graph(int n)
    {
        this.n = n;
        adjLists = new LinkedList[n]; // create new array of linked lists
        for (int i = 0; i < adjLists.length; i++) // for each node
        {
            adjLists[i] = new LinkedList(); // create a new linked list
        }
    }

    public void addEdge(int v, int u, int weight)
    {
        if (!hasEdge(v, u))
        {
            adjLists[v].add(new Edge(v, u, weight));
            adjLists[u].add(new Edge(u, v, weight));
        }
    }

    public boolean hasEdge(int v, int u)
    {
        return adjLists[v].contains(u);
    }

    public Iterable<Edge> edges(int v)
    {
        return adjLists[v];
    }
}
