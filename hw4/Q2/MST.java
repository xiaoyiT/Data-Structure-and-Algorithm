import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MST {

	private int sizeVertice;	//represents the number of vertices
	private int sizeEdge;
	private Edge[] edges;
	private int[] verticeId;
	//private int[] size;

	private int[] id;	//array to store the root of vertice
	private int[] treeSize;	//array to store the size of each union

	private List<Edge> edgeResult;
	private double weightSum;

	public MST(String filename) {
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			int count = 1;
			int edgeIndex = 0;
			//int verticeIndex = 0;

			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				//the first line stores the size of vertices
				if (count == 1) {	
					sizeVertice = Integer.valueOf(s);
					//vertices = new int[size];
					count++;
				} else if (count == 2) {
					//the second line stores the number of edges
					sizeEdge = Integer.valueOf(s);
					edges = new Edge[sizeEdge];
					count++;
				} else {
					String[] edgeString = s.split(" ");

					int start = Integer.valueOf(edgeString[0]);
					int end = Integer.valueOf(edgeString[1]);
					double weight = Double.parseDouble(edgeString[2]);

					edges[edgeIndex++] = new Edge(start, end, weight);
				}
			}

			sc.close();

			initialQU();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initialQU() {
		id = new int[sizeVertice];
		treeSize = new int[sizeVertice];
		for (int i = 0; i < sizeVertice; i++) {
			id[i] = i;
			treeSize[i] = i;
		}
	}

	private int root(int m) {
		while(m != id[m])
			m = id[m];
		return m;
	}

	private boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	private void Union(int p, int q) {
		int proot = root(p);
		int qroot = root(q);
		if(treeSize[proot] < treeSize[qroot]) {
			id[proot] = qroot;
			treeSize[qroot] += treeSize[proot];
		}
		else{
			id[qroot] = proot;
			treeSize[proot] += treeSize[qroot];
		}
	}

	public class Edge implements Comparable {
		private int start, end;
		private double weight;
		public Edge(int i, int j, double k) {
			this.start = i;
			this.end = j;
			this.weight = k;
		}

		@Override
		public int compareTo(Object o) {  
        	Edge to = (Edge)o;  
        	if (this.weight > to.weight) {
        		return 1; 
        	} else if (this.weight == to.weight) {
        		return 0;
        	} else {
        		return -1;
        	}  
        }
	}

	private void Kruskal() {
		edgeResult = new ArrayList();
		weightSum = 0.0;
		Arrays.sort(edges);
		for (int i = 0; i < sizeEdge; i++) {
			Edge cur = edges[i];
			if (!connected(cur.start, cur.end)) {
				edgeResult.add(cur);
				weightSum += cur.weight;
				Union(cur.start, cur.end);
			}
		}
	}

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		MST mst = new MST("mediumEWG.txt");
		mst.Kruskal();

		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Running time is: " + totalTime);
		//System.out.println(mst.weightSum);
	}
}