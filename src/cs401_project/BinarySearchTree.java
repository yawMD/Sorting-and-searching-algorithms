package cs401_project;

import java.util.Stack;

public class BinarySearchTree {

    // Node class for BST nodes
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    
    // Methods to insert a new key
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        // Return the unchanged node pointer
        return root;
    }

    // Method to calculate maximum depth of the BST
    int maxDepth(Node node) {
        if (node == null)
            return 0;
        else {
            // Compute the depth of each subtree
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            // Use the larger one
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }

    // Method to calculate size of the BST recursively
    int sizeRecursive(Node node) {
        if (node == null)
            return 0;
        else
            return(sizeRecursive(node.left) + 1 + sizeRecursive(node.right));
    }

    // Method to calculate size of the BST iteratively
    int sizeIterative(Node node) {
        if (node == null)
            return 0;

        int size = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            size++;

            if (current.left != null)
                stack.push(current.left);

            if (current.right != null)
                stack.push(current.right);
        }

        return size;
    }

    // This method mainly calls InorderRec()
    void inOrder()  {
        inOrderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.key + " ");
            inOrderRec(root.right);
        }
    }

    // This method mainly calls PreorderRec()
    void preOrder() {
        preOrderRec(root);
    }

    // A utility function to do preorder traversal of BST
    void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // This method mainly calls PostorderRec()
    void postOrder() {
        postOrderRec(root);
    }

    // A utility function to do postorder traversal of BST
    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}
