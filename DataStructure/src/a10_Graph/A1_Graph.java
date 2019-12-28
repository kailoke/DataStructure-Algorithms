package a10_Graph;

import java.util.ArrayList;
import java.util.Arrays;

/*
    ①线性表： 局限于一个直接前驱 和 一个直接后继
    ②树：只能有一个直接前驱(父节点)
    ③图：数据结构，处理数据的多对多关系

  一、图的属性：
    > 顶点 vertex : 节点
    > 边   edge   : 两个顶点的连接
    > 路径 ：顶点A->顶点D经过的路径，根据 有向图 \ 无向图 拥有唯一或多个到达路径

    有向图 : 路径有单一方向       无向图 : 路径没有方向，正反都可通过
    带权图 ：边有权值，带权值的图也称为网

  二、图的表示方式 :   表达 顶点与顶点间的<边>
    > 邻接矩阵：二维数组表示，表示N个点之间的连通/不连通关系(默认不连通)。 矩阵[][] = n * n
               由于为每个顶点都分配n个边，表示了不存在的边，会有一定的空间损失。
    > 邻接(链)表："数组"(索引是顶点编号) + "链表"(单向链表，每个值代表当前索引顶点连通到的点。
               数组索引表示顶点编号，链表只表示存在的边，不存在空间损失。
  三、图的属性：
    > 顶点数组
    > 邻接矩阵 / 邻接表
    > 边数量
 */

public class A1_Graph {
    // 顶点动态数组
    private ArrayList<String> vertexs;
    // 邻接矩阵 0不通 1连通 非0也可表示权
    private int[][] edges;
    // 边数量
    private int edgeNum;

    public A1_Graph(int n) {
        vertexs = new ArrayList<>();
        edges = new int[n][n];
//        edgeNum = 0;
    }

    // A1_Graph 图的创建
    // 图增加顶点
    public void insertVertex(String vertex) {
        vertexs.add(vertex);
    }

    /**
     * 图增加边
     * @param v1 顶点序号
     * @param v2 顶点序号
     * @param weight 边的权
     */
    public void insertEdge(int v1,int v2,int weight) {
        // 无向图
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeNum++;
    }
    // 返回节点的个数
    public int getVertexNum() {
        return vertexs.size();
    }
    // 返回边的数目
    public int getEdgeNum(){
        return edgeNum;
    }
    // 返回节点编号的数据
    private String getVertex(int i){
        return vertexs.get(i);
    }
    // 返回两个节点的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    // 显示矩阵
    public void show(){
        System.out.print("  ");
        // 将顶点一行输出
        for (String vertex : vertexs) {
            System.out.print(vertex + "  ");
        }
        System.out.println();
        // 将顶点一列输出
        for (int i = 0; i < edges.length; i++) {
            System.out.print(vertexs.get(i));
            System.out.println(Arrays.toString(edges[i]));
        }
    }

    // 深度优先遍历 : Depth First Search
    void dfs(int initialVertex){
        // 初始化vertex访问标记数组
        boolean[] isVisited = new boolean[this.getVertexNum()];
        // 1.依次选择初始节点遍历 : 因为有向图不能通过一个节点递归到所有节点
        for (int i = 0; i < this.getVertexNum(); i++) {
            // 取模使得可从任意节点开始遍历
            int vertex = initialVertex++ % this.getVertexNum();
            // 当前初始节点未被访问则开始其DFS
            if (!isVisited[vertex]){
                dfs(isVisited, vertex);
            }
        }
    }
    // 初始节点的 深度递归
    private void dfs(boolean[] isVisited, int vertex) {
        // 2.访问当前节点
        isVisited[vertex] = true;
        System.out.print(this.getVertex(vertex) + "->");
        // 3.查找当前节点的第一个邻接节点 索引
        int neighbor = getFirstNeighbor(vertex);
        // 4.若存在(未被访问的)邻接节点，则对邻接节点开始 递归 或 循环
        while (neighbor > -1){
            // 4.1 邻接节点未被访问，则展开深度递归
            if (!isVisited[neighbor]){
                dfs(isVisited, neighbor);
            }
            // 4.2 邻接节点已被访问，则循环查找其他(未被访问的)邻接节点
            neighbor = getNextNeighbor(isVisited, vertex, neighbor);
        }
    }
    // 2.获得当前节点的第一个邻接节点 索引
    private int getFirstNeighbor(int vertex) {
        for (int i = 0; i < this.vertexs.size(); i++) {
            if (this.edges[vertex][i] == 1){
                return i;
            }
        }
        return -1;
    }
    // 获得当前节点的下一个(未被访问的)邻接节点(相对于上一个邻接节点)
    private int getNextNeighbor(boolean[] isVisited, int vertex, int neighbor) {
        // 下一个i == neighbor + 1
        for (int i = neighbor + 1; i < this.vertexs.size(); i++) {
            if (this.edges[vertex][i] == 1 && !isVisited[i]){
                return i;
            }
        }
        return -1;
    }


    // 图的广度优先遍历 Broad First Search
    public void bfs(int initialVertex) {
        boolean[] isVisited = new boolean[this.getVertexNum()];
        //  1.依次选择初始节点遍历
        for (int i = 0; i < this.getVertexNum(); i++) {
            int vertex = initialVertex++ % this.getVertexNum();
            if (!isVisited[vertex]) {
                bfs(isVisited, vertex);
            }
        }
    }
    // 初始节点的 广度遍历
    private void bfs(boolean[] isVisited, int vertex){
        ArrayList<Integer> broadList = new ArrayList<>();// 也可以使用LinkedList.removeFirst() / addLast()
        // 2.访问当前节点，将当前节点 广度队列
        isVisited[vertex] = true;
        System.out.print(this.getVertex(vertex) + "->");
        broadList.add(vertex);

        // 3.若队列非空，进行队列内广度遍历
        while (!broadList.isEmpty()){
            // 3.1队列头节点出列
            int out = broadList.remove(0);
            // 3.2查找出列节点的第一个邻接节点
            int neighbor = getFirstNeighbor(out);
            while (neighbor > -1){
                // 3.3横向访问所有邻接节点，将成功访问的邻接节点加入 广度队列
                if (!isVisited[neighbor]){
                    isVisited[neighbor] = true;
                    System.out.print(this.getVertex(neighbor) + "->");
                    broadList.add(neighbor);
                }
                // 3.4查找出列节点的下一个(未被访问的)邻接节点
                neighbor = getNextNeighbor(isVisited, out, neighbor);
            }
        }
    }


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
