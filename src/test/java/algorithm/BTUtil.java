package algorithm;

public class BTUtil {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int pStart, int pEnd,
                                  int[] inorder, int inStart, int inEnd) {
        if (pStart > pEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[pStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        int leftLen = rootIndex - inStart;
        root.left = build(preorder, pStart + 1, pStart + leftLen,
                inorder, inStart, rootIndex - 1);
        root.right = build(preorder, pStart + leftLen + 1, pEnd,
                inorder, rootIndex + 1, inEnd);
        return root;
    }


}
