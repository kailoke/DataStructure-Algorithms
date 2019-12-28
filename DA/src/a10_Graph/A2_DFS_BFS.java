package a10_Graph;

/* 图的遍历，即是对节点的访问。
  一、图的深度优先搜索 Depth First Search : "纵向递归"
    > 1.循环选择未被访问的节点作为初始节点，对其展开dfs递归
    > 2.访问当前节点，找到其第一个邻接节点
    > 3.当当前节点有邻接节点时，dfs递归未被访问的节点，直到(新)当前节点的所有邻接节点被访问，出栈
    * 优先 纵向挖掘 深入，而不是对一个节点的所有邻接节点进行横向访问

  二、图的广度优先搜索 Broad First Search : "分层遍历"
    > 1.循环选择未被访问的节点作为初始节点，对其进行bfs广度队列访问
    > 2.从初始节点开始，将成功访问的元素加入 广度队列
    > 3.循环出列广度队列头元素，访问此"出列元素"连接的所有元素，并将被访问的元素加入动态数组
    > 4.循环出列访问广度队列元素，直到广度队列为空，结束此初始点的广度遍历
    * 类似于分层搜索
 */
public class A2_DFS_BFS {
    public static void main(String[] args) {
        int vertexNum = 5;
        String[] vertexs = {"A","B","C","D","E"};
        A1_Graph graph = new A1_Graph(vertexNum);
        for (String vertex : vertexs){
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0,1,1);    // A-B
        graph.insertEdge(0,2,1);    // A-C
        graph.insertEdge(1,2,1);    // B-C
        graph.insertEdge(1,3,1);    // B-D
        graph.insertEdge(1,4,1);    // B-E
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(2,3,1);
//        graph.insertEdge(3,4,1);
        graph.show();

        // 一、深度优先遍历
        System.out.println("深度优先遍历：");
        graph.dfs(0);
        System.out.println();
        graph.dfs(2);
        System.out.println();
        graph.dfs(4);
        System.out.println();

        // 二、广度优先遍历
        System.out.println("广度优先遍历：");
        graph.bfs(0);
        System.out.println();
        graph.bfs(2);
        System.out.println();
        graph.bfs(4);
        System.out.println();
    }
}
