package me.ghonix.practice.trees;

public class CheckBST {

    static class Node {
        Integer data;
        Node left;
        Node right;
    }

    boolean checkBST(Node root) {
        return checkBST(root, null, null);
    }

    private boolean checkBST(Node n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }

        if ((min != null && n.data <= min) || (max != null && n.data > max) ) {
            return false;
        }

        return checkBST(n.left, min, n.data) && checkBST(n.right, n.data, max);
    }


    public static void main(String[] args) {
        Node root = new Node();
        root.data = 20;

        root.left = new Node();
        root.left.data = 10;

        root.left.right = new Node();
        root.left.right.data = 25;

        root.right = new Node();
        root.right.data = 30;

        System.out.println(new CheckBST().checkBST(root));
    }

}
