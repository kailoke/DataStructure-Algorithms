package a9_Tree;

/*
    线索化二叉树 : 方便查找 叶子节点 在某种遍历条件下的"线索化前驱"和"线索化后继"节点
                  遍历时不再需要使用栈递归
    > 1. n个节点的二叉树有 n+1[2n - (n-1)] 个空指针域
         利用这些空指针域，存放叶子节点在某种遍历次序下的前驱和后继的指针(附加的指针被称为"线索")
    > 2. 线索化分为：前序(计算前驱需要用栈找父节点)、中序、后序线索化(计算后继需要用栈找)
    > 3. Node增加左、右节点类型字段，标识当前子节点的左右节点类型；
    > 4. 按遍历方式线索化当前子节点，增加 %前驱节点指针% 总是指向最新的前驱节点
            > 中序线索化 : 缓存上一个经历线索化的 节点信息
                          当前被线索化节点的 前驱节点 == 缓存的前驱节点
                          当前被线索化节点的 前驱节点的后继节点 == 当前节点

 */
public class A3_ThreadedBinaryTree {
    public static void main(String[] args) {
        Gordon root = new Gordon(1, "tom");
        Gordon node2 = new Gordon(3, "jack");
        Gordon node3 = new Gordon(6, "smith");
        Gordon node4 = new Gordon(8, "mary");
        Gordon node5 = new Gordon(10, "king");
        Gordon node6 = new Gordon(14, "dim");

        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        tree.infixThreaded(root);
        System.out.println("5号节点的前驱节点：" + node5.getLeft());
        System.out.println("5号节点的后继节点：" + node5.getRight());

        System.out.println("线索化遍历:");
        tree.threadTraverse();
    }
}

class Gordon extends HeroNode{
    /*
    leftType == 0 :指向左子树    leftType == 1 :指向前驱节点
    rightType == 0:指向右子树    rightType == 1:指向后继节点
     */
    private int leftType;
    private int rightType;

    public Gordon(int id, String name) {
        super(id, name);
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

class ThreadBinaryTree extends BinaryTree{
    // 总是指向前驱节点的指针
    private Gordon pre;

    // 一、中序线索化：按中序遍历对树进行线索化
    public void infixThreaded(Gordon node){
        if (node == null){
            return;
        }
        // 线索化左子树
        infixThreaded((Gordon) node.getLeft());

        // 1 线索化当前节点的前驱节点信息 ： 缓存的上一个经历线索化处理的节点
        if (node.getLeft() == null){
            node.setLeft(pre);
            // 修改左指针类型为 线索化指针标识
            node.setLeftType(1);
        }
        // 2 线索化前驱节点的后继节点信息 ： 当前节点
        if (pre != null && pre.getRight() == null){
            // 前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 3 经历线索化处理的节点成为 "新前驱节点"
        pre = node;

        // 线索化右子树
        infixThreaded((Gordon) node.getRight());
    }

    // 二、遍历线索化二叉树，不再需要使用栈递归
    public void threadTraverse(){
        Gordon temp = (Gordon) this.getRoot();
        // 当前节点不为空则循环遍历
        while (temp != null){
            // 1.定位起始点，找到直接左子节点
            while (temp.getLeftType() == 0){
                temp = (Gordon) temp.getLeft();
            }
            // 2.输出当前节点(起始点 或 子节点)
            System.out.println(temp);
            // 3.查找线索化后继节点并输出
            while (temp.getRightType() == 1){
                temp = (Gordon) temp.getRight();
                System.out.println(temp);
            }
            // 4.当前节点不具有线索化后继节点，则遍历其直接后继
            temp = (Gordon) temp.getRight();
        }
    }
}