import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Dijkstra {

	private int vertexSize;		//the number of vertices in graph
	private double[][] distance;	//the matrix which shows the connection and distance between vertices

	private boolean[] visited;

	public Dijkstra(String filename) {
		try {
			//read data from text file
			File file = new File(filename);
			Scanner sc = new Scanner(file);

			int count = 1;

			while (sc.hasNextLine()) {
				String s = sc.nextLine();

				if (count == 1) {
					vertexSize = Integer.valueOf(s);
					distance = new double[vertexSize][vertexSize];
					visited = new boolean[vertexSize];

					count++;
				} else if (count == 2) {
					count++;

				} else {
					String[] edgeString = s.split(" ");

					int start = Integer.valueOf(edgeString[0]);
					int end = Integer.valueOf(edgeString[1]);
					double weight = Double.parseDouble(edgeString[2]);

					//store the distance into distance matrix
					distance[start][end] = weight;

					count++;
				}

			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void shortestPath(int start) {
		double[] distTo = new double[vertexSize];
		int[] prev = new int[vertexSize];

		for (int i = 0; i < vertexSize; i++) {
			distTo[i] = 99999.0;
		}

		distTo[start] = 0.0;
		prev[start] = -1;

		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(start);  
        visited[start] = true; 
        System.out.println(start + " has been visited!"); 
  
        while (!queue.isEmpty()) {  
            int s = queue.remove();   
  
            for (int j = 0; j < vertexSize; j++) {  
                if (distTo[s] + distance[s][j] < distTo[j]) {                       
                    distTo[j] = distTo[s] + distance[s][j]; 
                    if (visited[j] == false) {
                    	queue.add(j); 
                    	visited[j] = true;
                    	System.out.println(j + " has been visited!");
                    } 
                }  
            }  
  
        } 

        for (int i = 0; i < vertexSize; i++) {
        	System.out.println("From " + start + " to " + i + " : " + distTo[i] + "\n");
        } 

	}


	public static void main(String[] args) {
		Dijkstra d = new Dijkstra("4a.txt");
		d.shortestPath(0);

	}
}



