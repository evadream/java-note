/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.algorithm;

/**
 * MockLinkedList
 *
 * @author huangtao
 * @date 2019/6/13
 * desc：
 */
public class MockLinkedList {
    private Node head;
    private Node last;
    private int size;


    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表范围");
        }
        Node insetNode = new Node(data);
        if (size == 0) {
            //插入头部
            head = insetNode;
            last = insetNode;
        } else if (size == index) {
            //插入尾部
            last.next = insetNode;
            last = insetNode;
        } else {
            Node preNode = get(index - 1);
            Node nextNode = preNode.next;
            preNode.next = insetNode;
            insetNode.next = nextNode;
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表范围");
        }
        if (index == 0) {
            //delete head
            Node deleteNode = head;
            head = deleteNode.next;
        } else if (index == size - 1) {
            //delete last
            Node preNode = get(index - 1);
            preNode.next = null;
            last = preNode;
        } else {
            Node preNode = get(index - 1);
            Node deleteNode = preNode.next;
            preNode.next = deleteNode.next;
        }
        size--;
    }

    /**
     * 获取一个元素
     *
     * @param index
     * @return
     */
    public Node get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表范围");
        }
        Node tem = head;
        for (int i = 0; i < index; i++) {
            tem = tem.next;
        }
        return tem;
    }


    /**
     * 输出链表元素
     */
    public void outPut() {
        Node tem = head;
        while (tem != null) {
            System.out.println(tem.data);
            tem = tem.next;
        }
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }


    public static void main(String[] args) {
        MockLinkedList mockLinkedList = new MockLinkedList();
        mockLinkedList.insert(3, 0);
        mockLinkedList.insert(2, 1);
        mockLinkedList.insert(4, 2);
        mockLinkedList.insert(3, 3);
        mockLinkedList.insert(5, 4);
        mockLinkedList.outPut();
        mockLinkedList.remove(0);
        mockLinkedList.outPut();
        mockLinkedList.remove(3);
        mockLinkedList.outPut();
    }

}

    