package tree

import java.util.LinkedList

class UniqueBST {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(UniqueBST().generateTrees(4).size)
        }
    }



    data class TreeNode(val value: Int, val left: TreeNode?, val right: TreeNode?)


    fun generateTrees(n: Int): List<TreeNode?> {
        if (n == 0) {
            return LinkedList<TreeNode>()
        }
        return generateTrees(1, n)

    }

    private fun generateTrees(start: Int, end: Int): List<TreeNode?> {
        val forest = LinkedList<TreeNode?>()
        if(start > end) {
            forest.add(null)
        }

        for (i in start .. end) {
            val leftForest = generateTrees(start, i-1)
            val rightForest = generateTrees( i+1, end)

            for (left in leftForest) {
                for (right in rightForest) {
                    val currentTree = TreeNode(i, left, right)
                    forest.add(currentTree)
                }
            }
        }

        return forest
    }
}