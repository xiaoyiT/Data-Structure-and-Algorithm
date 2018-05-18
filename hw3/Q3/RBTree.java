import java.util.*;
import java.util.Random;

public class RBTree<Key extends Comparable<Key>, Value> {

	private static boolean RED = true;
    private static boolean BLACK = false;

	private Node root;
	private int size;

	class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private boolean color;

		private Node(Key key, Value value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}
	}

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

    private boolean IsRed(Node node) {
        if (node == null) {
        	return false;
        }
        return node.color == RED;
    }

    private double countRedNodes() {
    	Node x = root;
    	int count = countRedNodes(x);
    	double pc = (double)count / (double)size(root) / 2;
        //System.out.println(count);
    	return pc;
    }

    private int size(Node node) {
        if(node == null) {
            return 0;
        }

        return size(node.left) + size(node.right) + 1;
    }

    private int countRedNodes(Node x) {
    	if(x == null) {
    		return 0;
    	}
    	if(x.color == RED) {
    		return 1 + countRedNodes(x.left) + countRedNodes(x.right);
    	} else {
    		return countRedNodes(x.left) + countRedNodes(x.right);
    	}

    }

    public static void main(String[] args) {

    	Random random = new Random();

    	int trials = 100, N = 1000000, r;
    	double redCount = 0.0;

    	for (int i = 0; i < trials; i++) {
    		RBTree<Integer, String> rbt = new RBTree();

    		for (int j = 0; j < N; j++) {
    			r = random.nextInt(100);
                
    			rbt.put(r, "A");
    		}
            //System.out.println(rbt.size);
    		redCount += rbt.countRedNodes();
    	}
    	redCount /= trials;
        System.out.println(redCount);
    	//System.out.println("When N = " + N + ", Red percentage: " + redCount);
    }
}




