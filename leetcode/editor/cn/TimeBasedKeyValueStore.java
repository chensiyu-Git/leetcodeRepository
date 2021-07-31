//创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作： 
//
// 1. set(string key, string value, int timestamp) 
//
// 
// 存储键 key、值 value，以及给定的时间戳 timestamp。 
// 
//
// 2. get(string key, int timestamp) 
//
// 
// 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。 
//
// 如果有多个这样的值，则返回对应最大的 timestamp_prev 的那个值。 
// 如果没有值，则返回空字符串（""）。 
// 
//
// 
//
// 示例 1： 
//
// 输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["f
//oo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
//输出：[null,null,"bar","bar",null,"bar2","bar2"]
//解释：  
//TimeMap kv;   
//kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1   
//kv.get("foo", 1);  // 输出 "bar"   
//kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即
// "bar"）   
//kv.set("foo", "bar2", 4);   
//kv.get("foo", 4); // 输出 "bar2"   
//kv.get("foo", 5); // 输出 "bar2"   
//
// 
//
// 示例 2： 
//
// 输入：inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [
//[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["lov
//e",20],["love",25]]
//输出：[null,null,null,"","high","high","low","low"]
// 
//
// 
//
// 提示： 
//
// 
// 所有的键/值字符串都是小写的。 
// 所有的键/值字符串长度都在 [1, 100] 范围内。 
// 所有 TimeMap.set 操作中的时间戳 timestamps 都是严格递增的。 
// 1 <= timestamp <= 10^7 
// TimeMap.set 和 TimeMap.get 函数在每个测试用例中将（组合）调用总计 120000 次。 
// 
// Related Topics 设计 哈希表 字符串 二分查找 
// 👍 107 👎 0


import java.util.*;

public class TimeBasedKeyValueStore{
      public static void main(String[] args) {
          TimeMap solution = new TimeBasedKeyValueStore().new TimeMap();
//          solution.set("fool","bar",1);
//          System.out.println(solution.get("fool",1));
//          System.out.println(solution.get("fool",3));
//          solution.set("fool","bar2",4);
//          System.out.println(solution.get("fool",4));
//          System.out.println(solution.get("fool",5));
//          solution.set("love","high",10);
//          solution.set("love","low",20);
//          System.out.println(solution.get("love",5));
//          System.out.println(solution.get("love",10));
//          System.out.println(solution.get("love",15));
//          System.out.println(solution.get("love",20));
//          System.out.println(solution.get("love",25));
//          System.out.println(solution.map);
          solution.set("a","bar",1);
          solution.set("x","b",3);
          solution.get("b",3);
          solution.set("foo","bar2",4);
          System.out.println(solution.get("fool",4));
          System.out.println(solution.get("fool",5));
      }
      /** */
      //leetcode submit region begin(Prohibit modification and deletion)
class TimeMap {

    Map<String,List<Value>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>(16);
    }
    
    public void set(String key, String value, int timestamp) {
        Value v = new Value(value,timestamp);
        List<Value> tmp;
        if(map.containsKey(key)){
            tmp = map.get(key);
        }else {
            tmp = new ArrayList<>();
        }
        tmp.add(v);
        map.put(key,tmp);
    }
    
    public String get(String key, int timestamp) {
        List<Value> lv = map.get(key);
        if(lv == null){
            return "";
        }
        if(lv.size() == 1){
            return lv.get(0).getValue();
        }
        if(lv.size() == 2){
            if ((timestamp < lv.get(0).getTimeStamp())) {
                return "";
            }else if(timestamp >= lv.get(1).getTimeStamp()){
                return lv.get(1).getValue();
            }else {
                return lv.get(0).getValue();
            }
        }
        //此时lv中的timeStamp单调递减
        return find(lv,0, lv.size()-1 , timestamp);
    }
    //find 5 [10,20]
    //find 11
    //[2,4,6,8,10,12,16,18]
    private String find(List<Value> list,int start,int end, int timeStamp){
        if(start == end){
            //完全终止条件，已经是一个确定的位数了
            if(timeStamp >= list.get(start).getTimeStamp()){
                return list.get(start).getValue();
            }else {
                if(start == 0){
                    return null;
                }
                return list.get(start-1).getValue();
            }
        }
        if(end - start == 1){
            //两个数的时候有特殊的终止条件
            if ((timeStamp < list.get(end).getTimeStamp())) {
                return list.get(start-1).getValue();
            }else if(timeStamp >= list.get(end).getTimeStamp()){
                return list.get(end).getValue();
            }else {
                return list.get(start).getValue();
            }
        }
        int middle = (start+end)/2;
        Value tmp = list.get(middle);
        if(timeStamp == tmp.getTimeStamp()){
            return tmp.getValue();
        }
        if(timeStamp>tmp.getTimeStamp()){
            return find(list,middle,end,timeStamp);
        }else {
            return find(list,start,middle,timeStamp);
        }
    }
          class Value {
              private String value;
              private long timeStamp;

              public Value(String value, long timeStamp) {
                  this.value = value;
                  this.timeStamp = timeStamp;
              }

              public String getValue() {
                  return value;
              }

              public void setValue(String value) {
                  this.value = value;
              }

              public long getTimeStamp() {
                  return timeStamp;
              }

              public void setTimeStamp(long timeStamp) {
                  this.timeStamp = timeStamp;
              }
          }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
//leetcode submit region end(Prohibit modification and deletion)

  }