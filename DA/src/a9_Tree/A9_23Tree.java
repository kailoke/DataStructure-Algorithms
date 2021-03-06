package a9_Tree;

/*
    2-3树：最简单的B树结构，由二节点 + 三节点 构成
    > 1. 2-3树所有的叶子节点都处于同一层。 (只要是B树都满足此条件)
    > 2. 二节点：有两个子节点的节点，其拥有 0个子节点 || 2个子节点
    > 3. 三节点：右三个子节点的节点，其拥有 0个子节点 || 3个子节点
     ↑↑ 2-3树每次插入完成后都要满足上述3个条件，
        若不满足则先向上拆，上层满则拆本层，拆完后也必须满足上述3个条件
    > 4. 所有节点的权都满足排序规则
 */
public class A9_23Tree {
    public static void main(String[] args) {
        int[] arr1 = {16,24,12,32,14,26,34,10,8,28,38,20};
    }
}
