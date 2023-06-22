/*
 * INSTRUCTION:
 *     This is a Java starting code for hw8_1.
 *     When you finish the development, download this file.
 *     Note that the current filename is "Main.java".
 *     But rename it to "Main_hw8_1.java".
 *     After that, upload the renamed file on Canvas.
 */

// Finish the head comment with Abstract, ID, Name, and Date.
/*
 * Title: Main_hw8_1.java
 * Abstract: Creating a BST and using some functions with it such as traversals(excluding inOrder), height, countLeaves, and countOneChildNodes.
 * ID: 3111
 * Name: Aaron Johnson
 * Date: 03/31/2023
 */

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST aBST = new BST();
        
        int numberOfCommands = sc.nextInt();
        sc.nextLine();
        String[] operations = new String[numberOfCommands];
        for(int i = 0; i < numberOfCommands; i++) operations[i] = sc.nextLine();
        sc.close();

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].charAt(0) == 'a' ) aBST.add(Integer.parseInt(operations[i].substring(4)));
            if (operations[i].charAt(0) == 'p' && operations[i].charAt(1) == 'o') {
                aBST.postOrder(); 
                System.out.println();
            } else if (operations[i].charAt(0) == 'p' && operations[i].charAt(1) == 'r') {
                aBST.preOrder(); 
                System.out.println();
            }
            if (operations[i].charAt(0) == 'h') System.out.println(aBST.height());
            if (operations[i].charAt(0) == 'c' && operations[i].charAt(5) == 'L') System.out.println(aBST.countLeaves());
            else if (operations[i].charAt(0) == 'c' && operations[i].charAt(5) == 'O') System.out.println(aBST.countOneChildNodes());
            
        }
    }
}

// A class to define a tree node.
class Node {
    public int data;
    public Node left;
    public Node right;

    // Constructor
    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
} 


// A class for a BST.
class BST {
    private Node root;
    
    public Node getRoot() { return root; }
    
    // Add an item to the BST
    public void add(int item) {
        Node newNode = new Node(item, null, null);

        // Empty tree
        if (root == null) root = newNode;
        else insert(newNode, root);
    }

    // Insert a new node under the subtree using recursion
    private void insert(Node newNode, Node subTree) {
        // Go to the left subtree
        if (newNode.data < subTree.data) {
            if (subTree.left == null) subTree.left = newNode;
            else insert(newNode, subTree.left);  
        } else { // Go to the right subtree 
            if(subTree.right == null) subTree.right = newNode;
            else insert(newNode, subTree.right);
        }
    }

    // Conduct all functions
    
    static boolean first;
    
    public void preOrder() { first = true; preOrder(root); }    

    public void postOrder() { first = true; postOrder(root); }   

    public int height() { return height(root); }

    public int countLeaves() { return countLeaves(root); }

    public int countOneChildNodes() { return countOneChildNodes(root); }

    private void preOrder(Node subTree) {
        if (subTree != null) {
            if (first) { System.out.print(subTree.data); first = false; }
            else if (!first) System.out.print(" "+subTree.data);
            preOrder(subTree.left);
            preOrder(subTree.right);
        }
    }

    
    private void postOrder(Node subTree) {
        if (subTree != null) {
            postOrder(subTree.left);
            postOrder(subTree.right);
            if (first) { System.out.print(subTree.data); first = false; }
            else if (!first) System.out.print(" "+subTree.data);
        }
    }

    private int height(Node subTree){
        if (subTree == null) return -1;
        else {
            int left = height(subTree.left);
            int right = height(subTree.right);
            return 1 + Math.max(left, right);
        }
    }

    private int countLeaves(Node subTree){
        if (subTree == null) return 0;
        else if (subTree.left == null && subTree.right == null) return 1;
        else return countLeaves(subTree.left) + countLeaves(subTree.right);
    }

    private int countOneChildNodes(Node subTree){
        if (subTree == null || (root.left == null && root.right == null)) return 0;
        if (subTree.left == null && subTree.right != null) return 1 + countOneChildNodes(subTree.left);
        if (subTree.left != null && subTree.right == null) return 1 + countOneChildNodes(subTree.right);
        return countOneChildNodes(subTree.left) + countOneChildNodes(subTree.right);
    }

}  // end of class BST


