fun mergeSort(list: IntArray): IntArray {
    _mergeSort(list, 0, list.size - 1)
    return list
}

fun _mergeSort(list: IntArray, start: Int, end: Int) {
    if (start < end) {
        val mid = (end + start) / 2
        _mergeSort(list, start, mid)
        _mergeSort(list, mid + 1, end)
        _merge(list, start, mid, end)
    }
}

fun _merge(list: IntArray, start: Int, mid: Int, end: Int) {
    val size = end - start + 1
    val output = IntArray(size)
    var left = start
    var right = mid + 1
    var k = 0
    while (left < mid +1 && right <= end) {
        if (list[left] > list[right]) {
            output[k] = list[left]
            k++
            left++
        } else {
            output[k] = list[right]
            k++
            right++
        }
    }
    while (left < mid + 1) {
        output[k] = list[left]
        k++
        left++
    }
    while (right <= end) {
        output[k] = list[right]
        k++
        right++
    }

    for (i in start..end) {
        list[i] = output[i - start]
    }
}

fun main(args: Array<String>) {
    var values = intArrayOf(3, 6, 3,8,9,10,-1,3,2,-10,-20, 0)
    values = mergeSort(values)
    println(values.contentToString())

}