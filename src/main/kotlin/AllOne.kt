class AllOne {
    private val map = HashMap<String, Node>()
    private var head: Node? = null
    private var tail: Node? = null

    data class Node(var key: String, var count: Int = 0) {
        var next: Node? = null
        var prev: Node? = null
    }


    fun inc(key: String) {
        var node = map[key]
        if (node != null) {
            node.count++
            moveUp(node)
        } else {
            node = Node(key, 1)
            map[key] = node
            insert(node)
        }
    }

    fun dec(key: String) {
        val node = map[key]
        if (node == null) {
            return
        } else {
            node.count--
            if (node.count == 0) {
                map.remove(key)
                remove(node)
            } else {
                moveDown(node)
            }
        }
    }

    fun getMaxKey(): String {
        return tail?.key ?: ""
    }

    fun getMinKey(): String {
        return head?.key ?: ""
    }

    private fun remove(node: Node) {
        if (head == node && tail == node) {
            head = null
            tail = null
            return
        }

        when (node) {
            tail -> {
                tail = node.prev
                tail!!.next = null
            }
            head -> {
                head = node.next
                head!!.prev = null
            }
            else -> {
                node.prev!!.next = node.next
                node.next!!.prev = node.prev
            }
        }
    }

    private fun insert(node: Node) { // inserts at tail
        if (head == null && tail == null) {
            head = node
            tail = node
        } else {
            node.next = head
            head!!.prev = node
            head = node
        }
    }

    private fun moveUp(node: Node) {
        var current: Node? = node.next
        while (current != null && current.count < node.count) {
            current = current.next
        }
        if (current != node.next) {
            if (head == node) {
                head = node.next
            } else {
                node.prev!!.next = node.next
            }
            node.next!!.prev = node.prev

            node.next = current
            if (current == null) {
                node.prev = tail
                tail!!.next = node
                tail = node
            } else {
                current.prev?.next = node
                node.prev = current.prev
                current.prev = node
            }
        }
    }

    private fun moveDown(node: Node) {
        var current: Node? = node.prev
        while (current != null && current.count > node.count) {
            current = current.prev
        }
        if (current != node.prev) {
            if (tail == node) {
                tail = node.prev
            } else {
                node.next!!.prev = node.prev
            }
            node.prev!!.next = node.next

            node.prev = current
            if (current == null) {
                node.next = head
                head!!.prev = node
                head = node
            } else {
                node.next = current.next
                current.next!!.prev =  node
                current.next = node
            }
        }
    }
}

fun main() {
    val a = AllOne()
    a.inc("hello")
    a.inc("world")
    a.inc("leet")
    a.inc("code")
    a.inc("ds")
    a.inc("leet")
    println(a.getMaxKey())
    a.inc("ds")
    a.dec("leet")
    println(a.getMaxKey())
}