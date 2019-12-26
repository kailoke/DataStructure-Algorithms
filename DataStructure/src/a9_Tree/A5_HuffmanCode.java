package a9_Tree;

import org.jetbrains.annotations.NotNull;
import java.util.*;

/*
    HuffmanTree: 赫夫曼树是带权路径长度(WPL)最小的树，权值较大的节点离根节点较近
    > 路径 ： 从A节点到B节点经过的通路
    > 路径长度 ： 从根节点到目标节点经过的分支数目。
    > 节点的权 ： 节点赋值
    > 带权路径长度 ： 路径长度 * 节点的权
    > 树的带权路径长度 WPL(Weight Path Length) : 所有叶子节点的带权路径长度和
 */

public class A5_HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        // 1.获得byte[] 数组   字节数组
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);

        // 2.将byte[] 统计生成Nodes (huffmanTree的生存一句)
        List<CodeNode> nodes = getNodes(contentBytes);
        System.out.println("NodeList:");
        System.out.println(nodes.toString());

        // 3.构建 HuffmanTree
        CodeNode huffmanTree = creatHuffmanTree(nodes);
        System.out.println("HuffmanTree PreOrder:");
        huffmanTree.preOrder();

        // 4.创建huffman 值:权重 映射表
        Map<Byte, String> huffmanCode = huffmanCode(huffmanTree);
        System.out.println("Huffman编码表：" + huffmanCode.toString());

        // 5. 根据huffman编码表，压缩原字节数组
        byte[] zipCodes = zip(contentBytes, huffmanCode);
        System.out.println("zip后的字节数组 : " + Arrays.toString(zipCodes));
    }

    // 将字节数组 生成节点列表
    private static List<CodeNode> getNodes(byte[] bytes){
        ArrayList<CodeNode> list = new ArrayList<>();
        // 使用map 来统计单位数量
        HashMap<Byte,Integer> map = new HashMap<>();
        for (Byte b : bytes) {
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
        if (node == null){
            return null;
        }
        HashMap<Byte, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        getCodes(node, map, sb);

        return map;
    }
    // 左子节点 0   右子节点 1
    private static void getCodes(CodeNode node,Map<Byte,String> map,StringBuilder sb){
        StringBuilder mySB = new StringBuilder(sb);
        if (node.data != null){
            map.put(node.data,mySB.toString());
        }
        if (node.left != null){
            mySB.append(0);
            getCodes(node.left, map, mySB);
        }
        // 若成功左递归，则需要重置mySB，去除左递归的修改
        mySB = new StringBuilder(sb);
        if (node.right != null){
            mySB.append(1);
            getCodes(node.right, map, mySB);
        }
    }

    // 根据huffman编码表，压缩原字节数组
    private static byte[] zip(byte[] src, Map<Byte,String> huffmanCodes){
        StringBuilder sb = new StringBuilder();
        for (Byte b : src){
            sb.append(huffmanCodes.get(b));
        }
        System.out.println("压缩后的bit数: " + sb.toString().length());
        // 将bit占位的字符串 转成 bit 数组
        int len = (sb.length() + 7) / 8;
        byte[] zip = new byte[len];
        int count = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i+8>sb.length()) {
                strByte = sb.substring(i,sb.length());
            }else {
                strByte = sb.substring(i,i+8);
            }
            zip[count++]  = (byte) Integer.parseInt(strByte, 2);
        }

        return zip;
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
