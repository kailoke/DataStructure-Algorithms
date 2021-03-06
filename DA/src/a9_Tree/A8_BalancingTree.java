package a9_Tree;

/*  B树：Balanced Multi-way Tree
  一、二叉树操作效率高，但数据量过大时存在问题：
    > 需要频繁 I/O，加载外部数据到内存中，树的构建耗时 ?? 此处不明白，可以一次读取大量数据到内存中
    > 数据量过大，则会使树的高度很大，树的查找次数会增大

  二、多叉树(Multi-wayTree): 每个节点能拥有更多的子节点
    B树：通过重新组织节点(排序与拆分)，降低树的高度，并且减少 I/O 读写次数提升效率
     > 文件系统 及 数据库系统 的设计者利用 磁盘预读原理，将一个节点的大小设为等于一个页(页==4K)，这样每个节点只需要一次 I/O 就可以完全载入 ??????
     > 树的度   M : 树内所有节点度的最大值    将树的度M==1024，则1次4K读取 == 1024个4字节数据
     > 节点的度 M ：其子节点个数
   三、B树
     > 0. 所有叶子节点位于同一层；所有B树都满足此条件
     > 1. 关键字集合分布在整棵树中，任何一个关键字出现且出现在一个节点中
     > 2. 内节点的度(指针) == 关键字总数 + 1
     > 3. 搜索可能在内节点命中从而结束
     > 4. 搜索性能等价于在关键字全集内做一次二分查找
     * B树的每个节点存储 M/2 ~ M 关键字，B树的是最低使用率为 M/2

   四、B+树 : B树的变体，也是一种多路搜索树
     > 0. 所有叶子节点增加一个链指针
     > 1. 所有关键字集合分布在叶子节点的链表中(稠密索引)，且链表中的关键词有序
     > 2. 内节点的度(指针) == 关键字总数
     > 3. 不可能在非叶子节点命中，只能在叶子节点命中
     > 4. 内节点相当于是叶子节点的索引(稀疏索引)，叶子节点相当于是存储(关键字)数据的数据层
     > 5. 更适合做文件索引系统

   五、B*树 : B+树的变体，在B+树的内节点再增加指向兄弟的指针
     > 1. B*树的最低使用率为 M * 2/3
     > 2. B*树分配新节点的概率比B+树低，空间使用率更高
 */
public class A8_BalancingTree {
    public static void main(String[] args) {
    }
}
