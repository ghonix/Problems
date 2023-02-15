class LFUCache(val capacity: Int) {

    val dictionary = HashMap<Int, Pair<Int, Int>>()
    val frequencies = HashMap<Int, LinkedHashSet<Int>>()

    var minFrequency = 0

    fun get(key: Int): Int {
        val v = dictionary[key] ?: return -1
        val frequency = v.second
        val value = v.first
        frequencies[frequency]?.remove(key)
        if (frequencies[frequency]?.isEmpty() == true) {
            if (minFrequency == frequency) {
                minFrequency++
            }
        }
        frequencies.putIfAbsent(frequency + 1, LinkedHashSet())
        frequencies[frequency + 1]?.add(key)
        insert(key, frequency + 1, value)
        return value
    }

    fun put(key: Int, value: Int) {
        if (capacity <= 0) {
            return
        }

        val v = dictionary[key]
        if (v != null) {
            val keyFrequency = v.second
            dictionary[key] = Pair(value, keyFrequency)
            get(key)
            return
        }

        if (capacity == dictionary.size) {
            val list = frequencies[minFrequency]
            if (list != null) {
                if (list.isNotEmpty()) {
                    val e = list.iterator().next()
                    list.remove(e)
                    dictionary.remove(e)
                } else {
                    frequencies.remove(minFrequency)
                }
            }
        }
        minFrequency = 1

        insert(key, 1, value)
    }

    private fun insert(key: Int, f: Int, value: Int) {
        dictionary[key] = Pair(value, f)
        frequencies.putIfAbsent(f, LinkedHashSet())
        frequencies[f]?.add(key)
    }

}

fun main() {
    val cache = LFUCache(2)
    cache.put(1, 1)
    cache.put(2, 2)
    println(cache.get(1))
    cache.put(3, 3)
    println(cache.get(2))
    println(cache.get(3))
    cache.put(4, 4)
    println(cache.get(1))
    println(cache.get(3))
    println(cache.get(4))
}