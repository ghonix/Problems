package tree

import java.util.Vector

/**
 *
 */
class WordSearch {
    class Node {
        val children = HashMap<Char, Node?>()
        var word: String? = null
    }

    private var _result = Vector<String>()
    private var _board: Array<CharArray>? = null
    private var root: Node = Node()


    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        _board = board
        // create a trie

        for (word in words) {
            var node = root
            for (c in word) {
                var current = node.children[c]
                if (current == null) {
                    current = Node()
                    node.children[c] = current

                }
                node = current
            }
            node.word = word
        }

        for (row in board.indices) {
            for (col in board[row].indices) {
                if (root.children.containsKey(board[row][col])) {
                    backTrack(root, row, col)
                }
            }
        }
        return _result
    }

    private fun backTrack(parent: Node, row: Int, col: Int) {
        val letter = _board?.get(row)?.get(col)
        val currentNode = parent.children[letter]
        if (currentNode?.word != null) {
            _result.add(currentNode.word)
            currentNode.word = null
        }

        // mark current node
        _board?.get(row)?.set(col, '#')

        val rowOffset = intArrayOf(-1, 0, 1, 0)
        val colOffset = intArrayOf(0, 1, 0, -1)
        for (i in rowOffset.indices) {
            val newRow = row + rowOffset[i]
            val newCol = col + colOffset[i]

            if (newRow < 0 || newRow >= _board!!.size || newCol < 0 || newCol >= _board!![newRow].size) {
                continue
            }
            if (currentNode?.children?.containsKey(_board?.get(newRow)?.get(newCol) ?: "") == true) {
                backTrack(currentNode, newRow, newCol)
            }
        }

        if (letter != null) {
            _board?.get(row)?.set(col, letter)
        }
    }
}


fun main() {
    println(
        WordSearch().findWords(
            arrayOf(
                charArrayOf('o', 'a', 'a', 'n'),
                charArrayOf('e', 't', 'a', 'e'),
                charArrayOf('i', 'h', 'k', 'r'),
                charArrayOf('i', 'f', 'l', 'v')
            ),
            arrayOf("oath", "pea", "eat", "rain")
        )
    )
}