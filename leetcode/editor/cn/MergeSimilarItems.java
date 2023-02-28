//给你两个二维整数数组 items1 和 items2 ，表示两个物品集合。每个数组 items 有以下特质： 
//
// 
// items[i] = [valuei, weighti] 其中 valuei 表示第 i 件物品的 价值 ，weighti 表示第 i 件物品的 重量 。
// 
// items 中每件物品的价值都是 唯一的 。 
// 
//
// 请你返回一个二维数组 ret，其中 ret[i] = [valuei, weighti]， weighti 是所有价值为 valuei 物品的 重量之和 
//。 
//
// 注意：ret 应该按价值 升序 排序后返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
//输出：[[1,6],[3,9],[4,5]]
//解释：
//value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 5 ，总重量为 1 + 5 = 6 。
//value = 3 的物品再 items1 中 weight = 8 ，在 items2 中 weight = 1 ，总重量为 8 + 1 = 9 。
//value = 4 的物品在 items1 中 weight = 5 ，总重量为 5 。
//所以，我们返回 [[1,6],[3,9],[4,5]] 。
// 
//
// 示例 2： 
//
// 
//输入：items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
//输出：[[1,4],[2,4],[3,4]]
//解释：
//value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 3 ，总重量为 1 + 3 = 4 。
//value = 2 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 1 ，总重量为 3 + 1 = 4 。
//value = 3 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
//所以，我们返回 [[1,4],[2,4],[3,4]] 。 
//
// 示例 3： 
//
// 
//输入：items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
//输出：[[1,7],[2,4],[7,1]]
//解释：
//value = 1 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 4 ，总重量为 3 + 4 = 7 。
//value = 2 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
//value = 7 的物品在 items2 中 weight = 1 ，总重量为 1 。
//所以，我们返回 [[1,7],[2,4],[7,1]] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= items1.length, items2.length <= 1000 
// items1[i].length == items2[i].length == 2 
// 1 <= valuei, weighti <= 1000 
// items1 中每个 valuei 都是 唯一的 。 
// items2 中每个 valuei 都是 唯一的 。 
// 
// Related Topics 数组 哈希表 有序集合 排序 👍 65 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSimilarItems {
    public static void main(String[] args) {
        Solution solution = new MergeSimilarItems().new Solution();
        int[][] items1 = new int[][]{{1, 3}, {2, 2}};
        int[][] items2 = new int[][]{{7, 1}, {2, 2}, {1, 4}};
        items1 = new int[][]{{1, 1}, {3, 2}, {2, 3}};
        items2 = new int[][]{{2, 1}, {3, 2}, {1, 3}};
        List<List<Integer>> lists = solution.mergeSimilarItems(items1, items2);
        System.out.println(lists);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
            //build Map<value, totalWeight>
//            Map<Integer, Integer> valueWeight = new HashMap<>();
            int entireLength = items1.length + items2.length;
            //it may waste some space but could avoid out of memory exception and grow method execution
            List<List<Integer>> res = new ArrayList<>(entireLength);
/*
            for (int[] item : items1) {
                int value = item[0];
                int weight = item[1];
                //because value and weight in items1 is unique
                valueWeight.put(value, weight);
            }
            for (int[] item : items2) {
                int value = item[0];
                int weight = item[1];
                int weight1 = valueWeight.getOrDefault(value, 0);
                valueWeight.put(value, weight + weight1);
            }
            //build end. then change map to array
            for (Map.Entry<Integer, Integer> entry : valueWeight.entrySet()) {
//                List<Integer> innerList = new ArrayList<>(2);
//                innerList.add(entry.getKey());
//                innerList.add(entry.getValue());
//                res.add(innerList);
                res.add(Arrays.asList(entry.getKey(),entry.getValue()));
            }
            //sort
            res.sort(Comparator.comparingInt(o -> o.get(0)));
*/

            //because 1<=value<=1000, actual 1-1000 is usable
            int[] tmpItems = new int[1001];
            for (int i = 0; i < entireLength; i++) {
                int tmpIndex, value, weight;
                if ((tmpIndex = i) < items1.length) {
                    value = items1[tmpIndex][0];
                    weight = items1[tmpIndex][1];
                } else {
                    tmpIndex -= items1.length;
                    value = items2[tmpIndex][0];
                    weight = items2[tmpIndex][1];
                }
                tmpItems[value] += weight;
            }
            for (int i = 0; i < tmpItems.length; i++) {
                if (tmpItems[i] > 0) {
                    res.add(Arrays.asList(i, tmpItems[i]));
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}