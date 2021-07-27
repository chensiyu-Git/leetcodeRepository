//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治算法 
// 👍 3992 👎 0

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        double med = solution.findMedianSortedArrays(new int[]{1,2},new int[]{3,4});
        System.out.println(med);
    }
    /** */
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
        基础思路：
        1.先合并数组
        2.将合并后的数组正序排列
            这一步排序会消耗大量的时间。优化方式有两种：
                1.优化排序算法(较难实现)
                2.尝试能不能在组装新数组的时候就直接排好序 (归并)
        3.找出中位数*/
//        int size1 = nums1.length;
//        int size2 = nums2.length;
//        int[] merge = new int[size1+size2];
//        System.arraycopy(nums1, 0, merge, 0, size1);
//        System.arraycopy(nums2, 0, merge, size1, size2);
//        if(merge.length >= 2) {
//            for (int j = merge.length - 1; j > 0; j--) {
//                for (int i = 0; i < j; i++) {
//                    if (merge[i] > merge[i + 1]) {
//                        int tmp = merge[i + 1];
//                        merge[i + 1] = merge[i];
//                        merge[i] = tmp;
//                    }
//                }
//            }
//        }
//        int med = merge.length/2;
//        if(merge.length%2==0){
//            return (merge[med]+merge[med-1])/2.0;
//        }else {
//            return merge[med];
//        }
        //归并：采用分治的思路（快排亦是基于这个思路）
        int i1 = 0,i2 = 0,im = 0;
        int[] merge = new int[nums1.length+nums2.length];
        while (i1 < nums1.length && i2<nums2.length){
            if(nums1[i1] < nums2[i2]){
                merge[im++]=nums1[i1++];
            }else {
                merge[im++]=nums2[i2++];
            }
        }
        while (i1 < nums1.length){
            merge[im++] = nums1[i1++];
        }
        while (i2 < nums2.length){
            merge[im++] = nums2[i2++];
        }
//        //到这里就合并完了数组
        int med = merge.length/2;
        if(merge.length%2==0){
            return (merge[med]+merge[med-1])/2.0;
        }else {
            return merge[med];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
    class QuickSort{
        public int[] quickSort(int[] arr){
            //分治实现快排
            //如何分治：将排序过程格式化为可重复执行的步骤，步骤如下
            //1.将最左点视为基点
            //2.将大于基点的数据排列至基点右方
            //3.将小于基点的数据排列至基点左方
            //4.最后剩下的数组位置就是基点的位置，将基点数据放入其中
            //5.重复1~4步骤，直到数组长度只剩下1



            return arr;
        }
    }

}