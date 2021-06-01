package com.imoc.order.temp.linked;

class MyLinkedList {
    int length;
    ListNode head;
    /** 初始化构造器*/
    public MyLinkedList() {
        this.head=new ListNode(0);
        this.length=0;
    }
    /**获取索引值对应的节点值 */
    public int get(int index) {
        if(index<0||index>=length) return -1;
        ListNode p=head;
        for(int i=0;i<=index;i++) {//index=0,对应第1个节点，第一个节点即为head.next
            p=p.next;
        }
        return p.val;
    }
    /**在链表头添加节点 */
    public void addAtHead(int val) {
        ListNode first=new ListNode(val);
        first.next=head.next;
        head.next=first;
        length++;
        //addAtIndex(0,val);
    }
    /** 在链表尾部添加节点 */
    public void addAtTail(int val) {
        ListNode p=head;
        while(p.next!=null) p=p.next;
        p.next=new ListNode(val);
        length++;
    }
    /**在索引值为index的节点之前插入节点 */
    public void addAtIndex(int index, int val) {
        if(index<0) index=0;//addAtHead(val); return;
        if(index>length) return;
        /*if(index==length) { addAtTail(val); return; }*/
        length++;
        ListNode pre=head;
        for(int i=0;i<index;i++)pre=pre.next;
        ListNode temp=new ListNode(val);
        temp.next=pre.next;
        pre.next=temp;
    }
    /** 如果索引 index 有效，则删除链表索引值为 index的节点。 */
    public void deleteAtIndex(int index) {
        if(index<0||index>=length) return;
        length--;
        ListNode pre=head;
        for(int i=0;i<index;i++) pre=pre.next;
        pre.next=pre.next.next;
    }
}
