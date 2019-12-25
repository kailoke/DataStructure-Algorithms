package a9_Graph;

/*
  一、图的深度优先遍历：图的深度优先搜索 Depth First Search
    > 1.每次访问初始节点后，首先访问初始节点的第一个邻接节点，
    > 2.再以这个邻接节点作为初始节点，"递归"访问
    > 3.若当前节点无邻接节点，则结束当前节点的递归，并从上一个初始节点开始循环查找其的下一个邻接节点
    * 优先 纵向挖掘 深入，而不是对一个节点的所有邻接节点进行横向访问
    * 递归初始节点的第一个邻接节点，跳过已经访问的邻接节点，循环查找其他未被访问邻接节点展开深度递归

  二、图的深度优先遍历：图的广度优先搜索 Broad First Search
    > 1.每次访问初始节点后，以此节点建立动态数组
    > 2.出列动态数组头元素，访问此头元素连接的所有元素，并将被访问的元素加入动态数组
    > 3.循环出列动态数组头部元素，直到动态数组为空，结束此 初始节点的 广度优先遍历
    > 4.选择其他未被访问的初始节点继续它们的广度优先遍历
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
//        graph.insertEdge(0,1,1);    // A-B
//        graph.insertEdge(0,2,1);    // A-C
//        graph.insertEdge(1,2,1);    // B-C
//        graph.insertEdge(1,3,1);    // B-D
//        graph.insertEdge(1,4,1);    // B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,3,1);
        graph.insertEdge(3,4,1);
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
