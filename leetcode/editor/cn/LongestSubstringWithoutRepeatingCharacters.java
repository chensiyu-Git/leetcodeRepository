//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 5328 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        int res = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(res);

    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /** solution */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 2){
            return s.length();
        }
        int lenth = 0;
        int markLeft = 0;
        int markRight = 0;
        char[] chars = s.toCharArray();
        StringBuffer notReChar = new StringBuffer();
        while (markRight != chars.length){
            if(notReChar.indexOf(String.valueOf(chars[markRight])) < 0){
                //右标指向的字符还不存在
                //此时，将新字符加入数组中,并将右标右移一步
                notReChar.append(chars[markRight]);
                markRight+=1;
            }else {
                //右标指向的字符已经存在了
                //记录此时的长度
                //队列是有序的，直接将左标右移到重复字符的右一步
                //将队列前n位干掉
                lenth = Math.max(lenth,notReChar.length());
//                System.out.println(notReChar.toString() + " : "+lenth);
                int step = notReChar.indexOf(String.valueOf(chars[markRight]));
                markLeft+=step+1;
                notReChar = new StringBuffer(notReChar.substring(step+1));
            }
        }
        lenth = Math.max(lenth,notReChar.length());
//        System.out.println(notReChar.toString() + " : "+lenth);
        return lenth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}