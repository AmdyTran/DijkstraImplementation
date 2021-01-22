import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;


public class EmptyDijkstra {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("dijkstraGenerated.txt");
		Scanner s = new Scanner(file);
		int V = s.nextInt();		//amount of vertices
		int E = s.nextInt();		//amount of edges
		int[][] graphInfo = new int[E][3]; //edge from u to v, with weight
		for(int i = 0; i< E; i++){
			graphInfo[i][0] = s.nextInt();	//u
			graphInfo[i][1] = s.nextInt();	//to v
			graphInfo[i][2] = s.nextInt();	//weight
		}
		s.close();
		Graph g = new Graph(V, E, graphInfo);
		//System.out.println(Dijkstra.solve(g, 0, 19998));
		System.out.println(g.solve(0, 19998));
	}

}
/*
class Dijkstra {
	public static int solve(Graph G, int start, int end) {
		int[] distance = new int[G.n];
		boolean[] visited = new boolean[G.n];
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(start, 0));
		for(int i = 1; i < G.n; i++){
			distance[i] = Integer.MAX_VALUE;
		}

		while(!queue.isEmpty()){
			Pair u = queue.poll();
			while(visited[u.index]){
				u = queue.poll();
			}
			visited[u.index] = true;
			for(int i = 0; i < G.out_degrees[u.index]; i++){
				if(G.weights[u.index][i] + distance[u.index] < distance[G.edges[u.index][i]]){
					distance[G.edges[u.index][i]] = G.weights[u.index][i] + distance[u.index];
					queue.add(new Pair(G.edges[u.index][i], distance[G.edges[u.index][i]]));
				}
			}
		}
		return distance[end];
	}
}
*/
class Pair implements Comparable<Pair>{
	int index;
	int cost;
	
	public Pair(int key, int cost){
		index = key;
		this.cost = cost;
	}
	
	@Override
    public int compareTo(Pair other) {
        if(this.cost < other.cost){
          return -1;
        }
        else if(this.cost==other.cost){
          return 0;
        }
        else{
          return 1;
        }
    }
	
}
class Graph{ 
	int n;				 //amount of vertices
	int m;				 //amount of edges
	int out_degrees[]; 	 //degrees of a certain vertex, assuming directed
	int[][] edges; 	     //edges[i][j] is an edge from i to its j-th edge
	int[][] weights; 	 //weight of i to j

	Graph(int n, int m, int[][] info){
		this.n = n;
		this.m = m;
		out_degrees = new int[n];
		for(int i = 0; i<n; i++){
			out_degrees[i] = 0;
		}
		for(int i = 0; i<m; i++){
			out_degrees[info[i][0]]++;
		}

		edges = new int[n][];
		weights = new int[n][];
		
		for(int i =0; i<n; i++){
			if(out_degrees[i] != 0){ 
				edges[i] = new int[out_degrees[i]];
				weights[i] = new int[out_degrees[i]];
				out_degrees[i] = 0;
			}
			//if out_degrees is 0 then we dont care at all since it's a sink (senke?)	
			else{
				edges[i] = null;
				weights[i] = null;
			}
		}

		for(int i = 0; i<m; i++){
			edges[info[i][0]][out_degrees[info[i][0]]] = info[i][1];
			weights[info[i][0]][out_degrees[info[i][0]]] = info[i][2];
			out_degrees[info[i][0]]++;
		}
	}
	public int solve(int start, int end) {
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(start, 0));
		for(int i = 1; i < n; i++){
			distance[i] = Integer.MAX_VALUE;
		}

		while(!queue.isEmpty()){
			Pair u = queue.poll();
			while(visited[u.index]){
				u = queue.poll();
			}
			visited[u.index] = true;
			for(int i = 0; i < out_degrees[u.index]; i++){
				if(weights[u.index][i] + distance[u.index] < distance[edges[u.index][i]]){
					distance[edges[u.index][i]] = weights[u.index][i] + distance[u.index];
					queue.add(new Pair(edges[u.index][i], distance[edges[u.index][i]]));
				}
			}
		}
		return distance[end];
	}
	
}