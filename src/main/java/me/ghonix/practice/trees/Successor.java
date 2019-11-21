package me.ghonix.practice.trees;

/**
 * Find the successor in a Binary search tree given a node
 */
public class Successor {
    static class Node {
        Integer data;
        Node left;
        Node right;
        Node parent;
    }

    public Node inorderSuccessor(Node n) {
        if (n == null) {
            return null;
        }

        if (n.right != null) {
            return leftMostElement(n.right);
        } else {
            Node current = n;
            Node parent = n.parent;
            while (parent != null && parent.left != current) {
                current = parent;
                parent = current.parent;
            }
            return parent;
        }

    }

    private Node leftMostElement(Node node) {
        if (node == null) {
            return null;
        }

        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        Node root = new Node();
        root.data = 20;

        root.left = new Node();
        root.left.data = 10;
        root.left.parent = root;

        root.right = new Node();
        root.right.data = 30;
        root.right.parent = root;

        root.left.right = new Node();
        root.left.right.data = 15;
        root.left.right.parent = root.left;



        Node n = new Successor().inorderSuccessor(root.left.right);
        assert n != null;
        System.out.println(n.data);

    }


}
