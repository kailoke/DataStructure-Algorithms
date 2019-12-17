package a8_Tree;


import jdk.swing.interop.SwingInterOpUtils;
import org.junit.validator.PublicClassValidator;

import java.awt.font.TextHitInfo;
import java.sql.PseudoColumnUsage;
import java.time.Period;

/*
    数组：通过下表访问元素非常快，增删改操作效率低，检索目标效率低
    链表：增删操作效率高，但检索目标效率低(改需要先检索)
    树：提高检索速度、也同时保证数据的插入、删除、修改的速度
 */
public class A1_BinarySortTree {
    public static void main(String[] args) {

    }
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

    // 中序遍历
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder(){
        if (left != null){
            left.postOrder();
        }
        if (right != null){
            right.postOrder();
        }
        System.out.println(this);
    }
}
