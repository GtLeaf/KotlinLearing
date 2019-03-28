package Offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolutionMain {
    public static void main(String[] arhs){
        MyCollision collision = new MyCollision();
        Solution solution = new Solution();
        ListNode listNode = collision.generate(2);
//        solution.deleteNode(listNode, listNode.next);
//        solution.ReorderOddEven(new int[]{1, 2, 3, 4, 5, 6});
//        solution.spiralOrderPrint(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}});
        solution.printOrderZhiWord(new int[][]{{1,2},{3,4},{5,6}});


    }
}

class Solution{

    //-------------之字打印，开始23:16-23:35
    public void printOrderZhiWord(int[][] matrix){
        if (null == matrix){
            return;
        }

        int x = matrix.length-1;
        int y = matrix[0].length-1;
        int times = x>y ? x : y;
        boolean direct = true;
        for (int i=0; i<=times; i++){
            printZhiWord(matrix, i, direct);
            direct  = !direct;
        }

    }

    public void printZhiWord(int[][] m, int len, boolean direct){
        int curX = 0;
        int curY = 0;
        //从上往下
        if (direct){
            curX = len;
            while (curX>=0 && curY>=0){
                if (curX<m.length && curY<m[0].length){
                    System.out.println(m[curX--][curY++]);
                }
            }
        }else {//从下往上
            curY = len;
            while (curX>=0 && curY>=0){
                if (curX<m.length && curY<m[0].length){
                    System.out.println(m[curX++][curY--]);
                }
            }
        }


    }

