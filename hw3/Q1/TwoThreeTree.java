import java.util.*;
import java.util.Random;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {

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
        	if (h.color == RED || (h.left != null && sh.left.color == RED)) {
        		h.right.color = BLACK;
        	}
        } else {
        	h.value = value;
        }

        return h;
	}

	private Value get(Key key) {
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

    private Value min() {
        return root == null ? null : min(root).value;
    }

    public Value max() {
        return root == null ? null : max(root).value;
    }

    private Node max(Node node) {
        if(node == null) {
            return null;
        } else {
            while(node.right != null) {
                node = node.right;
            }

            return node;
        }
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

	private boolean contains(Key key) {
		return get(key) != null;
	}

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
    	int num = 10, a;
        Random random = new Random();
        TwoThreeTree<Integer, String> ttt = new TwoThreeTree();

        for (int i = 1; i <= num; ++i) {
            a = random.nextInt(50000);
            ttt.put(a, "A");
        }

        for (int i = 0; i < 10; ++i) {
            ttt.delete(i);
        }


        //sSystem.out.print("VALID" + "\n");
        System.out.print("size is: " + ttt.size() + "\n");
    }


}