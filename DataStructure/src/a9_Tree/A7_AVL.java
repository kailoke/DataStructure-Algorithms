package a9_Tree;

/*
    平衡二叉树(Self-balancing Binary Search Tree): AVL树，平衡根节点的左右子树高度，保证查询效率较高
    > 特点：它是一颗空树 或者 它的"左右两个子树"的高度差的绝对值 <= 1，并且左右两个子树都是平衡二叉树
    > 常用算法： 红黑树、AVL、替罪羊树、Treap、伸展树
    > 旋转： 将原根节赋给创建的新节点作为 新根节点(原根节点的子节点) 的子节点
        > 左旋转：原根节点作为 新根节点的 新左子节点，新根节点的原左子节点作为 新左节点的右子节点
        > 右旋转：原根节点作为 新根节点的 新右子节点，新根节点的原右子节点作为 新右节点的左子节点
        > 双旋转：符合单旋转条件时，某子树的 中间高度太高，导致旋转后某子树中间高度依然不变
                > 先对这个根节点的 较高高度子树进行 反向旋转
                > 再对根节点进行 原旋转
    > 树的高度:
        > 当前节点数的高度 : Math.Max(this.left == null? 0 : this.left.height,
                                    this.right == null ? 0 : this.right.height) + 1
 */


public class A7_AVL {
    public static void main(String[] args) {
        int[] arr1 = {4,3,6,5,7,8};
        AVLTree avlTree1 = new AVLTree();
        for (int i : arr1) {
            avlTree1.add(new Node(i));
        }
        System.out.println("arr1中序遍历：");
        avlTree1.infixList();
        System.out.println("arr1树的高度：" + avlTree1.getRoot().height());
        System.out.println("arr1树的左子树高度" + avlTree1.getRoot().leftHeight());
        System.out.println("arr1树的右子树高度" + avlTree1.getRoot().rightHeight());

        int[] arr2 = {10,12,8,9,7,6};
        AVLTree avlTree2 = new AVLTree();
        for (int i : arr2) {
            avlTree2.add(new Node(i));
        }
        System.out.println("arr2中序遍历：");
        avlTree2.infixList();
        System.out.println("arr2树的高度：" + avlTree2.getRoot().height());
        System.out.println("arr2树的左子树高度" + avlTree2.getRoot().leftHeight());
        System.out.println("arr2树的右子树高度" + avlTree2.getRoot().rightHeight());

         int[] arr3 = {10,11,7,6,8,9};
        AVLTree avlTree3 = new AVLTree();
        for (int i = 0; i < arr3.length; i++) {
            avlTree3.add(new Node(arr3[i]));
        }
        System.out.println("arr3中序遍历：");
        avlTree3.infixList();
        System.out.println("arr3树的高度：" + avlTree3.getRoot().height());
        System.out.println("arr3树的左子树高度" + avlTree3.getRoot().leftHeight());
        System.out.println("arr3树的右子树高度" + avlTree3.getRoot().rightHeight());
        System.out.println(avlTree3.getRoot());
        System.out.println(avlTree3.getRoot().getLeft());

    }
}

class AVLTree{
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
    // 左旋转
    private void leftAVL(Node root) {
        // 创建新节点将原根节点向左侧旋转
        // 1. 复制原根节点
        Node node = new Node(root.getValue());
        // 2. 连接原根节点左子节点 至 左侧
        node.setLeft(root.getLeft());
        // 3. 连接原根节点的右子节点的左子节点 至 右侧
        node.setRight(root.getRight().getLeft());
        // 4. 更新根节点为 原根节点的右子节点
        root.setValue(root.getRight().getValue());
        // 5. 连接原根节点为 新根节点的左子节点
        root.setLeft(node);
        // 6. 将新根节点的 原所属右子节点 连接
        root.setRight(root.getRight().getRight());
    }
    // 右旋转
    private void rightAVL(Node root) {
        // 创建新节点将原根节点向右侧旋转
        // 1. 复制原根节点
        Node node = new Node(root.getValue());
        // 2. 连接原根节点右子节点 至 右侧
        node.setRight(root.getRight());
        // 3. 连接原根节点左子节点的右子节点 至 左侧
        node.setLeft(root.getLeft().getRight());
        // 4. 更新根节点为 原根节点的左子节点
        root.setValue(root.getLeft().getValue());
        // 5. 连接原根节点的 为 新根节点的右子节点
        root.setRight(node);
        // 6. 将新根节点的 原所属左子节点 连接
        root.setLeft(root.getLeft().getLeft());
    }

    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
            this.AVL();
        }
    }

    private void AVL() {
        int dis = root.leftHeight() - root.rightHeight();
        // 左子树高度低，进行左旋转
        if (dis < -1){
            // 左旋转时，右子树的左子树高度更高，即中间子树高度更高，则需要进行双旋转
            // 子树的左右子节点已经在左右高度方法中判断 null 值
            if (root.getRight().leftHeight() > root.getRight().rightHeight()){
                System.out.println("---右子节点右旋转---");
                rightAVL(root.getRight());
            }
            System.out.println("~~~~~进行左旋转~~~~~");
            leftAVL(root);
            // 右子树高度低，进行右旋转
        }else if (dis > 1) {
            // 中间子树长，导致单旋转后 中间子树仍然过长，故需进行双旋转
            if (root.getLeft().leftHeight() < root.getLeft().rightHeight()){
                System.out.println("---左子节点左旋转---");
                leftAVL(root.getLeft());
            }
            System.out.println("~~~~~右旋转~~~~~");
            rightAVL(root);
        }
    }
}
