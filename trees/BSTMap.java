package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by harry on 23/03/2018.
 */

public class BSTMap<Key extends Comparable<Key>, Value> extends TreeMap<Key, Value> {
    protected Node root;

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    @Override
    public void remove(Key key) {
        root = remove(root, key);
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Key min() {
        Node node = min(root);
        return node == null ? null : node.key;
    }

    @Override
    public Key max() {
        Node node = max(root);
        return node == null ? null : node.key;
    }

    @Override
    public Key floor(Key key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public Key ceil(Key key) {
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    protected Value get(Node node, Key key) {
        if(node == null) return null;

        int cmp = compare(key, node.key);

        if(cmp > 0) {
            return get(node.right, key);
        }
        else if(cmp < 0) {
            return get(node.left, key);
        }

        return node.value;
    }

    protected Node put(Node node, Key key, Value value) {
        if(node == null) return new Node(key, value);

        int cmp = compare(key, node.key);

        if(cmp > 0) {
            node.right = put(node.right, key, value);
        }
        else if(cmp < 0) {
            node.left = put(node.left, key, value);
        }
        else {
            node.value = value;
        }

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    protected Node remove(Node node, Key key) {
        if(node == null) return null;

        int cmp = compare(key, node.key);

        if(cmp > 0) {
            node.right = remove(node.right, key);
        }
        else if(cmp < 0) {
            node.left = remove(node.left, key);
        }
        else {
            if(node.left == null) {
                return node.right;
            }

            if(node.right == null) {
                return node.left;
            }

            Node currentNode = node.right;
            Node successor = currentNode;

            while(currentNode.left != null) {
                if(currentNode.left.left == null) {
                    successor = currentNode.left;
                    currentNode.left = null; // this breaks the loop
                    currentNode.size--;
                }
                else {
                    currentNode = currentNode.left;
                }
            }

            if(node.right != successor) {
                successor.right = node.right;
            }

            successor.left = node.left;

            node = successor;
        }

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    protected Node min(Node node) {
        if(node == null) {
            return null;
        }

        if(node.left != null) {
            return min(node.left);
        }

        return node;
    }

    protected Node max(Node node) {
        if(node == null) {
            return null;
        }

        if(node.right != null) {
            return max(node.right);
        }

        return node;
    }


    protected Node floor(Node node, Key key) {
        if(node == null) {
            return null;
        }

        int cmp = compare(key, node.key);

        if(cmp == 0) {
            return node;
        }
        else if(cmp < 0) { // it must be on the left
            return floor(node.left, key);
        }

        Node right = floor(node.right, key);

        return right != null ? right : node;
    }

    protected Node ceil(Node node, Key key) {
        if(node == null) {
            return null;
        }

        int cmp = compare(key, node.key);

        if(cmp == 0) {
            return node;
        }
        else if(cmp > 0) {
            return ceil(node.right, key);
        }

        Node left = ceil(node.left, key);

        return left != null ? left : node;
    }

    private int size(Node node) {
        return node != null ? node.size : 0;
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();

        if(root != null) queue.add(root);

        int currentLevel = 1;

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if(currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if(currentNode.right != null) {
                queue.add(currentNode.right);
            }

            System.out.print(currentNode.value + " ");
        }
    }

    protected class Node {
        protected Key key;
        protected Value value;

        protected Node left;
        protected Node right;

        protected int size;
        protected int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }
}
