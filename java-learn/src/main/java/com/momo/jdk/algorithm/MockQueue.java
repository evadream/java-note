/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.momo.jdk.algorithm;

/**
 * MockQueue
 *
 * @author huangtao
 * @date 2019/6/13
 * desc：循环队列（队尾始终不放元素，即 (rear + 1) % array.length < front）
 */
public class MockQueue {
    private int[] array;
    private int front;
    private int rear;

    public MockQueue(int capacity) {
        this.array = new int[capacity];
    }

    public void enQueue(int element) throws Exception {
        if ((rear + 1) % array.length == front) {
            throw new Exception("队列已满");
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
    }

    public int deQueue() throws Exception {
        if (rear == front) {
            throw new Exception("队列已空");
        }
        int dequeueElement = array[front];
        front = (front + 1) % array.length;
        return dequeueElement;
    }

    public void outPut() {
        for (int i = front; i != rear; i = (i + 1) % array.length) {
            System.out.print(array[i]+";");
        }
    }

    public static void main(String[] args) throws Exception {
        MockQueue mockQueue = new MockQueue(6);
        mockQueue.enQueue(1);
        mockQueue.enQueue(5);
        mockQueue.enQueue(7);
        mockQueue.enQueue(2);
        mockQueue.enQueue(4);
        System.out.print("init queue:");
        mockQueue.outPut();
        System.out.print("dequeue :");
        mockQueue.deQueue();
        mockQueue.deQueue();
        mockQueue.outPut();
    }
}

    