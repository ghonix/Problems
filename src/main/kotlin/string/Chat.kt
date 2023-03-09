package string

class Chat {
    private val dfa = hashMapOf(
        Pair(0, Pair('c', 1)),
        Pair(1, Pair('h', 2)),
        Pair(2, Pair('a', 3)),
        Pair(3, Pair('t', 4))
    )
    private val stateTokens = hashMapOf(
        Pair('c', 0),
        Pair('h', 1),
        Pair('a', 2),
        Pair('t', 3)
    )
    private val initialState = 0
    private val validTerminalState = 4

    private val stateMap = HashMap<Int, Int>()


    fun minPlayers(chat: String): Int {
        for (token in chat) {
            val state: Int = stateTokens[token] ?: return -1
            if (state == initialState) {
                val currentTerminalStateCount = stateMap[validTerminalState]
                if (currentTerminalStateCount != null && currentTerminalStateCount > 0) {
                    stateMap[validTerminalState] = currentTerminalStateCount - 1
                }
                stateMap[state] = 1
            }
            val stateCount = stateMap[state]
            if (stateCount != null && stateCount > 0) {
                val transition = dfa[state]
                if (transition != null && transition.first == token) {
                    // one state can advance
                    val currentStateCount = stateMap[state]
                    if (currentStateCount != null && currentStateCount > 0) {
                        stateMap[state] = currentStateCount - 1
                    }

                    stateMap[transition.second] = stateMap[transition.second]?.inc() ?: 1
                }
            } else {
                return -1
            }
        }
        return validateStates(stateMap)
    }

    private fun validateStates(stateMap: java.util.HashMap<Int, Int>): Int {
        for (entry in stateMap.entries) {
            if (entry.key != validTerminalState && entry.value > 0) {
                return -1
            }
        }

        return stateMap[validTerminalState] ?: -1
    }
}

fun main(args: Array<String>) {
    println(
        Chat().minPlayers("chacthat")
    )
    println(Chat().minPlayers("chaccthahtatchat"))
    println(Chat().minPlayers("chatchht"))
    println(Chat().minPlayers("chacthat"))
}
