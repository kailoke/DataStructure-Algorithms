package a10_Graph;

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
