package a8_Tree;

/*
    顺序存储二叉树: 只考虑"完全"二叉树， 应用于堆排序
    第n个元素的左子节点为 2 * n + 1
    第n个元素的右子节点为 2 * n + 2
    第n个元素的父节点为 (n - 1)/2
    根节点的index = 0

    数组可以转换成数，树可以存储为数组
 */
public class A2_SeqSaveBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1,3,6,8,10,14};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();

    }
}

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    // 对数组的前序遍历
    private void preOrder(int index) {
        if (arr == null || arr.length == 0){
            System.out.println("数组空，不需要遍历");
        }
        System.out.print(arr[index] + "\t");
        if (2*index + 1 < arr.length){
            preOrder(2 * index + 1);
        }
        if (2*index + 2 < arr.length){
            preOrder(2 * index + 2);
        }
    }
}
