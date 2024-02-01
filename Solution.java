import java.util.Scanner;

class Node {
    int value;
    Node left, right;
    public int height;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class Solution {

    public static int height(Node node) {
        return (node == null) ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int balanceFactor(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    public static Node rotateRight(Node y) {
        Node x = y.left;
        Node T = x.right;

        x.right = y;
        y.left = T;

        return x;
    }

    public static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T = y.left;

        y.left = x;
        x.right = T;

        return y;
    }

    public static Node insert(Node root, int newValue) {
        if (root == null) {
            return new Node(newValue);
        }

        if (newValue < root.value) {
            root.left = insert(root.left, newValue);
        } else if (newValue > root.value) {
            root.right = insert(root.right, newValue);
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = balanceFactor(root);

        if (balance > 1 && newValue < root.left.value) {
            return rotateRight(root);
        }

        if (balance < -1 && newValue > root.right.value) {
            return rotateLeft(root);
        }

        if (balance > 1 && newValue > root.left.value) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (balance < -1 && newValue < root.right.value) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public static void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.value + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = null;

        int[] values = {3, 2, 4, 5, 6};
        for (int value : values) {
            root = insert(root, value);
        }

        System.out.println("Arvore AVL em ordem:");
        inOrderTraversal(root);

        System.out.println("\nDigite o valor a ser inserido:");
        int newValue = scanner.nextInt();

        root = insert(root, newValue);

        System.out.println("Arvore AVL após a inserção:");
        inOrderTraversal(root);

        scanner.close();
    }
}
