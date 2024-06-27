import java.util.*;

public class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Queue<Path> q = new LinkedList<>();
        q.offer(new Path(root, root.val + ""));
        List<Path> currentLevel = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Path p = q.poll();
                currentLevel.add(p);
                map.put(p.node.val, p.node);
                if (p.node.left != null) {
                    q.offer(new Path(p.node.left, p.path + "-" + p.node.left.val));
                }
                if (p.node.right != null) {
                    q.offer(new Path(p.node.right, p.path + "-" + p.node.right.val));
                }
            }
            if (!q.isEmpty()) {
                currentLevel.clear();
            }
        }
        if (currentLevel.size() == 1) {
            return currentLevel.get(0).node;
        }

        //currentLevel.forEach(x -> System.out.println(x.path));
        String[][] paths = new String[currentLevel.size()][];

        for (int i = 0; i < currentLevel.size(); i++) {
            paths[i] = currentLevel.get(i).path.split("-");
        }
        TreeNode lca = root;
        for (int j = 0; j < paths[0].length; j++) {
            for (int i = 1; i < paths.length; i++) {
                if (!paths[i][j].equals(paths[0][j])) {
                    return lca;
                }
            }
            lca = map.get(Integer.parseInt(paths[0][j]));
        }
        return null;
    }
}

class Path {
    TreeNode node;
    String path;

    Path(TreeNode node, String path) {
        this.node = node;
        this.path = path;
    }

    Path() {

    }
}
