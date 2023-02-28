package search

fun Array<Int>.binarySearch(element: Int): Pair<Int, Array<Int>> {
    var start = 0
    var end = this.size
    var middle = (end) / 2
    while (start <= end) {
        middle = (end + start) / 2
        var current = this[middle]
        if (current == element) {
            return Pair(middle, this)
        } else if (element > current) {
            start = middle + 1
        } else {
            end = middle - 1
        }
    }

    return Pair(middle, this)
}

fun main(args: Array<String>) {
//    println(arrayOf(1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 100, 120, 199, 1000).binarySearch(10))
//    arrayOf(1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 100, 120, 199, 1000).binarySearch(-1)
//        ?.let { println(it) } ?: println("Not found")
    val (index, array) = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 120, 199, 1000)
        .binarySearch(99)
    println("Value Greater than or equal ${array[index]}")

}