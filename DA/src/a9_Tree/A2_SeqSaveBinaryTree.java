package a9_Tree;

/*
    顺序存储二叉树: 通常只考虑"完全"二叉树，应用于堆排序；因其按顺序从上至下，从左至右顺次添加
    > 1. 根节点索引 : 0
    > 2. 第index个索引的 左子节索引 ： 2 * index + 1
    > 3. 第index个索引的 右子节索引 ： 2 * index + 2
    > 4. 第index个索引的 父节点索引 ： (index - 1) / 2
    > 5. 顺序存储二叉树因为索引是计算得到，特别注意数组越界
    数组可以转换成树，树可以存储为数组
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

    // 从根节点索引 0 开始遍历
    public void preOrder() {
        this.preOrder(0);
    }

    // 对数组的前序遍历
    private void preOrder(int index) {
        if (arr == null || arr.length == 0){
            System.out.println("数组空，不需要遍历");
        }
        System.out.print(arr[index] + "\t");
        // 当前索引的左子节点 : 2*index + 1
        if (2*index + 1 < arr.length){
            preOrder(2 * index + 1);
        }
        // 当前索引的右子节点 : 2*index + 2
        if (2*index + 2 < arr.length){
            preOrder(2 * index + 2);
        }
    }
}
