package a9_Tree;

import org.jetbrains.annotations.NotNull;
import java.util.*;

/*  可变字长编码(VLC)的一种，被称为最佳编码
    赫夫曼编码 ： 是一种编码方式，属于程序算法；广泛应用于数据文件压缩，压缩率 20% ~ 90%
        > 1.编码根据霍夫曼树创建，由路径左(0)右(1)变长指定叶子权值节点的“编码”。因为路径唯一，则编码前缀不会重复
        > 2.字节使用率高(权值高)的使用短编码，字节使用率低(权值小)的使用长编码
  一、数据压缩
    > 1. 将源文件转换为字节数组，字节才能用传输
    > 2. 统计字节权值(使用Map，增加key->value)，封装为NodeList
    > 3. 构建赫夫曼树
 */

public class A5_HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        // 1.获得byte[] 数组   字节数组   ?Q? 非字符串都是字节数组，以字节数组作为源
        byte[] contentBytes = str.getBytes();
        System.out.println("1.SRC的字节数组长度：" + contentBytes.length);

        // 2.将byte[] 统计权值生成NodeList (用以生成huffmanTree)
        List<CodeNode> nodes = getNodes(contentBytes);
        System.out.print("2.NodeList:");
        System.out.println(nodes.toString());

        // 3.构建 HuffmanTree
        CodeNode huffmanTree = creatHuffmanTree(nodes);
        System.out.println("3.HuffmanTree PreOrder:");
        huffmanTree.preOrder();

        // 4.创建huffman Byte:String 字节:编码 编码表    HashMap<Byte,String>
        Map<Byte, String> huffmanCode = huffmanCode(huffmanTree);
        System.out.println("4.Huffman编码表：" + huffmanCode.toString());

        // 5. 根据huffman编码表，压缩原字节数组
        byte[] zipCodes = zip(contentBytes, huffmanCode);
        System.out.println("5.zip后的字节数组 : " + Arrays.toString(zipCodes));
    }

    // 2.将字节数组 生成节点列表
    private static List<CodeNode> getNodes(byte[] bytes){
        ArrayList<CodeNode> list = new ArrayList<>();
        // 使用HashMap 统计key的频率
        HashMap<Byte,Integer> map = new HashMap<>();
        for (Byte b : bytes) {
            Integer temp = map.get(b);
            if (temp == null){
                map.put(b,1);
            }else {
                map.put(b, temp + 1);
            }
        }
        // Node封装字节
        for (Map.Entry<Byte,Integer> entry : map.entrySet()){
            list.add(new CodeNode(entry.getKey(),entry.getValue()));
        }
        return list;
    }

    // 3.创建HuffmanTree
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

    // 4.递归找到叶子节点，根据路径生成 霍夫曼编码表
    private static Map<Byte,String> huffmanCode(CodeNode node){
        if (node == null){
            return null;
        }
        // 编码表
        HashMap<Byte, String> map = new HashMap<>();
        // 编码路径缓存
        StringBuilder sb = new StringBuilder();
        // 执行编码
        huffmanCode(node, map, sb);

        return map;
    }

    /**
     * 根据HuffmanTree 规定叶子节点的前缀编码
     * @param node  当前节点
     * @param map   Byte:String 编码表
     * @param sb    拼接路径编码  左子节点 0，右子节点 1
     */
    private static void huffmanCode(CodeNode node, Map<Byte,String> map, StringBuilder sb){
        // 使压栈时的编码保持仅读，保证当前编码可被回溯
        StringBuilder mySB = new StringBuilder(sb);
        // 叶子节点，执行编码
        if (node.data != null){
            map.put(node.data,mySB.toString());
        }else {
            // 编码 + 0,向左递归
            mySB.append(0);
            huffmanCode(node.left, map, mySB);

            // !!!回溯编码!!! 非常重要，使当前栈的编码返回入栈状态
            mySB = new StringBuilder(sb);

            // 编码 + 1 ，向右递归
            mySB.append(1);
            huffmanCode(node.right, map, mySB);
        }
    }

    // 5.根据huffman编码表，获得源字节数组压缩后的字符数组，将字符数组转为字节数组 byte[]
    private static byte[] zip(byte[] src, Map<Byte,String> huffmanCodes){
        StringBuilder sb = new StringBuilder();
        for (Byte b : src){
            sb.append(huffmanCodes.get(b));
        }
        System.out.println("压缩后的bit数: " + sb.toString().length());
        // 将bit占位的字符串 转成 bit 数组
        int len = (sb.length() + 7) / 8;    // 补充最后几位不满 1 字节，保证最后几位有放入空间
        byte[] zip = new byte[len];
        int count = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            // 注意最后不满足8位的字节，当其长度超出时，截取 (i,arr.length)
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

// 霍夫曼树的节点，除权值外还需要数据项本身
class CodeNode implements Comparable<CodeNode>{
    // 数据项      初始化 Byte = null，byte = 0！！byte产生歧义
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
