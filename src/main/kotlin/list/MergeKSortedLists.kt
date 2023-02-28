/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
package list

import java.util.PriorityQueue
import java.util.Queue

class MergeKSortedLists {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        var priorityQueue: Queue<ListNode> = PriorityQueue { a, b ->
            a.`val` - b.`val`
        }

        for (i in lists.indices) {
            if (lists[i] != null) {
                priorityQueue.add(lists[i])
            }
        }
        var root: ListNode? = null
        var currentNode: ListNode? = null
        while (priorityQueue.isNotEmpty()) {

            var currentMin = priorityQueue.poll()

            if (root == null) {
                currentNode = ListNode(currentMin.`val`)
                root = currentNode
            } else {
                currentNode?.next = ListNode(currentMin.`val`)
                currentNode = currentNode?.next
            }
            if (currentMin.next != null) {
                priorityQueue.add(currentMin.next)
            }
        }

        return root
    }
}