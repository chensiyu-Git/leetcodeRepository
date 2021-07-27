//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1246 👎 0

public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        char[][] land = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        printDoubleArray(land);
        System.out.println(solution.numIslands(land));
    }

    private static void printDoubleArray(char[][] doubleArray) {
        StringBuffer sbf = new StringBuffer("[landSize]").append(doubleArray.length).append("x").append(doubleArray[0].length).append("\n");
        for(char[] arr : doubleArray){
            sbf.append("[");
            for (char c : arr){
                sbf.append(c).append(",");
            }
            sbf.deleteCharAt(sbf.length()-1).append("]\n");
        }
        System.out.println(sbf.toString());
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            //应该使用广度搜索做全遍历能实现岛屿数量的检查
            char[][] searched = new char[grid.length][];
            for(int i=0;i<grid.length;i++){
                searched[i] = new char[grid[i].length];
            }
            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                char[] row = grid[i];
                for (int j = 0; j < row.length; j++) {
                    if (searched[i][j] != '1' && grid[i][j] == '1') {
                        //未被搜索过的陆地，证明是一块新大陆
                        searchLinkedLand(i, j, searched, grid);
                        res++;
                    }
                }
            }
            return res;
        }

        /**
         * 给一个点，广度搜索与该点连通的所有值为1的点,当值为0时返回
         * @param i 检索板块的纵坐标
         * @param j 检索板块的横坐标
         * @param searched 检索结果
         * @param grid 检索目标
         */
        private void searchLinkedLand(int i, int j, char[][] searched, char[][] grid) {
            if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length){
                //超出地图了
                return;
            }
            if(searched[i][j] == '1'){
                //被检索过的不用再接着检索了
                return;
            }
            searched[i][j] = '1';
            if(grid[i][j] != '1'){
                //不是陆地就该返回了
                return;
            }
            //是陆地，就接着往下找，四面检查最稳妥
            searchLinkedLand(i+1,j,searched,grid);
            searchLinkedLand(i-1,j,searched,grid);
            searchLinkedLand(i,j+1,searched,grid);
            searchLinkedLand(i,j-1,searched,grid);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}