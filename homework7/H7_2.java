package homework7;
import java.io.*;
import java.util.*;

 /* Name: Qian Cai
         * NU ID:001389278

         * Created by Qian Cai on 2019/10/25.
 */
public class H7_2 {
    private int V;

    private LinkedList<Integer> adj[];
   public void Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
    }
    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;
        System.out.print(v+" ");
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
    void DFS(int v)
    {
        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    public static void main(String[] args) {
            H7_2 h=new H7_2();
        h.Graph(8);
        h.addEdge(5,6);
        h.addEdge(4,6);
        h.addEdge(3,7);
        h.addEdge(6,7);
        h.addEdge(5,7);
        h.addEdge(1,4);
        h.addEdge(2,4);
        h.addEdge(2,3);
        h.addEdge(4,7);
        System.out.println("Following is Deepth First Traversal "+
                "(starting from vertex 1)");
        h.DFS(1);





    }
    }



