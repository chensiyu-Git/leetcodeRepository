//给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。 
//
// 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。 
//
// 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。 
//
// 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。 
//
// 
//
// 示例 1： 
//
// 
//输入：mat = 
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]], 
//k = 3
//输出：[2,0,3]
//解释：
//每行中的军人数目：
//行 0 -> 2   {2,0}
//行 1 -> 4 {4,1}
//行 2 -> 1 {1,2}
//行 3 -> 2 {2,3}
//行 4 -> 5 {5,4}
//目标排序结果 {1,2}{2,0}{2,3}{4,1}{5,4}
//从最弱到最强对这些行排序后得到 [2,0,3,1,4]
// 
//
// 示例 2： 
//
// 
//输入：mat = 
//[[1,0,0,0],
// [1,1,1,1],
// [1,0,0,0],
// [1,0,0,0]], 
//k = 2
//输出：[0,2]
//解释： 
//每行中的军人数目：
//行 0 -> 1 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 1 
//从最弱到最强对这些行排序后得到 [0,2,3,1]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 2 <= n, m <= 100 
// 1 <= k <= m 
// matrix[i][j] 不是 0 就是 1 
// 
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 
// 👍 94 👎 0


import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TheKWeakestRowsInAMatrix {
    public static void main(String[] args) {
        Solution solution = new TheKWeakestRowsInAMatrix().new Solution();
    }
    private static class Model1{
        int soldierNum;
        int index;

        public Model1(int soldierNum, int index) {
            this.soldierNum = soldierNum;
            this.index = index;
        }
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] kWeakestRows(int[][] mat, int k) {
            //做一个二元的数据体把士兵数量和所在的索引存储起来
            List<Model> sort = new ArrayList<>();
            for (int i = 0; i < mat.length; i++) {
                int[] row = mat[i];
                //二分找出最后一个1的位置,即士兵的数量-1
                int index = findLastOne(row, 0, row.length - 1);
                sort.add(new Model(index, i));
            }
            //士兵数量按从少到多排列，如果相同时，就按照索引位置从小到大排列
            sort.sort(new Comparator<Model>() {
                @Override
                public int compare(Model o1, Model o2) {
                    if(o1.soldierNum < o2.soldierNum){
                        return -1;
                    }else if(o1.soldierNum == o2.soldierNum){
                        if(o1.index<o2.index){
                            return -1;
                        }else {
                            return 0;
                        }
                    }else {
                        return 1;
                    }
                }
            });

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = sort.get(i).index;
            }
            return res;
        }

        private class Model{
            int soldierNum;
            int index;

            public Model(int soldierNum, int index) {
                this.soldierNum = soldierNum;
                this.index = index;
            }
        }

        private int findLastOne(int[] row, int start, int end) {
            if (start < 0 || end >= row.length) {
                throw new IndexOutOfBoundsException();
            }
            if (start == end) {
                return start;
            }
            if (row[0] == 0) {
                return -1;
            }
            if (row[row.length - 1] == 1) {
                return row.length - 1;
            }
            int mid = (start + end) / 2;
            if (row[mid] == 1 && row[mid + 1] == 0) {
                return mid;
            }
            if (row[mid] == 0) {
                return findLastOne(row, start, mid);
            } else {
                return findLastOne(row, mid, end);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}