    //-------------蛇形打印,开始:13:38--------------------
    public void spiralOrderPrint(int[][] matrix){
        if (null == matrix ){
            return;
        }
        int x1 = 0;
        int y1 = 0;
        int x2 = matrix.length-1;
        int y2 = matrix[0].length-1;
        while(x1 <= x2 && y1 <= y2){
//            printEdge(matrix, x1++, y1++, x2--, y2--);
            rotateEdge(matrix, x1++, y1++, x2--, y2--);
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++ ){
                System.out.print(matrix[j][i]+" ");
            }
            System.out.println();
        }
        System.out.println("结束");
    }

    //可改递归
    private void printEdge(int[][] m,int x1, int y1, int x2,int y2){
        if (x1 > x2 && y1 > y2){
            return;
        }
        //只有一横
        if (y1 == y2){
            for (int i=x1; i<=x2; i++){
                System.out.print(m[i][y1]+"  ");
            }
        }else if(x1 == x2){//在同一列
            for (int i=y1; i<=y2; i++){
                System.out.println(m[x1][i]);
            }
        }else {
            int curX = x1;
            int curY = y1;
            //打印上
            while (curX < x2){
                System.out.print(m[curX++][curY]+"  ");
            }
            //打印右
            while(curY < y2){
                System.out.print(m[curX][curY++]+"  ");
            }
            //打印下
            while(curX > x1){
                System.out.print(m[curX--][curY]+"  ");
            }
            //打印左
            while(curY > y1){
                System.out.print(m[curX][curY--]+"  ");
            }
        }
    }


    public void rotateEdge(int [][] m,int x1, int y1, int x2,int y2){
        int times = x2 - x1;
        for (int offset = 0; offset < times; offset++){
            int temp = m[x1+offset][y1];
            m[x1+offset][y1] = m[x1][y2-offset];
            m[x1][y2-offset] = m[x2-offset][y2];
            m[x2-offset][y2] = m[x2][y1+offset];
            m[x2][y1+offset] = temp;
        }
    }

    //----------------比较器
    public void sort(){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        List<ListNode> listNodeList = new ArrayList<>();
        listNodeList.add(listNode3);
        listNodeList.add(listNode1);
        listNodeList.add(listNode2);

        listNodeList.sort((Comparator<ListNode>) (o1, o2) -> {
            if (o1.val < o2.val) return -1;
            if (o1.val == o2.val) return 0;
            //if (o1.val > o2.val)
            return 1;
        });
        System.out.println("finish");
    }

    //-------------------将数组按一定规律划分前后，开始15：15，结束15:40---------------------------
    public void ReorderOddEven(int arr[]){
        //边界处理
        if (null == arr || arr.length<=1){
            return;
        }

        int left = 0;
        int right = arr.length-1;

        while (left<right){
            //不符合在前面才进行交换
            while (left<right && isEven(arr[left])){
                left++;
            }
            //符合在前面，则不符合在后面，交换
            while (left<right && !isEven(arr[right])){
                right--;
            }
            if (left<right){
                arr[left] = arr[left]^arr[right];
                arr[right] = arr[left]^arr[right];
                arr[left] = arr[left]^arr[right];
            }
        }

        System.out.println("");
    }

    //判断是否在数组前面符合条件
    public boolean isEven(int n){

        return n%2 == 1;
    }


    //删除链表指定节点，要求O(1),需确保val不同
    public ListNode deleteNode(ListNode listNode, ListNode node){
        //边界处理，null，node不在listNode中不考虑
        if (null == listNode || null == node){
            return null;
        }
        ListNode point = listNode;

        //只有一个结点的情况
        if (node.val == listNode.val){
            return null;
        }else if (null == node.next){//删除最后一个节点，找他的前一个节点
            while (point.next != null && point.next.val != node.val ){
                point = point.next;
            }
            //node不在listNode
            if (null == point.next){
                return listNode;
            }
            //找到，删除node
            ListNode tempNode = point.next;
            point.next = tempNode.next;
            tempNode.next = null;
            return listNode;
        }else {
            //不考虑node不在listNode的情况
            ListNode temp = node.next;
            node.val = temp.val;
            node.next = temp.next;
            temp.next = null;
            return listNode;

        }
    }

    //-----------------------------------
    //打印1到最大的n位数的主方法
    public void printToMaxOfDigits(int n){
        if(n <= 0){
            System.out.println("输入的n没有意义");
            return;
        }
        char number[] = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        for (int i = 0; i < 10; ++i) {
            number[0] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, 0);
        }
    }
    //利用递归实现1到最大的n位数的全排列
    public void printToMaxOfNDigitsRecursively(char[] number, int n, int index) {
        if(index == n - 1){
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            number[index + 1] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, index + 1);
        }
    }

    //输出
    private void printNumber(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; ++i) {
            if(isBeginning0 && number[i]!='0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

//-----------------------随机链表生成器-------------------
interface  Collision<T>{
    T generate();
    boolean equals();
    T rightMethod();
}
class MyCollision {
    //产生一条长度随机，值随机的链表
    public ListNode generate(){
        int count = (int) (Math.random()*10);
        ListNode rootNode = new ListNode(0);
        ListNode tempNode = rootNode;
        for (int i=0; i<count; i++){
            tempNode.next = new ListNode((int) (Math.random()*10));
            tempNode = tempNode.next;
        }
        return rootNode.next;
    }

    //产生一条长度为count，值随机的链表
    public ListNode generate(int count){
        ListNode rootNode = new ListNode(0);
        ListNode tempNode = rootNode;
        for (int i=0; i<count; i++){
            tempNode.next = new ListNode((int) (Math.random()*10));
            tempNode = tempNode.next;
        }
        return rootNode.next;
    }

    //判断两条链表是否相等,长度，每个节点的值
    public boolean equals(ListNode listNode1, ListNode listNode2){
        //边界处理
        if (null == listNode1 && null == listNode2){
            return true;
        }else if (null == listNode1 || null == listNode2){
            return false;
        }

        //值相等，next不为空
        while (listNode1.val == listNode2.val
                && listNode1.next != null
                && listNode2.next != null){
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
        };
        //判断值是否相等
        if (listNode1.val != listNode2.val){
            return false;
        }
        //判断长度是否相等
        return null == listNode1.next && null == listNode2.next;
    }

    public ListNode rightMethod(ListNode list, ListNode node){
        //边界处理,list == null, node == null, node不在list中
        if (null == list){
            return null;
        }
        if (null == node){
            return list;
        }
        ListNode point = list;
        while (point.val != node.val && point.next != null && point.next.val != node.val){
            point = point.next;
        }
        //第一个节点是需要删除的
        if (point.val == node.val){
            list = list.next;
        }
        //point.next == null，则node不在list上
        if (point.next == null){
            return list;
        }
        //next就是node
        if (point.next.val == node.val){
            ListNode tempNode = point.next;
            point.next = tempNode.next;
            tempNode.next = null;
        }

        return list;
    }

}
