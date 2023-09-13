package interview.ctrip;

import java.util.*;

public class ValidRemove {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] colors = in.next().toCharArray();
        int[][] edges = new int[n-1][2];
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt(), v = in.nextInt();
            adj[u].add(v);
            adj[v].add(u);
            edges[i][0] = u; edges[i][1] = v;
        }
        // dfs 求出以每个节点为根的子树中每种颜色的节点数量
        int[][] cnt = new int[n+1][3];
        dfs(1, 0, adj, colors, cnt);
        // 枚举所有边，找到满足条件的边
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            for (int i = 0; i < 3; i++) {
                int cu = cnt[u][i], cv = cnt[v][i];
                int cu2 = cnt[1][i] - cv, cv2 = cnt[1][i] - cu;
                if (cu > 0 && cv2 > 0 && cu2 + cv > 0) {
                    System.out.println(u + " " + v);
                    break;
                } else if (cv > 0 && cu2 > 0 && cv2 + cu > 0) {
                    System.out.println(v + " " + u);
                }
            }
        }
    }

    private static void dfs(int u, int prev, List<Integer>[] adj, char[] colors, int[][] cnt) {
        cnt[u][colors[u-1]-'r'] = 1;
        for (int v: adj[u]) {
            if (v == prev) {
                continue;
            }
            dfs(v, u, adj, colors, cnt);
            for (int i = 0; i < 3; i++) {
                cnt[u][i] += cnt[v][i];
            }
        }
    }

}
