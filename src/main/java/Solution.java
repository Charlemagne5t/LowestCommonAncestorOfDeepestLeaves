import java.util.*;

public class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Deque<TreeNode> q =new ArrayDeque<>();
        q.offer(root);
        Set<Integer> deepest = new HashSet<>();
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                deepest.add(cur.val);
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
            }
            if(!q.isEmpty()){
                deepest = new HashSet<>();
                level++;
            }

        }



        return dfs(root, deepest);
    }
    TreeNode dfs(TreeNode root, Set<Integer> set){
        if(root == null){
            return null;
        }
        if(set.contains(root.val)){
            return root;
        }

        TreeNode left = dfs(root.left, set);
        TreeNode right = dfs(root.right, set);

        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }

        return root;
    }
}
