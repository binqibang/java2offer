package algorithm.weekly;

import java.util.*;

public class Weekly436 {
    public static int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        int x0 = 0, y0 = n - 1;
        int x1 = 0, y1 = n - 1;
        List<Integer> lst = new ArrayList<>();
        for (; y0 > 0 && x1 < n - 1; y0--, x1++) {
            for (int i = x0, j = y0; i <= x1 && j <= y1; i++, j++) {
                lst.add(grid[i][j]);
            }
            lst.sort(Comparator.comparingInt(a -> a));
            for (int i = x0, j = y0; i <= x1 && j <= y1; i++, j++) {
                grid[i][j] = lst.get(i);
            }
            lst.clear();
        }

        for (; x0 < n && y1 >= 0; x0++, y1--) {
            for (int i = x0, j = y0; i <= x1 && j <= y1; i++, j++) {
                lst.add(grid[i][j]);
            }
            lst.sort((a, b) -> b - a);
            for (int i = x0, j = y0; i <= x1 && j <= y1; i++, j++) {
                grid[i][j] = lst.get(j);
            }
            lst.clear();
        }
        return grid;
    }

    public int[] assignElements(int[] groups, int[] elements) {
        int n1 = groups.length, n2 = elements.length;
        int[] assigned = new int[n1];
        for (int i = 0; i < n1; i++) {
            assigned[i] = -1;
            for (int j = 0; j < n2; j++) {
                if (groups[i] % elements[j] == 0) {
                    assigned[i] = j;
                    break;
                }
            }
        }
        return assigned;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(sortMatrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }

}


