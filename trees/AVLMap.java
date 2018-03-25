package trees;

/**
 * Created by harry on 24/03/2018.
 */

public class AVLMap<Key extends Comparable<Key>, Value> extends BSTMap<Key, Value> {
    private Node root;


    @Override
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

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;

        node = rotate(node);

        return node;
    }


    @Override
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
            if(node.right == null) {
                return node.left;
            }

            if(node.left == null) {
                return node.right;
            }

            Node successor = min(node.right);
            successor.right = removeMin(node.right);

            successor.left = node.left;

            node = successor;
        }

        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        node = rotate(node);

        return node;
    }

    private Node rotate(Node node) {
        int balanceFactor = balanceFactor(node);

        if(balanceFactor > 1 && balanceFactor(node.left) > 0) {
            return rightRotation(node);
        }

        if(balanceFactor > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        if(balanceFactor < -1 && balanceFactor(node.right) < 0) {
            return leftRotation(node);
        }

        if(balanceFactor < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    private Node removeMin(Node node) {
        if(node == null) return null;

        if(node.left == null) return node.right; // it doesn't have left

        node.left = removeMin(node.left);

        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    private Node leftRotation(Node root) {
        //System.out.println("Left rotation");
        Node newRoot = root.right;
        root.right = newRoot.left;

        newRoot.left = root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;


        root.size = size(root.left) + size(root.right) + 1;
        newRoot.size = size(newRoot.left) + size(newRoot.right) + 1;


        return newRoot;
    }

    private Node rightRotation(Node root) {
        //System.out.println("Right rotation");
        Node newRoot = root.left;
        root.left = newRoot.right;

        newRoot.right = root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;


        root.size = size(root.left) + size(root.right) + 1;
        newRoot.size = size(newRoot.left) + size(newRoot.right) + 1;

        return newRoot;
    }


    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private int balanceFactor(Node node) {
        if(node == null) return 0;

        return height(node.left) - height(node.right);
    }
}
