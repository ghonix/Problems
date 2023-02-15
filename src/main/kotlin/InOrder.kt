/*

Implement a function that takes two String inputs and checks if the first input string is "in order" based on an order the second string represents. For example, an ordering of "abc" indicates that the input string is "in order" if all 'a's occur before all 'b's, and all 'b's occur before all 'c's.

"hello", "le" -> TRUe
"hello", "el" -> False

idea:
(0,1,2)
"elh"

"hello"
e -> 0
l -> 1
l -> 1

"elh".indexOf('h') -> 2

currentOrder char
-1             h
0              e
1              l
*/


import kotlin.io.*;

fun main(args: Array<String>) {
    println(inOrder("hello", "el"))

    println(inOrder("hello", "elh"))
}


fun inOrder( s: String, alphabet: String): Boolean {
    var currentOrder = -1
    for (char in s) {
        val order = alphabet.indexOf(char)
        if (order == -1) {
          continue
        } else if (currentOrder <= order) {
            currentOrder = order
        } else {
            return false
        }
    }
    return true
}