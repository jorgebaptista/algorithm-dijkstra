public class Edge
{
    int from; // where does edge start
    int destination; // where it ends
    int weight;

    public Edge(int from, int destination, int weight)
    {
        this.from = from;
        this.destination = destination;
        this.weight = weight;
    }

    // getters
    public int getFrom()
    {
        return from;
    }

    public int getDestination()
    {
        return destination;
    }

    public int getWeight()
    {
        return weight;
    }

    // return the properties as a string, e.g. 1,2:3
    public String toString()
    {
        return from + "," + destination + ":" + weight;
    }
}
