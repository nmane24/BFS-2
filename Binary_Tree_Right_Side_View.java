/*
 * LeetCode Explanation : https://leetcode.com/problems/binary-tree-right-side-view/description/
 * 
 * // Apporach 1 
// Time Complexity : O(n) ...... N is total number of elements in tree
// Space Complexity : O(n/2) == O(n) ...... Because of use of Queue; maximum elements in queue will be
//                                   ...... elements from last level (leaf nodes) which is n/2
//
// Apporach 2
// Time Complexity : O(n) ...... N is total number of elements in tree
// Space Complexity : O(h) == worst case O(n) skewed tree ...... Because of use of stack in recursion
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

/*
Leetcode : https://leetcode.com/problems/binary-tree-right-side-view/description/

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
*/

import java.util.ArrayList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/**
 * Approach 1 : BFS approach
 * 
 * Queue is used to store the nodes.
 * Size of queue gives the nodes at same level.
 * LAst node in the queue is the right most element.
 * 
 * Watch vide for more understanding : https://www.youtube.com/watch?v=tYNauGZDhZI
 * 
 */

 public class Binary_Tree_Right_Side_View {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size;i++){
                TreeNode curr  = q.poll();
                // We add the right side view elements ( i.e the last element at each level)
                if(i==size -1){
                    result.add(curr.val);
                } 

                // If we need to add the left side view we change the above statement to below
                // if(i==0){
                //     result.add(curr.val);
                // } 

                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }

        return result;
    }
 }
 
 
 
 /**
  * Approach 2 : Using DFS
  * 
  * Recursion is used. Right nodes are visited first in each iteration.
  * Local variable level is used to track the level of the tree.
  * If level is visited first then that node is added to result.
  * 
 */


 public class Binary_Tree_Right_Side_View {
    List<Integer> result;
    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        if(root == null) return result;
        helper(root, 0);
        return result;
    }

    private void helper(TreeNode root, int level){
        // base
        if (root == null) return;

        // process root
        if(result.size() == level){
            result.add(root.val);
        }

        // traverse right first
        helper(root.right, level+1);

        // traverse left after
        helper(root.left, level+1);

    }

    // With traversing left first and then right

    private void helper(TreeNode root, int level){
        // base
        if (root == null) return;

        // process root
        if(result.size() == level){
            result.add(root.val);
        }
        else{
             // as we are traversing left first, replace the level value 
            result.set(level, root.val);
        }

        // traverse left first
        helper(root.left, level+1);

        // traverse right afer
        helper(root.right, level+1);

    }
 }