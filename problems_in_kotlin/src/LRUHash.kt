


class LRUHash<K, V> (capacity: Int){

    class Record<V> {
        var next: Record<V>? = null
        var previous: Record<V>? = null
        var data: V? = null

    }

    val hashCapacity = capacity

    val hash = HashMap<K, Record<V>>()
    var lru: Record<V>? = null // the head of the linked list
    var mru: Record<V>? = null // the tail of the linked list


    @Synchronized operator fun get(key: K): V? {
        var record = hash.get(key)
        if (record != null) {
            moveToMRU(record)
            return record.data
        }
        return null
    }

    @Synchronized operator fun set(key: K, value: V) {
        var record = hash.get(key)
        if (record == null) {
            // add an item
            record = Record()
            record.data = value

            // check if capacity reached and empty an element if so
            if (hash.size == hashCapacity) {
                removeLRU()
            }
            // add to hash
            hash[key] = record
            // add to the mru list
            addToMru(record)
        } else {
            record.data = value
            moveToMRU(record)
        }
    }

    private fun removeLRU() {
        lru = lru?.next
        lru?.previous = null
    }

    @Synchronized private fun addToMru(record: LRUHash.Record<V>) {
        if (lru == null) {
            lru = record
            mru = record
        } else {
            // connect the record to the tail of the list
            mru?.next = record
            record.previous = mru
            record.next = null
            mru = record
        }
    }

    @Synchronized private fun moveToMRU(record: LRUHash.Record<V>) {
        if (mru != lru) {
            // adjust the lru pointer
            if(lru == record) {
                lru = record.next
            }

            // Connect previous and next nodes
            record.previous?.next = record.next
            record.next?.previous = record.previous

            // connect the record to the tail of the list
            mru?.next = record
            record.previous = mru
            record.next = null
            mru = record
        }
    }
}