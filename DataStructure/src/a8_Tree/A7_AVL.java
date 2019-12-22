package a8_Tree;

/*
    平衡二叉树(Self-balancing Binary Search Tree): AVL树，保证查询效率较高
    > 特点：它是一颗空树 或者 它的左右两个子树的高度差的绝对值 <= 1，并且左右两个子树都是平衡二叉树
    > 常用算法： 红黑树、AVL、替罪羊树、Treap、伸展树
 */


import javax.swing.*;

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

        int[] arr2 = {10,12,8,9,7,6};
        AVLTree avlTree2 = new AVLTree();
        for (int i : arr1) {
            avlTree2.add(new Node(i));
        }
        System.out.println("arr2中序遍历：");
        avlTree2.infixList();
        System.out.println("arr2树的高度：" + avlTree2.getRoot().height());

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
        // 1. 设为原节点值
        Node node = new Node(root.getValue());
        // 2. 连接原左子节点
        node.setLeft(root.getLeft());
        // 3. 连接原节点右子节点的左子节点，即将＜新根节点的左节点移动至旋转原根节点的右侧较大值
        node.setRight(root.getRight().getLeft());
        // 4. 更新根节点为 右子节点
        root.setValue(root.getRight().getValue());
        // 5. 更新根节点的左子节点为 新节点
        root.setLeft(node);
        // 6. 跟新根节点的右子节点为 其原所属的右子节点
        root.setRight(root.getRight().getRight());
    }
    // 右旋转
    private void rightAVL(Node root) {
        Node node = new Node(root.getValue());
        node.setRight(root.getRight());
        node.setLeft(root.getLeft().getRight());

        root.setValue(root.getLeft().getValue());
        root.setLeft(root.getLeft().getLeft());
        root.setRight(node);
    }

    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
            int dis = root.leftHeight() - root.rightHeight();
            // 进行左旋转
            if (dis < -1){
                leftAVL(root);
            // 进行右旋转
            }else if (dis > 1) {
                rightAVL(root);
            }
        }
    }

    private Node search(int value){
        if (root != null) {
            return root.search(value);
        }
        return null;
    }

    // 找到 非叶子节点的右侧子节点的最小值
    private int getRightedMin(Node node){
        if (node.getLeft() != null){
            return getRightedMin(node.getLeft());
        }else {
            return node.getValue();
        }
    }

    // 删除节点： 1 叶子节点(仅根节点)    2 非叶子节点+1个其子节点(根节点)    3 非叶子几点+2个其子节点
    public Node remove(int value) {
        Node target = search(value);
        Node parent = null;
        if (target != null) {
            // 0.待删除的不是根节点则寻找 待删除节点的父节点
            if (root.getValue() != value) parent = root.searchParent(value);
            // 1. 删叶子节点
            if (target.getLeft() == null && target.getRight() == null) {
                // 1.1 删的是根节点
                if (parent == null) {
                    root = null;
                    // 1.2 删的不是根节点
                } else {
                    // 删父节点左子节点
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(null);
                        // 删父节点右子节点
                    } else {
                        parent.setRight(null);
                    }
                }
                // 3. 非叶子节点+2个其子节点
            } else if (target.getLeft() != null && target.getRight() != null) {
                int temp = getRightedMin(target.getRight());
                remove(temp);
                target.setValue(temp);

                // 2.非叶子节点+1个其子节点
            } else {
                // 2.1删根节点
                if (parent == null) {
                    root = root.getLeft() != null ? root.getLeft() : root.getRight();
                    // 2.2非根节点
                } else {
                    // 删父节点的 左子节点
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(target.getLeft() != null ? target.getLeft() : target.getRight());
                        // 删父节点的 右子节点
                    } else {
                        parent.setRight(target.getLeft() != null ? target.getLeft() : target.getRight());
                    }
                }
            }
        }
        return parent;
    }
}
