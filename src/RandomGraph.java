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

    public void generateGraph() {
        try {
            File graphFile = new File(fileName);
            if (graphFile.createNewFile()) {
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

            graphFile.write("S\n");

            Random random = new Random();
            int n = random.nextInt(nMax - nMin + 1) + nMin;

            graphFile.write(n + "\n");

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (random.nextInt(100) > sparsity) // sparse - less probability = more density
                    {
                        // for each node iterated through all nodes after it and has a probability to create an edge
                        graphFile.write(i + " " + j + " " + (random.nextInt(16) + 1) + "\n");
                        graphFile.flush();
                    }
                }
            }
            graphFile.close();

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
