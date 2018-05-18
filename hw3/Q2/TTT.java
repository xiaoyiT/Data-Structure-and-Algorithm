import java.util.*;
import java.util.Random;

public class TTT<Key extends Comparable<Key>, Value> {

	private static boolean RED = true;
    private static boolean BLACK = false;

	private Node root;
	private int size;

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

	private boolean isTwoNode(Node node) {
		return node.right == null;
	}

	private void put(Key key, Value value) {
		if (key == null || value == null) {
			return;
		}
		root = insert(root, key, value);
		root.color = BLACK;
	}

	private Node insert(Node h, Key key, Value value) {
		if (h == null) {
            size++;
        	return new Node(key, value, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
        	h.left = insert(h.left, key, value);
        	if (h.color == RED || (h.right != null && h.right.color == RED)) {
        		h.left.color = BLACK;
        	}
        } else if (cmp > 0) {
        	h.right = insert(h.right, key, value);
        	if (h.color == RED || (h.left != null && h.left.color == RED)) {
        		h.right.color = BLACK;
        	}
        } else {
        	h.value = value;
        }

        return h;
	}

	private int get(Key key) {
        int result = 0;
		Node cur = root;
        while (cur != null) {
            if (cur.color != RED) {
                result++;
            }
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

	// private boolean contains(Key key) {
	// 	return get(key) != null;
	// }

	private boolean isEmpty() {
		return root == null;
	}

	private int size() {
		return size(root);
	}

	private int size(Node node) {
		if(node == null) {
			return 0;
		}

		return size(node.left) + size(node.right) + 1;
	}

	private void delete(Key key) {
		if (!isEmpty()){
            root.color = RED;

            root = delete(key, root);

            if (!isEmpty())
                root.color = BLACK;
        }
	}

	private Node delete(Key key, Node node) {
		if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = delete(key, node.left);

        }else if (cmp > 0) {
            node.right = delete(key, node.right);

        }else {
            size--;
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node x = min(node.right);
            node.key = x.key;
            node.value = x.value;

            node = delete(x.key, node);
        }

        return node;
	}

	private Node min(Node node) {
        if(node == null) {
            return null;
        } else {
            while(node.left != null) {
                node = node.left;
            }

            return node;
        }
    }

    public static void main(String[] args) {
    	int trials = 100, N = 8192, a;
        Random random = new Random();
        TTT<Integer, String> ttt = new TTT();

        int sum = 0;
        for (int i = 0; i < trials; i++) {
            for(int j = 0; j < N; j++) {
                a = random.nextInt(N);
                ttt.put(a, "A");
            }
            a = random.nextInt(N);
            sum += ttt.get(a);
        }

        double avgPath = (double)sum / (double)trials;
        //sSystem.out.print("VALID" + "\n");
        System.out.print("Random, N is: " + N + ", avgPath is: " + avgPath + "\n");

        TTT<Integer, String> tttSort = new TTT();

        sum = 0;
        for (int i = 0; i < trials; i++) {
            for (int j = 0; j < N; j++) {
                tttSort.put(j, "a");
            }
            a = random.nextInt(N);
            sum += tttSort.get(a);
        }

        double avgPathSort = (double)sum / (double)trials;

        System.out.println("Sorted, N is: " + N + ", avgPathSort is: " + avgPathSort);

    }


}