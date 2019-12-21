package a8_Tree;

import java.util.ArrayList;


public class A4_Huffman {
    public static void main(String[] args) {
        int[] arr1 = {13,7,8,3,29,6,1};
        Node head = huffmanTree(arr1);

        head.PreList();
    }

    public static Node huffmanTree(int[] arr){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size() > 1) {
            nodes.sort(((o1, o2) -> o1.getValue() - o2.getValue()));
            System.out.println(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node mid = new Node(left.getValue() + right.getValue());
            nodes.add(mid);
            mid.setLeft(nodes.get(0));
            mid.setRight(nodes.get(1));

            nodes.remove(0);
            nodes.remove(0);
        }

        return nodes.get(0);
    }
}

class Node{
    private Node left;
    private Node right;
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void PreList(){
        System.out.println(this);
        Node left = this.left;
        if (left != null){
            left.PreList();
        }
        if (this.right != null){
            this.right.PreList();
        }
    }
}