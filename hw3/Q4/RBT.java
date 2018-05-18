import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RBT<Key extends Comparable<Key>, Value> {
	private static boolean RED = true;
    private static boolean BLACK = false;

	private Node root;	//root node in the 23tree
    private int size;

    //left rotation
    private Node RotateLeft(Node h) {
        //assume that h.right is red
        Node x = h.right;
        h.right = x.left;   
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    //right rotation
    private Node RotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void FlipColor(Node h) {
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    private Node balance(Node node) {

        if (IsRed(node.left) && IsRed(node.right) && !IsRed(node)) {
            if ((IsRed(node.left.left) || IsRed(node.left.right) || IsRed(node.right.left) || IsRed(node.right.right)))
                FlipColor(node);
        }
        else {
            if (IsRed(node.left)){
                if (IsRed(node.left.right))
                    node.left = RotateLeft(node.left);
                if (IsRed(node.left) && IsRed(node.left.left))
                    node = RotateRight(node);
            }else if (IsRed(node.right)){
                if (IsRed(node.right) && IsRed(node.right.left))
                    node.right = RotateRight(node.right);

                if (IsRed(node.right) && IsRed(node.right.right))
                    node = RotateLeft(node);
            }

            if (IsRed(node.left) && IsRed(node.right) && !IsRed(node)) {
                if ((IsRed(node.left.left) || IsRed(node.left.right) || IsRed(node.right.left) || IsRed(node.right.right)))
                    FlipColor(node);
            }
        }
        return node;
    }

	//@override
	private void put(Key key, Value value) {
		//the key or value cannot be null
		if(key == null || value == null) {
			return;
		}
		
		root = Put(root, key, value);
		root.color = BLACK;
	}

	private Node Put(Node h, Key key, Value value) {
        if (h == null) {
            size++;
        	return new Node(key, value, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
        	h.left = Put(h.left, key, value);
        } else if (cmp > 0) {
        	h.right = Put(h.right, key, value);
        } else {
        	h.value = value;
        }

        return balance(h);
        //return h;
    }

    //@override
    private int size() {
    	return size;
    }

    private int get(Key key) {
    	int result = 0;
        Node cur = root;
        while (cur != null) {
        	result++;
            //compare given key to the key of current node
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return result;
            } else if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return result;
    }

    class Node {
	    public Key key;
        public Value value;
        public Node left;
        public Node right;
        //public int number;
        public boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            //this.number = number;
            this.color = color;
        }
    }

    private boolean IsRed(Node node) {
        if (node == null) {
        	return false;
        }
        return node.color == RED;
    }

    private static double getAvg(int[] array) {
    	int sum = 0, len = array.length;
    	for (int i = 0; i < len; i++) {
    		sum += array[i];
    	}
    	return (double)sum / (double)len;
    }

    private static double getStd(double avg, int[] a) {
    	int len = a.length;
    	double sum = 0;
    	for (int i = 0; i < len; i++) {
    		double dev = (double)a[i] - avg;
    		sum += Math.pow(dev, 2);
    	}
    	sum /= len - 1;
    	sum = Math.pow(sum, 0.5);
    	return sum;
    }

    public static void main(String[] args) throws FileNotFoundException{
    	Random r = new Random();
    	int trials = 1000, MaxN = 10000, a, b;
    	int length = 0;
    	double[] avg = new double[MaxN + 1];
    	double[] std = new double[MaxN + 1];

        PrintWriter pw = new PrintWriter(new File("data.csv"));
        StringBuilder sb = new StringBuilder();

        sb.append("size");
        sb.append(",");
        sb.append("avg");
        sb.append(",");
        sb.append("std");
        sb.append("\n");

    	for (int size = 1; size < MaxN; size++) {
            sb.append(Integer.toString(size));
            sb.append(",");
    		System.out.println(size);
    		int[] len = new int[trials];
    	    for (int i = 0; i < trials; i++) {
    	    	RBT<Integer, String> rbt = new RBT();
    		    for (int j = 0; j < size; j++) {
    			    a = r.nextInt(size);
    			    rbt.put(j, "a");
    		    }
    		    b = r.nextInt(size);
    		    length = rbt.get(b);
    		    len[i] = length;
    	    }
    	    double average = getAvg(len);
    	    avg[size] = average;
    	    double stdDev = getStd(average, len);
    	    std[size] = stdDev;
    	    // System.out.println("avg is: " + average);
    	    // System.out.println("std is: " + stdDev);
            sb.append(Double.toString(average));
            sb.append(",");
            sb.append(Double.toString(stdDev));
            sb.append("\n");
    	}

        pw.write(sb.toString());
    	pw.close();
    }

}



