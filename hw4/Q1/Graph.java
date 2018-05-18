import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
	private int size;	//the number of vertices;
	//private int[] vertices;
	private int[][] matrix;		//the relationship between vertices

	private static final int NEVERVISITED = 0;
	private static final int ISVISITING = -1;
	private static final int ALLVISITED = 1;

	public Graph(String filename) {
		//read file from text file
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			int count = 1;
			//int verticeIndex = 0;

			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				//the first line stores the size of vertices
				if (count == 1) {	
					size = Integer.valueOf(s);
					//vertices = new int[size];
					matrix = new int[size][size];
					count++;
				} else if (count == 2) {
					count++;
				} else {
					String[] edge = s.split(" ");
					int start = Integer.valueOf(edge[0]);
					int end = Integer.valueOf(edge[1]);
					matrix[start][end] = 1;
					matrix[end][start] = 1;
				}
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private boolean isAcylic() {
		int[] visited = new int[size];
		for (int i = 0; i < size; i++) {
			if (visited[i] == NEVERVISITED) {
				if (!dfsVisit(i, visited)) {
					return false;
				} else {
					visited[i] = ALLVISITED;
				}
			}
		}
		return true;
	}

	private boolean dfsVisit(int v, int[] visited) {
		visited[v] = ISVISITING;
		for (int j = 0; j < size; j++) {
			if (matrix[v][j] == 1) {
				if (visited[j] == ISVISITING) {
					return false;
				} else if (visited[j] == NEVERVISITED) {
					if (!dfsVisit(j, visited)) {
						return false;
					} else {
						visited[j] = ALLVISITED;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Graph graph = new Graph("mediumEWG.txt");
		boolean noCycle = graph.isAcylic();
		if (noCycle) {
			System.out.println("The graph is acyclic.");
		} else {
			System.out.println("The graph is not acyclic.");
		}
	}
}