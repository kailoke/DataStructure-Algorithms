package a8_Tree;

import org.jetbrains.annotations.NotNull;
import java.util.*;


public class A5_HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        // 1.获得byte[] 数组   字节数组   最后是以字节的方式传输，所以直接按字节的方式压缩
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);

        // 2.将byte[] 统计生成Nodes (编码表的生成依据)， 写入数据及权重
        List<CodeNode> nodes = getNodes(contentBytes);
        System.out.println(nodes.toString());

        // 3.构建 HuffmanTree
        CodeNode huffmanTree = creatHuffmanTree(nodes);
//        huffmanTree.preOrder();

        // 4.创建huffmanCode
        Map<Byte, String> huffmanCode = huffmanCode(huffmanTree);
        System.out.println(huffmanCode.toString());

        byte[] huffman;
        // 5. 压缩后的字节数组
        for (int i = 0; i < str.length(); i++) {

        }
    }

    // 将字节数组 生成节点列表
    private static List<CodeNode> getNodes(byte[] bytes){
        ArrayList<CodeNode> list = new ArrayList<>();
        // 使用map 来统计单位数量
        HashMap<Byte,Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer temp = map.get(b);
            if (temp == null){
                map.put(b,1);
            }else {
                map.put(b, temp + 1);
            }
        }
        // Node化数据
        for (Map.Entry<Byte,Integer> entry : map.entrySet()){
            list.add(new CodeNode(entry.getKey(),entry.getValue()));
        }
        return list;
    }

    // 创建HuffmanTree
    private static CodeNode creatHuffmanTree(List<CodeNode> list){
        while (list.size() > 1){
            Collections.sort(list);
            CodeNode newNode = new CodeNode(list.get(0).weight + list.get(1).weight);
            newNode.left = list.get(0);
            newNode.right = list.get(1);
            list.add(newNode);

            list.remove(0);
            list.remove(0);
        }
        return list.get(0);
    }

    // 生成 霍夫曼编码表
    private static Map<Byte,String> huffmanCode(CodeNode node){
        HashMap<Byte, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        getCodes(node,map,sb);

        return map;
    }

    private static void getCodes(CodeNode node,Map<Byte,String> map,StringBuilder sb){
        StringBuilder mySB = new StringBuilder(sb);
        if (node.data != null){
            map.put(node.data,mySB.toString());
        }
        if (node.left != null){
            mySB.append(0);
            getCodes(node.left, map, mySB);
        }

        if (node.right != null){
            mySB.append(1);
            getCodes(node.right, map, mySB);
        }
    }

}


class CodeNode implements Comparable<CodeNode>{
    // 数据本身，Byte型! byte初始化=0 ; 引用则=null!
    Byte data;
    // 权值
    int weight;
    CodeNode left;
    CodeNode right;

    public CodeNode(int weight) {
        this.weight = weight;
    }

    public CodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 自然排序
    @Override
    public int compareTo(@NotNull CodeNode c) {
        return this.weight - c.weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

}
