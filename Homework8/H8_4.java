package homework8;
import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Created by cassie on 11/12/19.
 */
public class H8_4 {
    class Edge implements Comparable<Edge>
    {
        char src,dest;
        int weight;
        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    }
    class subset
    {
        char parent;
         int rank;
    };

    int V, E;
    Edge edge[];


    H8_4(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
    char find(subset subsets[], char i)
    {

        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);

        }
        return subsets[i].parent;
    }

    void Union(subset subsets[], char x, char y)
    {
        char xroot = find(subsets, x);
        char yroot = find(subsets, y);


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
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i=0; i<V; ++i)
            result[i] = new Edge();
        Arrays.sort(edge);

        subset subsets[] = new subset[('g'+1)];
        for(char g='a'; g<('g'+1); ++g)
            subsets[g]=new subset();

        for (char v = 'a'; v < ('g'+1); ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;
        while (e < V - 1)
        {

            Edge next_edge = new Edge();
            next_edge = edge[i++];
            char x = find(subsets, next_edge.src);
            char y = find(subsets, next_edge.dest);


            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }


        System.out.println("Following are the edges in " +
                "the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src+" -- " +
                    result[i].dest+" == " + result[i].weight);
    }



    public static void main(String [] args)
    {
        int V = 7;
        int E = 12;
        H8_4 graph = new H8_4(V, E);

        graph.edge[0].src = 'a';
        graph.edge[0].dest = 'b';
        graph.edge[0].weight = 5;

        graph.edge[1].src = 'a';
        graph.edge[1].dest = 'c';
        graph.edge[1].weight = 3;

        graph.edge[2].src = 'b';
        graph.edge[2].dest = 'c';
        graph.edge[2].weight = 4;

        graph.edge[3].src = 'b';
        graph.edge[3].dest = 'd';
        graph.edge[3].weight = 6;

        graph.edge[4].src = 'b';
        graph.edge[4].dest = 'e';
        graph.edge[4].weight = 2;

        graph.edge[5].src = 'd';
        graph.edge[5].dest = 'c';
        graph.edge[5].weight = 5;

        graph.edge[6].src = 'd';
        graph.edge[6].dest = 'e';
        graph.edge[6].weight = 7;

        graph.edge[7].src = 'd';
        graph.edge[7].dest = 'f';
        graph.edge[7].weight = 9;

        graph.edge[8].src = 'c';
        graph.edge[8].dest = 'f';
        graph.edge[8].weight = 11;

        graph.edge[9].src = 'f';
        graph.edge[9].dest = 'e';
        graph.edge[9].weight = 12;

        graph.edge[10].src = 'e';
        graph.edge[10].dest = 'g';
        graph.edge[10].weight = 8;

        graph.edge[11].src = 'f';
        graph.edge[11].dest = 'g';
        graph.edge[11].weight = 7;

        graph.KruskalMST();
    }
}
