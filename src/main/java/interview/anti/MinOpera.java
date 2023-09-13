package interview.anti;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinOpera {
    static List<List<Integer>> adj = new ArrayList<>();
    static boolean[] visited;
    static long ans = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[n+1];
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt(), v = in.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dfs(1, 1);
        System.out.println(ans);
    }

    private static void dfs(int u, int val) {
        if (u < val || ans == -1) {
            ans = -1;
            return;
        }
        ans += u - val;
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, u);
            }
        }
    }
}
