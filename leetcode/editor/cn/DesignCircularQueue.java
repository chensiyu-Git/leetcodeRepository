

//设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”
//。 
//
// 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环
//队列，我们能使用这些空间去存储新的值。 
//
// 你的实现应该支持如下操作： 
//
// 
// MyCircularQueue(k): 构造器，设置队列长度为 k 。 
// Front: 从队首获取元素。如果队列为空，返回 -1 。 
// Rear: 获取队尾元素。如果队列为空，返回 -1 。 
// enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。 
// deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。 
// isEmpty(): 检查循环队列是否为空。 
// isFull(): 检查循环队列是否已满。 
// 
//
// 
//
// 示例： 
//
// MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//circularQueue.enQueue(1);  // 返回 true
//circularQueue.enQueue(2);  // 返回 true
//circularQueue.enQueue(3);  // 返回 true
//circularQueue.enQueue(4);  // 返回 false，队列已满
//circularQueue.Rear();  // 返回 3
//circularQueue.isFull();  // 返回 true
//circularQueue.deQueue();  // 返回 true
//circularQueue.enQueue(4);  // 返回 true
//circularQueue.Rear();  // 返回 4 
//
// 
//
// 提示： 
//
// 
// 所有的值都在 0 至 1000 的范围内； 
// 操作数将在 1 至 1000 的范围内； 
// 请不要使用内置的队列库。 
// 
// Related Topics 设计 队列 数组 链表 
// 👍 199 👎 0

public class DesignCircularQueue {
    public static void main(String[] args) {
        MyCircularQueue solution = new DesignCircularQueue().new MyCircularQueue(3);
        solution.enQueue(3);
        solution.enQueue(2);
        solution.enQueue(1);
        solution.enQueue(10);
        solution.Rear();

    }
    /**
     * 基于数组的循环队列设计
     */
    //leetcode submit region begin(Prohibit modification and deletion)
class MyCircularQueue {
    int[] queue;
    int headPointer;
    int tailPointer;
    int freeLength;
    int usedLength;
    final int queueLength;

    public MyCircularQueue(int k) {
        if(k<=0){
            throw new RuntimeException("length can not less than 1");
        }
        queue = new int[k];
        queueLength = k;
        headPointer = 0;
        freeLength = k;
        usedLength = 0;
        tailPointer = -1;
    }
    
    public boolean enQueue(int value) {
        if(freeLength == 0){
            return false;
        }
        //入队列表示循环记录
        tailPointer = ++tailPointer % queueLength;
        queue[tailPointer] = value;
        usedLength++;
        freeLength--;
        return true;
    }
    
    public boolean deQueue() {
        //从队列头删一个元素，删除成功则返回true
        if(usedLength == 0){
            return false;
        }
        //头指针指向下一位，并把free+1，use-1
        headPointer = ++headPointer % queueLength;
        freeLength++;
        usedLength--;
        return true;
    }
    
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return queue[headPointer];
    }
    
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return queue[tailPointer];
    }
    
    public boolean isEmpty() {
        return usedLength == 0;
    }
    
    public boolean isFull() {
        return freeLength == 0;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}