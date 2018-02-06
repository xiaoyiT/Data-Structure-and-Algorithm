import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread;

class QuickFindUF {
	private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for(int i = 0 ; i < N ; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int m, int n) {
        int mid = id[m];
        int nid = id[n];
        for(int i = 0 ; i < id.length ; i++) {
            if(id[i] == mid)
                id[i] = nid;
        }
    }


    public static void main(String[] args) {
        
        //read data from text file
        List<Integer> data1 = new ArrayList<Integer>();
        List<Integer> data2 = new ArrayList<Integer>();
        try{
            FileReader fr = new FileReader("8pair.txt");
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
        int size = data1.size();
        int[][] data = new int[size][2];
        for(int i = 0; i < size; i++) {
            data[i][0] = (int)(data1.get(i));
            data[i][1] = (int)(data2.get(i));
        }

        QuickFindUF qf = new QuickFindUF(8192);
        //qf.QuickFindUF(8192);
        //System.out.println(size);
        //int count = 0;
        //System.out.println(size);
        long startTime = System.nanoTime();
        //for(int m = 0; m < 5; m++) {   
            for(int i = 0; i < size; i++) {

                // String s = data.get(i);
                // String[] pair = s.split(" ");
                int p = data[i][0];
                int q = data[i][1];
                if(!qf.connected(p, q)) {
                    qf.union(p, q);
                    System.out.println(p + " and " + q + " are not initially connected");
                }
            }
        //}
        long endTime = System.nanoTime();
        double totalTime = (endTime - startTime) / 1000000000.0;
        System.out.println(totalTime); 
    }
}