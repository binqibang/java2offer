package algorithm.weekly;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
public class Weekly432 {

    public static List<Integer> zigzagTraversal(int[][] grid) {
        boolean dir = true;
        boolean isSkip = false;
        int m = grid.length, n = grid[0].length;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (dir) {
                for (int j = 0; j < n; j++) {
                    if (!isSkip) {
                        list.add(grid[i][j]);
                        isSkip = true;
                    } else {
                        isSkip = false;
                    }
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    if (!isSkip) {
                        list.add(grid[i][j]);
                        isSkip = true;
                    } else {
                        isSkip = false;
                    }
                }
            }
            dir = !dir;
        }
        return list;
    }

    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        // dp[i][j]表示到位置(i,j)得最大金币
        // dp[i][j][k]表示使用k次感化操作得最大金币
        int[][][] dp = new int[m][n][3];
        // initialize dp with INF
        int INF = Integer.MIN_VALUE / 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }
        dp[0][0][0] = coins[0][0];
        dp[0][0][1] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 不使用感化
                for (int k = 0; k < 3; k++) {
                    // 只能从左或上
                    if (i > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k] + coins[i][j]);
                    }
                    if (j > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k] + coins[i][j]);
                    }
                }
                // 使用感化
                for (int k = 1; k < 3; k++) {
                    if (i > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k - 1]);
                    }
                    if (j > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k - 1]);
                    }
                }
            }
        }

        int ans = INF;
        for (int k = 0; k < 3; k++) {
            ans = Math.max(ans, dp[m - 1][n - 1][k]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(zigzagTraversal(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
