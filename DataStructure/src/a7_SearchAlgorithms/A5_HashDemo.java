package a7_SearchAlgorithms;

import java.util.Objects;
import java.util.Scanner;

public class A5_HashDemo {
    public static void main(String[] args) {
        A5_HashDemo demo = new A5_HashDemo();
        A5_HashDemo.HashDemo hashDemo = demo.new HashDemo(7);

        // 交互文字
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add  : 添加雇员");
            System.out.println("list : 显示雇员");
            System.out.println("find : 查找雇员");
            System.out.println("exit : 退出系统");
            System.out.print("请输入：");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.print("输入id:");
                    int id = scanner.nextInt();
                    System.out.print("输入名字：");
                    String name = scanner.next();
                    hashDemo.add(demo.new Emp(id,name));
                    System.out.println("雇员：id=" + id +",name=" + name + "添加成功!");
                    break;
                case "list":
                    hashDemo.list();
                    break;
                case "find":
                    System.out.print("请输入要查找的ID:");
                    id = scanner.nextInt();
                    hashDemo.findEmp(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("------输入错误------");
                    break;
            }
        }
    }

    // 需要存放的元素
    class Emp {
        private int id;
        private String name;
        private Emp next;

        public Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Emp getNext() {
            return next;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNext(Emp next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Emp{" +
                    "id=" + id +
                    ", name='" + name +
                    "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Emp emp = (Emp) o;
            return id == emp.id &&
                    Objects.equals(name, emp.name) &&
                    Objects.equals(next, emp.next);
        }
    }

    // 管理元素的链表
    class EmpLinkedList{
        private Emp head;

        public void add(Emp emp){
            if (head == null){
                head = emp;
            }else
            {
                Emp temp = head;
                while (temp.next != null){
                    temp = temp.next;
                }
                temp.next = emp;
            }
        }

        public void list(int index){
            if (head==null) {
                System.out.println(index + " 链表信息为空");
                return;
            }
            System.out.print(index + " 链表信息为：");
            Emp temp = head;
            while (temp != null){
                System.out.print("=> " + temp + "\t");
                temp = temp.next;
            }
            System.out.println();
        }

        public Emp findEmp(int id){
            if (head != null){
                Emp emp = head;
                while (emp != null){
                    if (emp.id == id){
                        return emp;
                    }
                    emp = emp.next;
                }
            }
            return null;
        }
    }

    // Hash表管理链表
    class HashDemo{
        private EmpLinkedList[] list;
        private int size;

        public HashDemo(int size) {
            this.size = size;
            // 数组默认为空啊
            list = new EmpLinkedList[size];
            for (int i = 0; i < size; i++) {
                list[i] = new EmpLinkedList();
            }
        }

        private int hashFun(Emp emp){
            return emp.id % size;
        }
        public void add(Emp emp){
            int index = hashFun(emp);
            list[index].add(emp);
        }

        public void list(){
            for (int i = 0; i < size; i++) {
                list[i].list(i);
            }
            System.out.println("-----以上为所有雇员信息-----");
        }

        public void findEmp(int id){
            int index = hashFun(new Emp(id,null));
            Emp emp = list[index].findEmp(id);
            if (emp == null){
                System.out.println("没有满足条件的雇员");
            }else {
                System.out.println("在链表 " + index + " 中找到雇员信息：" + emp.toString());
            }
        }
    }
}


