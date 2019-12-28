package a10_Graph;

/*  搜索步： 搜索的任务是给出出发位置和目标位置，要求找到一条到达目标的路径
    > 1. 获得当前位置上，有几条路可供选择
    > 2. 根绝选择策略，选择其中一条路，走到下个位置

    DFS:一条路走到黑，不同则回溯至上一个路口选择其他路，直到找到目标。
        即使成功也不一定是一条好路，但好处是需要记住的位置比较少。
    BFS:从初始位置出发，尝试走过所有能 一步、二步、三步··· ···到达的所有位置，直到找到目标。
        一定能找到一条最短路径，但需要记忆的内容非常多
 */
public class A3_Comparation {
    public static void main(String[] args) {
        String vertexs[] = {"1","2","3","4","5","6","7","8"};
        A1_Graph graph = new A1_Graph(vertexs.length);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        graph.show();
        System.out.println("深度优先");
        graph.dfs(0);
        System.out.println();

        System.out.println("广度优先");
        graph.bfs(0);
        System.out.println();

    }
}
