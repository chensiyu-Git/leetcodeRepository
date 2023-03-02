//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。 
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 2566 👎 0

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    public static void main(String[] args) {
        LRUCache solution = new LruCache().new LRUCache(2);
        System.out.println(solution.get(2));
        solution.put(2, 6);
        System.out.println(solution.get(1));
        solution.put(1, 5);
        solution.put(1, 2);
        System.out.println(solution.get(1));
        System.out.println(solution.get(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        Map<Integer, Integer> values;

        Node head;

        Node last;

        int capacity;

        int entireNodeSize;


        public LRUCache(int capacity) {
            values = new HashMap<>(capacity);
            head = new Node();
            last = new Node();
            head.pre = last;
            head.next = last;
            last.pre = head;
            last.next = head;
            this.capacity = capacity;
            entireNodeSize = 0;
        }

        public int get(int key) {
            //刷新使用关系，然后return
            if (!values.containsKey(key)) {
                return -1;
            }
            refreshUsingRelation(key, true);
            //刷新完成,然后return
            return values.get(key);
        }

        public void put(int key, int value) {
            //刷新使用关系，然后put
            refreshUsingRelation(key, false);
            values.put(key, value);
        }

        private void refreshUsingRelation(int key, boolean getMethod) {
            //刷新头结点
            Node nextLast = head.next;
            if (nextLast.key != null && nextLast.key == key) {
                return;
            }
            Node first = new Node(key);
            head.next = first;
            first.pre = head;
            first.next = nextLast;
            nextLast.pre = first;
            //检索总长度是否有超出
            if (++entireNodeSize > capacity) {
                //超了，就要移除last的pre
                //这里最精确的其实是维护一个pool，然后如果操作的key在pool中，这里从尾部迭代上去找到最后一个key的node，移除那个node是一定正确的，但是时间复杂度不能保证为o（1）
                Node lastPre = last.pre;
                Node lastPrePlus = lastPre.pre;
                lastPrePlus.next = last;
                last.pre = lastPrePlus;
                if (!getMethod) {
                    //如果不是get导致的超长，就还要将map中的数据移除
                    values.remove(lastPre.key);
                }
            }

        }

        class Node {
            Integer key;
            Node next;
            Node pre;

            public Node() {
            }

            public Node(Integer key) {
                this.key = key;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}