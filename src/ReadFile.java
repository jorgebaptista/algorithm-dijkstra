import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public Graph generateGraph(String fileName)
    {
        int nodes;
        String[] edges;

        try
        {
            File graphFile = new File(fileName);
            Scanner fileReader = new Scanner(graphFile);

            if (fileReader.nextLine().compareTo("S") == 0)
            {
                System.out.println("Reading a stream of weighted arcs...");
            }
            else
            {
                System.out.println("This program only accepts a stream of weighted arcs for input");
                return null;
            }

            nodes = Integer.parseInt(fileReader.nextLine());

            List<String> lines = new ArrayList<>();

            while (fileReader.hasNextLine())
            {
                lines.add(fileReader.nextLine());
            }

            edges = lines.toArray(new String[lines.size()]);

        } catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            e.printStackTrace();
            return null;
        }

        Graph g = new Graph(nodes + 1);

        // add edges
        for (int i = 0; i < edges.length - 1; i++)
        {
            String[] splitted = edges[i].split("\\s+"); // split each line per white space
            int[] numbers = new int[splitted.length]; // create an int array to store the converted values
            for (int j = 0; j < splitted.length; j++)
            {
                numbers[j] = Integer.parseInt(splitted[j]); // convert value to int and store in int array
            }
            g.addEdge(numbers[0], numbers[1], numbers[2]); // create the edges
        }

        return g;
    }
}
