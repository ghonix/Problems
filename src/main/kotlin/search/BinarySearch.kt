package search

class BinarySearch {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(arrayOf(1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 100, 120, 199, 1000).binarySearch(10))
            arrayOf(1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 100, 120, 199, 1000).binarySearch(-1)
                ?.let { println(it) } ?: println("Not found")
        }
    }
}

fun Array<Int>.binarySearch(element: Int): Int? {
    var start = 0
    var end = this.size

    while (start <= end) {
        var middle = (end + start) / 2
        var current = this[middle]
        if (current == element) {
            return middle
        } else if (element > current) {
            start = middle + 1
        } else {
            end = middle - 1
        }
    }

    return null
}