package string

class Chat {


    fun main(args: Array<String>) {
        println(minPlayers("chacthat"))
        println(minPlayers("chaccthahtatchat"))
        println(minPlayers("chatchht"))
        println(minPlayers("chacthataaaa"))
    }
//
    /*
    inProgress = 1
    c -> 1
    h -> 1
    a -> 1
    t -> 1
    */

    fun minPlayers(s: String): Int {
        var finished = 0
        var inProgress = 0
        val charMap = HashMap<Char, Int>()
        for (char in arrayOf('c', 'h', 'a', 't')) {
            charMap[char] = 0
        }

        // loop till end of string
        // if detect start of chat
        // if finished is non zero -> decrement it
        // increment in progress
        //

        // if detect end of chat
        // increment finished
        // decrement in progress
        for (i in s.indices) {
            // detect a start
            if (s[i] == 'c') {
                charMap['c'] = charMap['c']!! + 1

                if (finished > 0) {
                    finished--
                }
                inProgress++
            } else if (s[i] == 't') {
                charMap['t'] = charMap['t']!! - 1
                if (inProgress <= 0) {
                    // Error state
                    return -1
                } else {
                    finished++
                    inProgress--
                }

                var error = decrementMap(charMap)
                if (error) {
                    return -1
                }
            } else if ("ha".contains(s[i])) {
                charMap[s[i]] = charMap[s[i]]!! + 1
            }
        }

        var error = verifyMap(charMap)
        if (error) {
            return -1
        }
        return finished
    }

    fun verifyMap(charMap: HashMap<Char, Int>): Boolean {
        for (char in arrayOf('c', 'h', 'a', 't')) {
            if (charMap[char]!! > 0) {
                return true
            }
        }
        return false
    }

    fun decrementMap(charMap: HashMap<Char, Int>): Boolean {
        for (char in arrayOf('c', 'h', 'a', 't')) {
            if (charMap[char] == 0) {
                return true
            } else {
                charMap[char] = charMap[char]!! - 1
            }
        }
        return false
    }
}