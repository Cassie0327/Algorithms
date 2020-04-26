package homework10;
import java.util.*;
import java.lang.*;
import java.io.*;
public class H10_3b {
    private static final int V = 6;

    int minKey(int key[], Boolean mstSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }


    void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println((parent[i]+1) + " - " +(i+1)+ "\t" + graph[i][parent[i]]);
    }

    void primMST(int graph[][])
    {
        int parent[] = new int[V];

        int key[] = new int[V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;
            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, graph);
    }
    public static void main(String[] args)
    {

        H10_3b t = new H10_3b();
        int graph[][] = new int[][] { { 0, 53, 0,0,0, 55 },
                { 53, 0, 47, 0,0, 70 },
                { 0, 47, 0, 45,21,68  },
                { 0, 0, 45, 0, 56,32 },
                { 0, 0, 21, 56, 0,37 } ,
                { 55, 70, 68, 32, 37,0 }

        };

        t.primMST(graph);
    }

}
