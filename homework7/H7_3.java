package homework7;
import java.util.*;
/**
 Name: Qian Cai
 * NU ID:001389278

 * Created by Qian Cai on 2019/10/25.
 */
public class H7_3 {
    private int V;
    private LinkedList<Integer> adj[];
    public void Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
    void addEdge(int v,int w)
    {
        adj[v].add(w);
        adj[w].add(v);
    }

    void BFS(int s)
    {

        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            s = queue.poll();
            System.out.print(s+" ");
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[])
    {
        H7_3 h=new H7_3();
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
        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 1)");
        h.BFS(1);

    }
}
