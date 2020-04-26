package homework10;
import java.util.*;
import java.lang.*;
import java.io.*;
public class H10_3a {

    // A class to represent a graph edge
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;

        // Comparator function used for sorting edges
        // based on their weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };

    // A class to represent a subset for union-find
    class subset
    {
        int parent, rank;
    };

    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // Creates a graph with V vertices and E edges
    H10_3a(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }


    int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }


    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);


        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;


        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void KruskalMST()
    {
        Edge result[] = new Edge[V];  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;
        for (i=0; i<V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge);

        subset subsets[] = new subset[V+1];
        for(i=1; i<(V+1); ++i)
            subsets[i]=new subset();

        // Create V subsets with single elements
        for (int v = 1; v < (V+1); ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;  // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1)
        {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in " +
                "the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src+" -- " +
                    result[i].dest+" == " + result[i].weight);
    }
    // Driver Program
    public static void main (String[] args)
    {


        int V = 6;
        int E = 10;
        H10_3a graph = new H10_3a(V, E);

        // add edge 0-1
        graph.edge[0].src = 1;
        graph.edge[0].dest = 2;
        graph.edge[0].weight = 53;

        // add edge 0-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 6;
        graph.edge[1].weight = 55;

        // add edge 0-3
        graph.edge[2].src = 2;
        graph.edge[2].dest = 6;
        graph.edge[2].weight = 70;

        // add edge 1-3
        graph.edge[3].src = 2;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 47;

        // add edge 2-3
        graph.edge[4].src = 3;
        graph.edge[4].dest = 6;
        graph.edge[4].weight = 68;

        graph.edge[5].src = 5;
        graph.edge[5].dest = 6;
        graph.edge[5].weight = 37;

        // add edge 0-2
        graph.edge[6].src = 3;
        graph.edge[6].dest = 5;
        graph.edge[6].weight = 21;

        // add edge 0-3
        graph.edge[7].src = 3;
        graph.edge[7].dest = 4;
        graph.edge[7].weight = 45;

        // add edge 1-3
        graph.edge[8].src = 4;
        graph.edge[8].dest = 5;
        graph.edge[8].weight = 56;

        // add edge 2-3
        graph.edge[9].src = 4;
        graph.edge[9].dest = 6;
        graph.edge[9].weight = 32;

        graph.KruskalMST();
    }

}
