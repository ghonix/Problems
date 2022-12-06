package tree

import java.lang.StringBuilder
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class Tree {
    data class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    private val NULL = "null"
    fun serialize(root: Node?): String? {
        if (root == null) {
            return null
        }
        val nodeStack = Stack<Node>()
        val outputList = Vector<String>()
        nodeStack.push(root)
        while (nodeStack.isNotEmpty()) {
            val current = nodeStack.pop()
            if (current == null) {
                outputList.add(NULL)
            } else {
                outputList.add(current.data.toString())
                nodeStack.push(current.right)
                nodeStack.push(current.left)
            }
        }
        return outputList.joinToString(",")
    }

    private fun deSerialize(buffer: String): Node? {
        val elements = buffer.split(",")
        return _deSerialize(AtomicInteger(0), elements)

    }

    private fun _deSerialize(i: AtomicInteger, elements: List<String>): Node? {
        return if (i.get() > elements.size) {
            null
        } else if (NULL.equals(elements[i.get()])) {
            return null
        } else {
            val node = Node(Integer.parseInt(elements[i.get()]))
            i.incrementAndGet()
            node.left = _deSerialize(i, elements)
            i.incrementAndGet()
            node.right = _deSerialize(i, elements)

            node
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tree = Tree()
            val root =  Node(20)
            root.left =Node (8)
            root.right = Node (22)
            root.left!!.left = Node(4)
            root.left!!.right = Node(12)
            root.left!!.right!!.left = Node (10)
            root.left!!.right!!.right = Node (14)
            val serialized =  tree.serialize(root)
            println(serialized)
            serialized?.let {
                val deserializedTree = tree.deSerialize(serialized)
                val serializedAgain = tree.serialize(deserializedTree)

                println(serializedAgain)
            }
        }
    }
}