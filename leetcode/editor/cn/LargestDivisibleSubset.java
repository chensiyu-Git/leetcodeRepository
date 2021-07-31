

//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
//j]) 都应当满足：
// 
// answer[i] % answer[j] == 0 ，或 
// answer[j] % answer[i] == 0 
// 
//
// 如果存在多个有效解子集，返回其中任何一个均可。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 1 <= nums[i] <= 2 * 109 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数学 动态规划 
// 👍 309 👎 0


import java.util.*;

public class LargestDivisibleSubset {
    public static void main(String[] args) {
        Solution solution = new LargestDivisibleSubset().new Solution();
        List<Integer> res = solution.largestDivisibleSubset(new int[]{1,2,3});
        System.out.println("end");
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            //基于基础规则的暴力解法
            Set<Integer> res = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0) {
                        res.add(nums[i]);
                        res.add(nums[j]);
                    }
                }
            }
            return new ArrayList<>(res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}