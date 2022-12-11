import kotlin.math.max

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */


class Solution {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private var maximum = Int.MIN_VALUE

    fun maxPathSum(root: TreeNode?): Int {
        gain(root)
        return maximum
    }

    fun gain(root:TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val leftMaxSum = max(gain(root.left), 0)
        val rightMaxSum = max(gain(root.right), 0)
        maximum = max(maximum, leftMaxSum + root.`val` + rightMaxSum)
        return max(leftMaxSum + root.`val`, rightMaxSum + root.`val`)
    }
}