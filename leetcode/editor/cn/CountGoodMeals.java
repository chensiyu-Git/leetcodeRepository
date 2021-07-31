//大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。 
//
// 你可以搭配 任意 两道餐品做一顿大餐。 
//
// 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大
//餐 的数量。结果需要对 109 + 7 取余。 
//
// 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。 
//
// 
//
// 示例 1： 
//
// 
//输入：deliciousness = [1,3,5,7,9]
//输出：4
//解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
//它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
// 
//
// 示例 2： 
//
// 
//输入：deliciousness = [1,1,1,3,3,3,7]
//输出：15
//解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。 
//
// 
//
// 提示： 
//
// 
// 1 <= deliciousness.length <= 105 
// 0 <= deliciousness[i] <= 220 
// 
// Related Topics 数组 哈希表 
// 👍 103 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountGoodMeals {
    public static void main(String[] args) {
        Solution solution = new CountGoodMeals().new Solution();
        System.out.println(solution.countPairs(new int[]{1, 3, 5, 7, 9}));
        System.out.println(solution.countPairs(new int[]{1, 1, 1, 3, 3, 3, 7}));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countPairs(int[] deliciousness) {
            //实际是一个高级的两数相加，不过需求的数据不再是传入，二十确定为2的整数次幂
            //构建每一次两数相加的目标值
            int[] mi = new int[22];
            for (int i = 0; i < 22; i++) {
                mi[i] = 1 << i;
            }

            int res = 0;
            for (int target : mi) {
                Map<Integer, Integer> tmpSet = new HashMap<>(deliciousness.length / 2);
                for (int i = 0; i < deliciousness.length; i++) {
                    for(int j=0;j<deliciousness.length;j++) {
                        if(j==i){
                            continue;
                        }
                        if (tmpSet.containsKey(target - deliciousness[i])) {
                            res++;
                            res %=  1000000007;
                        } else {
                            tmpSet.put(deliciousness[i], 1);
                        }
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}