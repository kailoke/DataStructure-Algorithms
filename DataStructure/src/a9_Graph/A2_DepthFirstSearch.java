package a9_Graph;

/*
  一、图的深度优先搜索 : Depth First Search

    1.深度优先遍历： 首先访问初始节点的第一个邻接节点，然后再以这个邻接节点作为初始节点，递归访问
     > 每次都在访问当前节点的后，首先访问当前节点的第一个邻接节点
    2.优先往纵向挖掘深入，而不是对一个节点的所有邻接节点进行横向访问。

  二、图的深度优先遍历：
    > 1.
 */
public class A2_DepthFirstSearch {
    public static void main(String[] args) {
        int vertexNum = 5;
        String[] vertexs = {"A","B","C","D","E"};
        // 创建图对象
        A1_Graph graph = new A1_Graph(vertexNum);
        // 插入所有顶点
        for (String vertex : vertexs){
            graph.insertVertex(vertex);
        }
        // 添加边
        graph.insertEdge(0,1,1);    // A-B
        graph.insertEdge(0,2,1);    // A-C
        graph.insertEdge(1,2,1);    // B-C
        graph.insertEdge(1,3,1);    // B-D
        graph.insertEdge(1,4,1);    // B-E
        // 邻接矩阵
        graph.show();
    }
}
