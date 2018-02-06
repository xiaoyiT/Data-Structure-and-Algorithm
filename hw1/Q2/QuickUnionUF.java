import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class QuickUnionUF {
	private int[] id;

	public QuickUnionUF(int N) {
		id = new int[N];
		for(int i = 0 ; i < N ; i++) {
			id[i] = i;
		}
	}

	public int root(int i) {
		while(i != id[i])
			i = id[i];
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}

	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //read data from text file
        List<Integer> data1 = new ArrayList<Integer>();
        List<Integer> data2 = new ArrayList<Integer>();
        try{
            FileReader fr = new FileReader("8192pair.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            //Scanner scanner = new Scanner(file);
            while((s = br.readLine()) != null) {
                String[] pair = s.split(" ");
                int p = Integer.parseInt(pair[0]);
                int q = Integer.parseInt(pair[1]);
                data1.add(p);
                data2.add(q);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuickUnionUF qf = new QuickUnionUF(8192);
        int size = data1.size();
        for(int i = 0; i < size; i++) {
            int p = (int)(data1.get(i));
            int q = (int)(data2.get(i));
            if(!qf.connected(p, q)) {
                qf.union(p, q);
                System.out.println(p + " and " + q + " are not initially connected");
            }
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total running time is " + totalTime + " ms."); 
    }
}