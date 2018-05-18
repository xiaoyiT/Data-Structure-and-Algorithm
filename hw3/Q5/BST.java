import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BST<Key extends Comparable<Key>, Value> {

	private static boolean RED = true;
    private static boolean BLACK = false;

	Node root;
	int size;

	class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private boolean color;

		public Node(Key key, Value value, boolean color) {
			this.key = key;
		    this.value = value;
		    this.color = color;
	    }
	}

	public boolean IsRed(Node node) {
        if (node == null) {
        	return false;
        }
        return node.color == RED;
    }

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

	public Value get(Key key) {
        Node cur = root;
        while (cur != null) {
            //compare given key to the key of current node
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return cur.value;
            } else if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return null;
    }

	private int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node h, Key key) {
		if (root == null) {
			return 0;
		}

		int cmp = key.compareTo(h.key);
		if (cmp < 0) {
			return rank(h.left, key);
		} else if (cmp > 0) {
			return rank(h.right, key) + size(h.left) + 1;
		} else {
			return size(h.left);
		}
	}

	private int size(Node h) {
		if(h == null) {
			return 0;
		}
		return size(h.left) + size(h.right) + 1;
	}

	private Key select(int k) {
		if (size(root) < k) {
			return null;
		}

		return select(root, k);
	}

	private Key select(Node node, int k) {
		int r = rank(node.key);
		if (r == k) {
			return node.key;
		} else if (r < k) {
			return select(node.right, k);
		} else {
			return select(node.left, k);
		}
	}

	public boolean isRBTree() {
        return isRBTree(root);
    }

    public boolean isRBTree(Node node) {
        if(node == null) {
            return true;
        } else if(node.color == RED) {
            return false;
        } else {
            Node x = node;
            int count = 0;

            for(; x != null; x = x.left) {
                if(x.color == BLACK) {
                    ++count;
                }
            }

            return isRBTree(node, count, 0);
        }
    }

    private boolean isRBTree(Node node, int count, int k) {
        if(node == null) {
            return count == k;
        } else if((IsRed(node.left) && IsRed(node.left.left))
                ||(IsRed(node.left) && IsRed(node.left.right))
                ||(IsRed(node.right) && IsRed(node.right.right))
                ||(IsRed(node.right) && IsRed(node.right.left))) {
            return false;
        } else {
            if(node.color == BLACK) {
                ++k;
            }
            return node.left == null && node.right == null ? k == count:isRBTree(node.left, count, k) && isRBTree(node.right, count, k);
        }
    }

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST();
		List<Integer> data = new ArrayList();
		//read data from .txt file
		try {
		    File file = new File("select-data.txt");
		    Scanner sc = new Scanner(file);

		    while (sc.hasNextLine()) {
			    data.add(Integer.valueOf(sc.nextLine()));
		    }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		int len = data.size();
		System.out.println("len is: " + len);
		for (int i = 0; i < len; i++) {
			bst.put(data.get(i), "A");
		}

		// int rankValue = bst.rank(7);
		// Key selectKey = bst.select(7);

		System.out.println("Value of rank(7) is: " + bst.rank(7) + "\n");
		System.out.println("value of select(7) is: " + bst.select(7) + "\n");

	}


}