import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class BFS {
	private Map<Integer, List<Integer>> graph;
	private int vertexSize;
	private boolean[] visited;

	public BFS() {
		readData("NYC.txt");
	}

	private void readData(String filename) {

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
					graph = new HashMap<Integer, List<Integer>>();
					visited = new boolean[vertexSize + 1];

					count++;

				} else if (count == 2) {
					count++;
					//the second line stores the size of edges
					//here we do nothing
				} else {

					String[] edgeString = s.split(" ");

					int start = Integer.valueOf(edgeString[0]);
					int end = Integer.valueOf(edgeString[1]);
					//float weight = Float.parseFloat(edgeString[2]);

					if (graph.containsKey(start)) {
						List<Integer> value = graph.get(start);
						if (!value.contains(end)) {
							value.add(end);
							graph.put(start, value);
						}
					} else {
						List<Integer> value = new ArrayList();
						value.add(end);
						graph.put(start, value);
					}

					if (graph.containsKey(end)) {
						List<Integer> value = graph.get(end);
						if (!value.contains(start)) {
							value.add(start);
							graph.put(end, value);
						}
					} else {
						List<Integer> value = new ArrayList();
						value.add(start);
						graph.put(end, value);
					}
					//System.out.println(weight);
					count++;

				}
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void BFSTraverse() {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= vertexSize; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				System.out.println("vertex" + i + " has been visited!");
				q.add(i);

				while(!q.isEmpty()) {
        			int top = q.poll();//get the first value in the queue
        			List<Integer> value = graph.get(top);

        			if (value != null) {
        				for (int j = 0; j < value.size(); j++) {
        					if (visited[value.get(j)] == false) {
        						visited[value.get(j)] = true;
        						System.out.println("vertex " + value.get(j) + " has been visited!");
        						q.add(value.get(j));
        					}
        				}
        			}
    			}
			}
		}
	}

	public static void main(String[] args) {
		BFS b = new BFS();
		b.BFSTraverse();
		System.out.println("Succeed!");
	}
}