package a8_Tree;

public class A6_BinarySortTree {
    public static void main(String[] args) {
        // preOrder: 1 3 5 7 9 10 12
        int[] arr1 = {7,3,10,12,5,1,9};
        BinarySortTree tree = new BinarySortTree();
        for (int i : arr1) {
            tree.add(new Node(i));
        }
        System.out.println("BinarySortTree infixOrder : ");
        tree.infixList();

        System.out.println("finded : " + tree.search(1));

    }
}

class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    void infixList(){
        if (root == null){
            System.out.println("...");
        }else {
            root.infixList();
        }
    }

    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    Node search(int value){
        if (root != null) {
            return root.search(value);
        }
        return null;
    }
    // 删除节点： 1 叶子节点(仅根节点)    2 非叶子节点+1个其子节点(根节点)    3 非叶子几点+2个其子节点
    public Node remove(int value){



        return null;
    }
}
