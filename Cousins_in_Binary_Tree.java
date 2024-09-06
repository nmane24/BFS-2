
/*
 * 
/*
Leetcode : https://leetcode.com/problems/cousins-in-binary-tree/description/

Given the root of a binary tree with unique values and the values of two different
nodes of the tree x and y, return true if the nodes corresponding to the values x and y 
in the tree are cousins, or false otherwise.
Two nodes of a binary tree are cousins if they have the same depth with different parents.
Note that in a binary tree, the root node is at the depth 0, and children of each depth k
 node are at the depth k + 1.



 class example
                                        1
                    2                                       3
                4           5                       6               7
        8           9           10                      11      12      13

8 and 9 are not cousins as they belong to same parent
8 and 10 are cousins as they belong to different level and have different parent

x = 11 and y =13

/**
 * 
 * Approach 1 : BFS
 * 
 * Watch vide for more understanding : https://www.youtube.com/watch?v=tYNauGZDhZI
 * 
 * We use a queue and size variable here, and we maintain an x_parent and y_parent variables to track the parents
 * Any time we pop the element , we check the babies are equal to x and y( for their boolean flags) and 
 * if they are found equal we store their  parents into the variables
 * And we maintain x_found and y_found, if these two variables become true at the same level 
 * and their parents are not equal, it means they are cousins , otherwise we will return false if they are found in
 * other levels.
 * If any of the boolean is found and the other is not found yet in the same level, it means they do not belong in
 * same level and we return false.
 * 
 * But we do not maintain x_parent and y_parent variables. 
 * We control this is by while we are adding to the queue I am making sure i am checking if those two babies are equal 
 * to x and y and if they belong to the same parent, we do not add to the queue itself. We return false from there as they belong
 * to the same parent and so they are not cousins.
 * 
 * So the check is the two elements we need to find out they do not belong to the same parent. If they are gettting added to the
 * queue, we are sure that they do not belong to the same parent and hence getting aded to the queue 
 * Because if they would have belonged to the same parent we would have returned false from there only 
 * 
 * 
 * 
 * // Apporach 1 
// Time Complexity : O(n) ...... N is total number of elements in tree
// Space Complexity : O(n/2) == O(n) ...... Because of use of Queue; maximum elements in queue will be
//                                   ...... elements from last level (leaf nodes) which is n/2
*/




import java.util.Queue;

import javax.swing.tree.TreeNode;

public class Cousins_in_Binary_Tree {
        public boolean isCousins(TreeNode root, int x, int y) {
                Queue<TreeNode> q = new LinkedList<>();
                q.add(root);

                while(!q.isEmpty()){
                    int size = q.size();
                    boolean x_found = false;
                    boolean y_found = false;

                    for(int i = 0; i<size; i++){
                        TreeNode curr = q.poll();
                        if(curr.val == x){
                           x_found = true;  
                        }
                        if(curr.val == y){
                           y_found = true;
                        }
                        //checking if babies belong to same parent, return false and restrict adding to the queue.
                        if(curr.left != null && curr.right != null){
                           if(curr.left.val == x && curr.right.val == y) return false;
                           if(curr.left.val == y && curr.right.val == x) return false;
                         }

                         if(curr.left != null){
                                q.add(curr.left);
                         }

                         if(curr.right != null){
                                q.add(curr.right);
                         }
                        
                    }
                    if(x_found && y_found) return true;
                    if(x_found || y_found) return false;

                }
                return false;
        }
}


/*
 * 
 * 
/*
 * 
 * Approach 2 : DFS
 * 
 * Watch vide for more understanding : https://www.youtube.com/watch?v=tYNauGZDhZI
 * 
 * 
 * In this recursive approach, we maintain parent and depth(level) which are passed as local / parameters of helper function.
 * We maintain them as local because they keep on changing. 
 * Anytime we find the element or node equal to x or y we record the depth and parent at that point
 * So we have 4 variables parent_x, parent_y, depth_x, depth_y
 * 
 * 
 * 
*/


 public class Cousins_in_Binary_Tree {
        TreeNode parent_x;
        TreeNode parent_y;
        int depth_x;
        int depth_y;
        public boolean isCousins(TreeNode root, int x, int y) {
          dfs(root, null, 0, x, y);
          return depth_x == depth_y && parent_x != parent_y;
        }

        private void dfs(TreeNode root, TreeNode parent, int depth, int x, int y){
                // base
                if(root == null) return;

                // logic
                if(root.val == x){
                   parent_x = parent;
                   depth_x = depth
                }

                if(root.val == y){
                   parent_y = parent;
                   depth_y = depth;
                }

                dfs(root.left, root, depth+1, x, y);
                dfs(root.right, root, depth+1, x, y);
        }
 }
