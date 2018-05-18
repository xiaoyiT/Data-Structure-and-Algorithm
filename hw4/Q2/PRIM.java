import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PRIM {
	private int vertexSize;	//the number of vertices
	private float[][] graph;	//graph[i][j] represents the weight from i to j

	public PRIM(String filename) {
		//System.out.println("enter");

		try {
			//read data from txt file
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			int count = 1;

			while (sc.hasNextLine()) {
				String s = sc.nextLine();

				if (count == 1) {
					//System.out.println("count == 1");
					vertexSize = Integer.valueOf(s);
					graph = new float[vertexSize][vertexSize];

					count++;

				} else if (count == 2) {
					count++;
					//the second line stores the size of edges
					//here we do nothing
				} else {

					String[] edgeString = s.split(" ");

					int start = Integer.valueOf(edgeString[0]);
					int end = Integer.valueOf(edgeString[1]);
					float weight = Float.parseFloat(edgeString[2]);

					graph[start][end] = weight;
					//System.out.println(weight);
					count++;

				}
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void prim(int start) {

		List<Integer> used = new ArrayList();
        float[] lowcost = new float[vertexSize];    
        //int[] mid= new int[n];
        float min, sum = 0.0f, MAX = 9999.0f;

        int minID;

        used.add(0);
        //initialize the helper array
        for (int i = 1; i < vertexSize; i++) {
        	lowcost[i] = graph[0][i];
        }

        for (int i = 1; i < vertexSize; i++) {
        	min = MAX;
        	minID = start;

        	//find the point that has the smallest distance from the set
        	for (int j = 1; j < vertexSize; j++) {
        		if (lowcost[j] != 0.0f && lowcost[j] < min) {
        			min = lowcost[j];
        			minID = j;
        		}
        	}

        	//System.out.println("Now, minID is: " + minID);
        	used.add(minID);

        	if (minID == 0.0) {
        		//System.out.println("Wrong data" + " " + "i is: " + i);
        		return;
        	} 

        	lowcost[minID] = 0.0f;
        	sum += min;

        	for (int j = 1; j < vertexSize; j++) {
        		if (!used.contains(j)) {
        			if ((lowcost[j] == 0.0f && graph[minID][j] != 0.0f) || (lowcost[j] != 0.0f && graph[minID][j] != 0.0f && lowcost[j] > graph[minID][j])) {
        				lowcost[j] = graph[minID][j];
        			}
        		}

        		// if (lowcost[j] > 0 && graph[minID][j] > 0 && lowcost[j] > graph[minID][j]) {
        		// 	lowcost[j] = graph[minID][j];
        		// } else if (lowcost[j] == 0.0f && graph)
        	}
        }

        //System.out.println("sum is: " + sum);
	} 


	public static void main(String[] args) {
		long startTime = System.nanoTime();

		PRIM p = new PRIM("mediumEWG.txt");
		p.prim(0);

		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Running time is: " + totalTime);
	}


}


