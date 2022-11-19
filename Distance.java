package satTest;
import java.util.*;

public class Distance {
	static class Edge{
        String start;
        String end;
        int w;
        Edge(String start,String end,int w)
        {
            this.start=start;
            this.end=end;
            this.w=w;
        }
    }
    static class Graph{
        int vertices;
        LinkedList<Edge>[] adj;

        int paths;

        int Distance=0;
        Graph(int vertices)
        {
            this.vertices=vertices;
            adj=new LinkedList[vertices];
            for(int i=0;i<vertices;i++)
            {
                adj[i]=new LinkedList<>();
            }
        }
        public void addingEdge(String start,String end,int w)
        {
            Edge e1=new Edge(start,end,w);
            Edge e2=new Edge(end,start,w);
            int index1=(int)(start.charAt(0))-65;
            int index2=(int)(end.charAt(0))-65;
            adj[index1].addFirst(e1);
            adj[index2].addFirst(e2);
        }
        public double calculateAverageDistance(String a,String b)
        {
            boolean visited[]=new boolean[vertices];
            for(int i=0;i<vertices;i++)
                visited[i]=false;
            paths=0;
            Distance=0;
            visited[(int)(a.charAt(0))-65]=true;
            calculate(a,b,visited,0);
            return ((double)Distance/(double) paths);
        }
        public void calculate(String a,String b,boolean visited[],int dt)
        {
            if(a.equals(b))
            {
                paths++;
                Distance+=dt;
                return;
            }
            int index=(int)(a.charAt(0))-65;
            for(int i=0;i<adj[index].size();i++)
            {
                String to=adj[index].get(i).end;
                int indexOfDestination=(int)(to.charAt(0))-65;
                if(visited[indexOfDestination]==false)
                {
                    visited[indexOfDestination]=true;
                    calculate(to,b, visited, dt+adj[index].get(i).w);
                    visited[indexOfDestination]=false;
                }
            }
            return;
        }
    }
    public static void main(String[] args)
    {
        Graph g=new Graph(5);
        g.addingEdge("A","B",12);
        g.addingEdge("A","C",13);
        g.addingEdge("A","D",11);
        g.addingEdge("A","E",8);
        g.addingEdge("B","C",3);
        g.addingEdge("C","E",4);
        g.addingEdge("D","E",7);
        try (Scanner in = new Scanner(System.in)) {
			System.out.println("Enter Node One");
			String a=in.next();
			System.out.println("Enter Node Two");
			String b= in.next();
			double avgDt=g.calculateAverageDistance(a,b);
			System.out.println("Average Distance Obtained is "+avgDt);
		}
    }

}

