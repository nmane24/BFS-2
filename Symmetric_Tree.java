
/*
 * LeetCode : https://leetcode.com/problems/symmetric-tree/description/
 * 
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * 
 * Example 1 :
 * Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false
 * 
 * Class Example 
 * 
 * This is not mirror image
 *                              1
 *                     2                  2
 *              3           4       4           3
 *          6       7     5        5         7      6
 *                 8  9                     9  8 
 * 
 * 
 * 
 *  * 
 * This is a mirror image, as we changed the position 5
 *                              1
 *                     2                  2
 *              3           4       4           3
 *          6       7     5            5        7      6
 *                 8  9                     9  8 
 * 
 * 
 * 
 * 
 * Check video for explanation : https://www.youtube.com/watch?v=tYNauGZDhZI
 * 
 * Just check if the recursive calls taken simultaneously if they are equal or not.
 * We make two calls , we will be processing two nodes at a time in each call
 * 
 * TC : O(n)
 * SC : O(h)
 */

import javax.swing.tree.TreeNode;

public class Symmetric_Tree {

    // using global flag
    boolean flag;
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        this.flag = true;
        dfs(root.left, root.right);
        return flag;
    }

    private void dfs(TreeNode left, TreeNode right){
        // base
        if(left == null && right == null) return;

        // logic and the breach
        if(left == null || right == null || left.val != right.val){
            flag = false;
            return;
        }

        dfs(left.left, right.right);
        dfs(left.right, right.left);
    }
    // restricting traversing with conditional recursion
    private void dfs(TreeNode left, TreeNode right){
        // base
        if(left == null && right == null) return;

        // logic and the breach
        if(left == null || right == null || left.val != right.val){
            flag = false;
            return;
        }
        if(flag) // if breach has found then we wont be traversing further
            dfs(left.left, right.right);
        if(flag)
            dfs(left.right, right.left);
    }
}

// Wihtout the boolean flag

public class Symmetric_Tree {

    public boolean isSymmetric(TreeNode root) {
        if(root == null )return true;
        return dfs(root.left , root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right){
        //base
        if(left == null && right == null) return true;

        // logic and the breach
        if(left == null || right == null || left.val != right.val){
            return false;
        }

        boolean case1 = dfs(left.left, right.right);
        boolean case2 = dfs(left.right, right.left);

        return case1 && case2;
    }
}

/*
 * 
 * Using BFS, where we process two nodes at a time in queue
 * 
 * TC : O(n)
 * SC : O(n).. queue space
 */
public class Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        // process two nodes at the same time
        q.add(root.left);
        q.add(root.right);

        while(!q.isEmpty){
            // poll two nodes at the same time
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            // we just continue if both found null 
            if(left == null && right == null){
                continue;
            }
            // breach condition and we return from here
            if(left == null || right == null || left.val != right.val){
                return false;
            }
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }

        return true;
    }

}