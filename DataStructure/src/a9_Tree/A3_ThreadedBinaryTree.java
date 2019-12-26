package a9_Tree;

/*
    线索化二叉树
    > 1. 每个二叉树总有 n-1 个节点的指针为空，根据特定的遍历方式，利用左右空指针建立线索化
    > 2. 线索化分为：前序、中序、后序线索化
    > 3. 增加左、右节点类型字段，标识当前子节点的左右节点类型；增加前驱节点指针总是指向最新的前驱节点
    > 4. 按遍历方式线索化当前子节点
            > 中序    : 前驱节点为 前驱节点指针； 后继节点为 父类节点 (pre.setXxx) ；节点线索化后成为新的 前驱节点
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

        tree.threadedNodes(root);
        System.out.println("5号节点的前驱节点：" + node5.getLeft());
        System.out.println("5号节点的后继节点：" + node5.getRight());

        tree.threadList();

    }
}

class Gordon extends HeroNode{
    /*
    leftType == 0 :指向左子树    leftType == 1 :指向前驱节点
    rightType == 0:指向右自述    rightType == 1:指向后继节点
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

    // 对二叉树的节点 一、中序线索化
    public void threadedNodes(Gordon node){
        if (node == null){
            return;
        }
        // 1.线索化左子树
        threadedNodes((Gordon) node.getLeft());

        // 2.线索化当前节点
        if (node.getLeft() == null){
            // 2.1 修改当前节点的前驱节点
            node.setLeft(pre);
            // 2.2 标识当前节点的左指针类型
            node.setLeftType(1);
        }
            // 2.3 处理后继节点
        if (pre != null && pre.getRight() == null){
            // 前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 2.4 被线索化的节点成为新的前驱节点
        pre = node;

        // 3.线索化右子树
        threadedNodes((Gordon) node.getRight());
    }

    // 遍历线索化二叉树
    public void threadList(){
        Gordon temp = (Gordon) super.getRoot();
        while (temp != null){
            // 定位起始点
            while (temp.getLeftType() == 0){
                temp = (Gordon) temp.getLeft();
            }
            // 输出当前节点(起始点 或 子节点)
            System.out.println(temp);
            // 查找并输出后继节点
            while (temp.getRightType() == 1){
                temp = (Gordon) temp.getRight();
                System.out.println(temp);
            }
            // 正常子节点，获得右子节点
            temp = (Gordon) temp.getRight();
        }
    }
}