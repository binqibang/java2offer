package algorithm.design;

import java.util.*;

/**
 * LeetCode #1896 内存分配器
 */
public class Allocator {
    private Map<Integer, List<int[]>> used;
    private TreeSet<int[]> free;

    public Allocator(int n) {
        used = new HashMap<>();
        free = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
        free.add(new int[] {0, n});
    }

    /**
     * 分配内存空间
     *
     * @param size 内存空间的大小
     * @param mID 申请内存空间的模块ID
     * @return 返回分配的内存空间的起始地址，如果无法分配则返回-1
     */
    public int allocate(int size, int mID) {
        // 遍历 TreeSet，找到最左边满足 size 大小的内存
        for (int[] interval : free) {
            // 检查当前内存区间是否满足所需大小
            if (interval[1] - interval[0] >= size) {
                // 计算分配内存的左右边界
                int left = interval[0];
                int right = interval[0] + size;
                // 从空闲内存中移除该区间
                free.remove(interval);
                // 如果分配后还有剩余内存，则将剩余内存重新加入空闲列表
                if (right < interval[1]) {
                    free.add(new int[]{right, interval[1]});
                }
                // 将分配的内存加入已使用列表中，关联到对应的模块ID
                used.computeIfAbsent(mID, k -> new ArrayList<>()).add(new int[]{left, right});
                // 返回分配内存的起始地址
                return left;
            }
        }
        // 如果没有找到合适的内存区间，返回-1
        return -1;
    }

    /**
     * 释放指定内存块ID的所有内存
     * 此方法通过回收不再使用的内存区间来管理内存的释放
     * 它根据给定的内存块ID查找所有分配的内存区间，并尝试将这些区间合并到空闲内存列表中
     *
     * @param mID 内存块ID，用于标识特定的内存分配
     * @return 返回释放的总内存大小
     */
    public int freeMemory(int mID) {
        int total = 0;
        // 获取指定内存块ID的所有内存区间
        var intervals =  used.get(mID);
        // 如果该内存块ID没有分配任何内存区间，则直接返回0
        if (intervals == null) {
            return 0;
        }
        // 遍历该内存块ID的所有内存区间
        for (var interval : intervals) {
            // 获取当前内存区间在空闲内存列表中的直接下邻和上邻区间
            int[] lower = free.lower(interval);
            int[] higher = free.higher(interval);
            // 如果当前内存区间可以与下邻和上邻区间合并，则执行合并操作
            if (lower != null && lower[1] == interval[0] &&
                    higher != null && higher[0] == interval[1]) {
                free.remove(lower);
                free.remove(higher);
                free.add(new int[] {lower[0], higher[1]});
            } else if (lower != null && lower[1] == interval[0]) {
                // 如果只能与下邻区间合并，则扩展下邻区间的上界
                lower[1] = interval[1];
            } else if (higher != null && higher[0] == interval[1]) {
                // 如果只能与上邻区间合并，则扩展上邻区间的下界
                higher[0] = interval[0];
            } else {
                // 如果不能与任何区间合并，则将当前内存区间直接添加到空闲内存列表中
                free.add(interval);
            }
            total += interval[1] - interval[0];
        }
        // 从已使用的内存列表中移除该内存块ID的所有记录
        used.remove(mID);
        return total;
    }
}

