import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomGraph
{
    int nMin;
    int nMax;
    int sparsity; // from 0 to 100 how probable for 2 nodes to get an edge
    String fileName;

    public RandomGraph(int min, int max, int sparsity, String fileName)
    {
        // properties of the file in the constructor
        this.nMin = min;
        this.nMax = max;
        this.sparsity = sparsity;
        this.fileName = fileName;
    }

    public void generateGraph()
    {
        try {
            File graphFile = new File(fileName);
            if (graphFile.createNewFile())  // if file does not exist, create it
            {
                System.out.println("File created: " + graphFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter graphFile = new FileWriter(fileName);

            graphFile.write("S\n"); // write first line as S for stream of weighted arcs

            Random random = new Random();
            int n = random.nextInt(nMax - nMin + 1) + nMin; // generate a number of nodes between the min and max

            graphFile.write(n + "\n"); // write the second line the number of nodes

            for (int i = 0; i < n; i++)  // for each node
            {
                for (int j = i + 1; j <= n; j++) // for each node after that
                {
                    if (random.nextInt(100) > sparsity) // sparse - less probability = more density
                    {
                        // for each node iterated through all nodes after it and has a probability to create an edge
                        graphFile.write(i + " " + j + " " + (random.nextInt(16) + 1) + "\n");
                        graphFile.flush();
                    }
                }
            }
            graphFile.close(); // close the file at the end

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        RandomGraph sparseGraph = new RandomGraph(200, 1000, 98, "SparseGraph.txt");
        sparseGraph.generateGraph();

        RandomGraph denseGraph = new RandomGraph(200, 1000, 20, "DenseGraph.txt");
        denseGraph.generateGraph();
    }
}
